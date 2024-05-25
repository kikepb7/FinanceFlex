package com.enriquepalmadev.financeflex.domain.coin_feature.usecase

import com.enriquepalmadev.financeflex.domain.coin_feature.CoinRepository
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchCoinListUseCase @Inject constructor(
    private val coinListUseCase: CoinRepository
) {
    suspend fun fetchCoinList(): Flow<Either<FailureDomain, List<CoinModel>?>> {
        return flow { emit(coinListUseCase.fetchCoinList()) }
    }
}