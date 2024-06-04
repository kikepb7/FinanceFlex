package com.enriquepalmadev.financeflex.presentation.stock_feature.model

import com.enriquepalmadev.financeflex.domain.stock_feature.model.CompanyInfo
import com.enriquepalmadev.financeflex.domain.stock_feature.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)