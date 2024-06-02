package com.enriquepalmadev.financeflex.ui.coin_feature.model

import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel

data class CoinDetailScreenModel(
    val coinDetail: CoinDetailModel? = null
)

data class CoinDetailScreenState(
    val coinDetailScreenData: CoinDetailScreenModel? = null,
    val loadingScreenData: CoinListScreenLoading = CoinListScreenLoading(loader = true),
    val errorScreenData: ScreenError? = null,
    val emptyListScreenData: CoinListScreenEmpty? = null
)