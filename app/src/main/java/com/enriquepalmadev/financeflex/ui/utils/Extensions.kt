package com.enriquepalmadev.financeflex.ui.utils

import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.model.FailureDomain
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenEmpty
import com.enriquepalmadev.financeflex.ui.model.CoinListScreenError

fun FailureDomain.toComicListScreenError(): CoinListScreenError {
    return when (this) {
        is FailureDomain.ApiError -> {
            CoinListScreenError(
                image = R.drawable.ic_launcher_background, // TODO --> Cambiarlo
                errorMsg = R.string.unexpected_error // TODO --> Cambiarlo
            )
        }

        is FailureDomain.Unauthorized -> {
            CoinListScreenError(
                image = R.drawable.ic_launcher_background, // TODO --> Cambiarlo
                errorMsg = R.string.unexpected_error // TODO --> Cambiarlo
            )
        }

        is FailureDomain.UnknownHostError -> {
            CoinListScreenError(
                image = R.drawable.ic_launcher_background, // TODO --> Cambiarlo
                errorMsg = R.string.unexpected_error // TODO --> Cambiarlo
            )
        }
    }
}

fun Throwable.toComicListScreenError(): CoinListScreenError {
    return CoinListScreenError(
        image = R.drawable.ic_launcher_background, // TODO --> Cambiarlo
        errorMsg = R.string.unexpected_error // TODO --> Cambiarlo
    )
}

fun toEmptyListModel(): CoinListScreenEmpty {
    return CoinListScreenEmpty(
        image = R.drawable.ic_launcher_background, // TODO --> Cambiarlo
        emptyMessage = R.string.unexpected_error // TODO --> Cambiarlo
    )
}