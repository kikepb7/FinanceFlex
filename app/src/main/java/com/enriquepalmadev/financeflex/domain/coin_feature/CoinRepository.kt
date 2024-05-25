package com.enriquepalmadev.financeflex.domain.coin_feature

import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain

interface CoinRepository {
    suspend fun fetchCoinList(): Either<FailureDomain, List<CoinModel>?>
    suspend fun fetchCoinDetail(coinId: String): Either<FailureDomain, CoinDetailModel?>
}