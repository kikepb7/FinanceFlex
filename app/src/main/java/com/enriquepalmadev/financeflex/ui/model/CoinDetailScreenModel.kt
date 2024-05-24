package com.enriquepalmadev.financeflex.ui.model

import com.enriquepalmadev.financeflex.domain.model.CoinDetailModel

data class CoinDetailScreenModel(
    val coinDetail: CoinDetailModel? = null
)

data class CoinDetailScreenState(
    val coinDetailScreenData: CoinDetailScreenModel? = null,
    val loadingScreenData: CoinListScreenLoading = CoinListScreenLoading(loader = true),
    val errorScreenData: CoinListScreenError? = null,
    val emptyListScreenData: CoinListScreenEmpty? = null
)