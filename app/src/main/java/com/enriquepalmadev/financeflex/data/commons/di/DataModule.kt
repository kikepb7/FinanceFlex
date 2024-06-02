package com.enriquepalmadev.financeflex.data.commons.di

import android.app.Application
import androidx.room.Room
import com.enriquepalmadev.financeflex.data.coin_feature.datasource.CoinRemoteDataSource
import com.enriquepalmadev.financeflex.data.coin_feature.repository.CoinRepositoryImpl
import com.enriquepalmadev.financeflex.data.coin_feature.service.CryptoService
import com.enriquepalmadev.financeflex.data.commons.utils.Constants
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoDao
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoDatabase
import com.enriquepalmadev.financeflex.data.portfolio_feature.repository.TodoRepositoryImpl
import com.enriquepalmadev.financeflex.data.stock_feature.database.StockDao
import com.enriquepalmadev.financeflex.data.stock_feature.database.StockDatabase
import com.enriquepalmadev.financeflex.data.stock_feature.service.StockService
import com.enriquepalmadev.financeflex.domain.coin_feature.CoinRepository
import com.enriquepalmadev.financeflex.domain.portfolio_feature.TodoRepository
import com.enriquepalmadev.financeflex.ui.utils.navigation.AppScreens
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.net.UnknownHostException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
        addInterceptor(NetworkErrorInterceptor())
    }.build()


    class NetworkErrorInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            try {
                return chain.proceed(request)
            } catch (e: UnknownHostException) {
                throw e
            }
        }
    }

    // CRYPTO
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinsFromApi(retrofit: Retrofit): CryptoService {
        return retrofit.create(CryptoService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(application, TodoDatabase::class.java, "todo_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

    @Singleton
    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }

    @Singleton
    @Provides
    fun provideCoinRemoteDataSource(cryptoService: CryptoService): CoinRemoteDataSource {
        return CoinRemoteDataSource(cryptoService)
    }

    @Singleton
    @Provides
    fun provideCoinRepository(coinRemoteDataSource: CoinRemoteDataSource): CoinRepository {
        return CoinRepositoryImpl(coinRemoteDataSource)

    }


    // STOCK
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideStockApi(moshi: Moshi): StockService {
        return Retrofit.Builder()
            .baseUrl(Constants.STOCK_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideStockDatabase(application: Application): StockDatabase {
        return Room.databaseBuilder(application, StockDatabase::class.java, "stock_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideStockDao(stockDatabase: StockDatabase): StockDao {
        return stockDatabase.dao
    }
}