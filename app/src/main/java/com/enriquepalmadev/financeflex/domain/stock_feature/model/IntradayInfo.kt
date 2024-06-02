package com.enriquepalmadev.financeflex.domain.stock_feature.model

import java.time.LocalDateTime

data class IntradayInfo(
    val date: LocalDateTime,
    val close: Double
)