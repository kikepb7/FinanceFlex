package com.enriquepalmadev.financeflex.ui.home_feature.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enriquepalmadev.financeflex.data.portfolio_feature.database.TodoEntity
import com.enriquepalmadev.financeflex.domain.portfolio_feature.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repo: TodoRepository
): ViewModel() {

    private val _todos : MutableStateFlow<List<TodoEntity>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch(Dispatchers.IO){
            repo.getTodos().collect{ data ->
                _todos.update { data }
            }
        }
    }

    fun updateTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTodo(todo = todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTodo(todo = todo)
        }
    }

    fun addTodo(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTodo(todo = todo)
        }
    }
}