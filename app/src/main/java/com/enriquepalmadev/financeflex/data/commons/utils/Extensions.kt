package com.enriquepalmadev.financeflex.data.commons.utils

import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDetailDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.FailureDto
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain

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

fun CoinDetailDto.dtoToCoinDetailModel(): CoinDetailModel {
    return CoinDetailModel(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team
    )
}