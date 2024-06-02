package com.enriquepalmadev.financeflex.domain.stock_feature.model

data class CompanyInfo(
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String,
)