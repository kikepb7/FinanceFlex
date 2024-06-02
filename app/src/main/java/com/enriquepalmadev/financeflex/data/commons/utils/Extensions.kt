package com.enriquepalmadev.financeflex.data.commons.utils

import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDetailDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.FailureDto
import com.enriquepalmadev.financeflex.data.stock_feature.database.CompanyListingEntity
import com.enriquepalmadev.financeflex.data.stock_feature.dto.CompanyInfoDto
import com.enriquepalmadev.financeflex.data.stock_feature.dto.IntradayInfoDto
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain
import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyInfo
import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyListing
import com.enriquepalmadev.financeflex.domain.stock_feature.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

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
        symbol = symbol,
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
        team = team,
        logo = logo,
        links = links
    )
}

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange,
        assetType = assetType,
        ipoDate = ipoDate,
        delistingDate = delistingDate,
        status = status
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange,
        assetType = assetType,
        ipoDate = ipoDate,
        delistingDate = delistingDate,
        status = status
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}

fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}