package com.enriquepalmadev.financeflex.data.repository

import com.enriquepalmadev.financeflex.data.datasource.CoinRemoteDataSource
import com.enriquepalmadev.financeflex.data.dto.CoinDetailDto
import com.enriquepalmadev.financeflex.data.dto.CoinDto
import com.enriquepalmadev.financeflex.data.utils.dtoToCoinListModel
import com.enriquepalmadev.financeflex.data.utils.toFailureDomain
import com.enriquepalmadev.financeflex.domain.CoinRepository
import com.enriquepalmadev.financeflex.domain.model.CoinModel
import com.enriquepalmadev.financeflex.domain.model.Either
import com.enriquepalmadev.financeflex.domain.model.FailureDomain
import javax.inject.Inject

class CoinRepositoryImpl  @Inject constructor(
    private val remoteDataSource: CoinRemoteDataSource
): CoinRepository {
    override suspend fun fetchCoinList(): Either<FailureDomain, List<CoinModel>?> {
        return try {
            return when (val response = remoteDataSource.fetchCoinsFromApi()) {
                is Either.Failure -> Either.Failure(error = response.error.toFailureDomain())
                is Either.Success -> Either.Success(
                    data = response.data?.dtoToCoinListModel()
                )
            }
        } catch (e: Exception) {
            Either.Failure(error = FailureDomain.ApiError)
        }
    }

    override suspend fun fetchCoinDetail(coinId: String): Either<FailureDomain, CoinModel> {
        TODO("Not yet implemented")
    }
}