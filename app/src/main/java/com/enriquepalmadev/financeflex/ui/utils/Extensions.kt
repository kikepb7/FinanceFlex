package com.enriquepalmadev.financeflex.ui.utils

import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.model.FailureDomain
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenEmpty
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenError

fun FailureDomain.toComicListScreenError(): CoinListScreenError {
    return when (this) {
        is FailureDomain.ApiError -> {
            CoinListScreenError(
                image = R.drawable.error_logo,
                errorMsg = R.string.api_error
            )
        }

        is FailureDomain.Unauthorized -> {
            CoinListScreenError(
                image = R.drawable.error_logo,
                errorMsg = R.string.authentication_error
            )
        }

        is FailureDomain.UnknownHostError -> {
            CoinListScreenError(
                image = R.drawable.error_logo,
                errorMsg = R.string.unexpected_error
            )
        }
    }
}

fun Throwable.toComicListScreenError(): CoinListScreenError {
    return CoinListScreenError(
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