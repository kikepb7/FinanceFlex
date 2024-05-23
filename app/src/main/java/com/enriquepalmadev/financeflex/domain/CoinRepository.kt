package com.enriquepalmadev.financeflex.domain

import com.enriquepalmadev.financeflex.domain.model.CoinModel
import com.enriquepalmadev.financeflex.domain.model.Either
import com.enriquepalmadev.financeflex.domain.model.FailureDomain

interface CoinRepository {
    suspend fun fetchCoinList(): Either<FailureDomain, List<CoinModel>?>
    suspend fun fetchCoinDetail(coinId: String): Either<FailureDomain, CoinModel?>
}