package com.enriquepalmadev.financeflex.data.commons.di

import com.enriquepalmadev.financeflex.data.coin_feature.datasource.CoinRemoteDataSource
import com.enriquepalmadev.financeflex.data.coin_feature.repository.CoinRepositoryImpl
import com.enriquepalmadev.financeflex.data.coin_feature.service.CryptoService
import com.enriquepalmadev.financeflex.data.commons.utils.Constants
import com.enriquepalmadev.financeflex.domain.coin_feature.CoinRepository
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
        fun provideCoinRemoteDataSource(cryptoService: CryptoService): CoinRemoteDataSource {
            return CoinRemoteDataSource(cryptoService)
        }

        @Singleton
        @Provides
        fun provideCoinRepository(coinRemoteDataSource: CoinRemoteDataSource): CoinRepository {
            return CoinRepositoryImpl(coinRemoteDataSource)

        }

}