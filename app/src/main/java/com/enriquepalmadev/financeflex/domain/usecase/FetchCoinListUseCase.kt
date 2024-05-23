package com.enriquepalmadev.financeflex.domain.usecase

import com.enriquepalmadev.financeflex.domain.CoinRepository
import com.enriquepalmadev.financeflex.domain.model.CoinModel
import com.enriquepalmadev.financeflex.domain.model.Either
import com.enriquepalmadev.financeflex.domain.model.FailureDomain
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