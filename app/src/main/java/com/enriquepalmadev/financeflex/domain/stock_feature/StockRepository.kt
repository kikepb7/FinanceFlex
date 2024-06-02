package com.enriquepalmadev.financeflex.domain.stock_feature

import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyInfo
import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyListing
import com.enriquepalmadev.financeflex.domain.stock_feature.model.IntradayInfo
import com.enriquepalmadev.financeflex.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}