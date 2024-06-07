package com.enriquepalmadev.financeflex.presentation.home_feature.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.InvestEntity
import com.enriquepalmadev.financeflex.domain.portfolio_feature.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val repo: TodoRepository
): ViewModel() {

    private val _todos : MutableStateFlow<List<InvestEntity>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    init {
        getInvests()
    }

    private fun getInvests() {
        viewModelScope.launch(Dispatchers.IO){
            repo.getTodos().collect{ data ->
                _todos.update { data }
            }
        }
    }

    fun updateInvest(invest: InvestEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTodo(todo = invest)
        }
    }

    fun deleteInvest(invest: InvestEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTodo(todo = invest)
        }
    }

    fun addInvest(invest: InvestEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTodo(todo = invest)
        }
    }
}