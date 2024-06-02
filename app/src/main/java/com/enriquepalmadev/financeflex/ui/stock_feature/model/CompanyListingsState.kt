package com.enriquepalmadev.financeflex.ui.stock_feature.model

import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
