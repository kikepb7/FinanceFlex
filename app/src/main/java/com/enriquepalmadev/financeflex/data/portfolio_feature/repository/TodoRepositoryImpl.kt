package com.enriquepalmadev.financeflex.data.portfolio_feature.repository

import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoDao
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoEntity
import com.enriquepalmadev.financeflex.domain.portfolio_feature.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDatabase: TodoDao
): TodoRepository {

    override suspend fun getTodos(): Flow<List<TodoEntity>> = todoDatabase.getTodos()

    override suspend fun addTodo(todo: TodoEntity) = todoDatabase.addTodo(todo = todo)

    override suspend fun updateTodo(todo: TodoEntity) = todoDatabase.updateTodo(todo = todo)

    override suspend fun deleteTodo(todo: TodoEntity) = todoDatabase.deleteTodo(todo = todo)
}