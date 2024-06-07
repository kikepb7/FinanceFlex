package com.enriquepalmadev.financeflex.data.portfolio_feature.repository

import com.enriquepalmadev.financeflex.data.portfolio_feature.database.InvestDao
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.InvestEntity
import com.enriquepalmadev.financeflex.domain.portfolio_feature.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDatabase: InvestDao
): TodoRepository {

    override suspend fun getTodos(): Flow<List<InvestEntity>> = todoDatabase.getInvest()

    override suspend fun addTodo(todo: InvestEntity) = todoDatabase.addInvest(invest = todo)

    override suspend fun updateTodo(todo: InvestEntity) = todoDatabase.updateInvest(todo = todo)

    override suspend fun deleteTodo(todo: InvestEntity) = todoDatabase.deleteInvest(todo = todo)
}