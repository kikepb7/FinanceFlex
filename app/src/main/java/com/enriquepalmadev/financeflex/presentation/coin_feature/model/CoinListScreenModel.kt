package com.enriquepalmadev.financeflex.presentation.coin_feature.model

import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel

data class CoinListScreenLoading(
    val loader: Boolean
)

data class ScreenError(
    val image: Int,
    val errorMsg: Int
)

data class CoinListScreenEmpty(
    val image: Int,
    val emptyMessage: Int
)

data class CoinListScreenItemModel(
    val coin: CoinModel
)

data class CoinListModel(
    val coinsModel: List<CoinListScreenItemModel>
)

data class CoinListScreenModel(
    val coinListModel: CoinListModel? = null
)

data class CoinListScreenState(
    val coinScreenData: CoinListScreenModel? = null,
    val loadingScreenData: CoinListScreenLoading = CoinListScreenLoading(loader = false),
    val errorScreenData: ScreenError? = null,
    val emptyListScreenData: CoinListScreenEmpty? = null
)