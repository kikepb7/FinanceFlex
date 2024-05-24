package com.enriquepalmadev.financeflex.domain.usecase

import com.enriquepalmadev.financeflex.domain.CoinRepository
import com.enriquepalmadev.financeflex.domain.model.CoinDetailModel
import com.enriquepalmadev.financeflex.domain.model.Either
import com.enriquepalmadev.financeflex.domain.model.FailureDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchCoinDetailUseCase @Inject constructor(
    private val coinDetailUseCase: CoinRepository
) {
    suspend fun fetchCoinDetail(coinId: String): Flow<Either<FailureDomain, CoinDetailModel?>> {
        return flow { emit(coinDetailUseCase.fetchCoinDetail(coinId = coinId)) }
    }
}