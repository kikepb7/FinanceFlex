package com.enriquepalmadev.financeflex.domain.portfolio_feature

import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodos(): Flow<List<TodoEntity>>
    suspend fun addTodo(todo: TodoEntity)
    suspend fun updateTodo(todo: TodoEntity)
    suspend fun deleteTodo(todo: TodoEntity)
}