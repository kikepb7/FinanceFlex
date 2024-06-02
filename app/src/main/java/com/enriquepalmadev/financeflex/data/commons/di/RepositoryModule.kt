package com.enriquepalmadev.financeflex.data.commons.di

import com.enriquepalmadev.financeflex.data.stock_feature.csv.CSVParser
import com.enriquepalmadev.financeflex.data.stock_feature.csv.CompanyListingsParser
import com.enriquepalmadev.financeflex.data.stock_feature.csv.IntradayInfoParser
import com.enriquepalmadev.financeflex.data.stock_feature.repository.StockRepositoryImpl
import com.enriquepalmadev.financeflex.domain.stock_feature.StockRepository
import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyListing
import com.enriquepalmadev.financeflex.domain.stock_feature.model.IntradayInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}