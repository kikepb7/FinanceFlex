package com.enriquepalmadev.financeflex.domain.stock_feature.model

data class CompanyListing(
    val name: String,
    val symbol: String,
    val exchange: String,
    val assetType: String,
    val ipoDate: String,
    val delistingDate: String,
    val status: String
)