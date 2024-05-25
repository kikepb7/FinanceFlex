package com.enriquepalmadev.financeflex.domain.coin_feature.usecase

import com.enriquepalmadev.financeflex.domain.coin_feature.CoinRepository
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain
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