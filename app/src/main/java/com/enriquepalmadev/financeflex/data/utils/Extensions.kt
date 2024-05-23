package com.enriquepalmadev.financeflex.data.utils

import com.enriquepalmadev.financeflex.data.dto.CoinDto
import com.enriquepalmadev.financeflex.data.dto.FailureDto
import com.enriquepalmadev.financeflex.domain.model.CoinModel
import com.enriquepalmadev.financeflex.domain.model.FailureDomain

fun FailureDto.toFailureDomain(): FailureDomain {
    return when (this.code) {
        400 -> FailureDomain.UnknownHostError
        401 -> FailureDomain.Unauthorized
        else -> FailureDomain.ApiError
    }
}

fun List<CoinDto>.dtoToCoinListModel(): List<CoinModel> {
    return this.map {
        it.dtoToCoinModel()
    }
}

fun CoinDto.dtoToCoinModel(): CoinModel {
    return CoinModel(
        id = id,
        isActive = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}