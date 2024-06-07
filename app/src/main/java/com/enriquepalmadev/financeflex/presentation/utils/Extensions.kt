package com.enriquepalmadev.financeflex.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenEmpty
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenItemModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.ScreenError

fun FailureDomain.toComicListScreenError(): ScreenError {
    return when (this) {
        is FailureDomain.ApiError -> {
            ScreenError(
                image = R.drawable.error_logo,
                errorMsg = R.string.connection_error
            )
        }

        is FailureDomain.Unauthorized -> {
            ScreenError(
                image = R.drawable.error_logo,
                errorMsg = R.string.authentication_error
            )
        }

        is FailureDomain.UnknownHostError -> {
            ScreenError(
                image = R.drawable.error_logo,
                errorMsg = R.string.unexpected_error
            )
        }
    }
}

fun Throwable.toComicListScreenError(): ScreenError {
    return ScreenError(
        image = R.drawable.error_logo,
        errorMsg = R.string.exception_error
    )
}

fun toEmptyListModel(): CoinListScreenEmpty {
    return CoinListScreenEmpty(
        image = R.drawable.ic_launcher_background,
        emptyMessage = R.string.empty_list_error
    )
}

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

fun List<CoinModel>.toCoinListModel(): CoinListModel {
    return CoinListModel(
        coinsModel = this.map {
            it.toCoinModel()
        }
    )
}

fun CoinModel.toCoinModel(): CoinListScreenItemModel {
    return CoinListScreenItemModel(
        coin = this
    )
}