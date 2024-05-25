package com.enriquepalmadev.financeflex.ui.coin_feature.model

import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel

data class CoinListScreenLoading(
    val loader: Boolean
)

data class CoinListScreenError(
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
    val loadingScreenData: CoinListScreenLoading = CoinListScreenLoading(loader = true),
    val errorScreenData: CoinListScreenError? = null,
    val emptyListScreenData: CoinListScreenEmpty? = null
)