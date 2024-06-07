package com.enriquepalmadev.financeflex.domain.portfolio_feature

import com.enriquepalmadev.financeflex.data.portfolio_feature.database.InvestEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodos(): Flow<List<InvestEntity>>
    suspend fun addTodo(todo: InvestEntity)
    suspend fun updateTodo(todo: InvestEntity)
    suspend fun deleteTodo(todo: InvestEntity)
}