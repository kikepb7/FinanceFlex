package com.enriquepalmadev.financeflex.data.stock_feature.csv

import java.io.InputStream

interface CSVParser<T> {
    suspend fun parse(stream: InputStream): List<T>
}