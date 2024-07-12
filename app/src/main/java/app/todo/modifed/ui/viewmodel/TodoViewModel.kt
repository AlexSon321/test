package app.todo.modifed.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todo.modifed.data.MainDatabase
import app.todo.modifed.data.repository.TodoRepository
import app.todo.modifed.model.Todo
import app.todo.modifed.utils.CurrentUtils
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val todoRepository: TodoRepository
    private var currentUtils = CurrentUtils(application)

    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList

    private val _todoListByStatus = MutableLiveData<List<Todo>>()
    val todoListByStatus: LiveData<List<Todo>> = _todoListByStatus

    private val _currentTodo = MutableLiveData<Todo>()
    val currentTodo: LiveData<Todo> = _currentTodo

    init {
        val dao = MainDatabase.createDatabase(application).getDao()
        todoRepository = TodoRepository(dao)
    }

    fun add(todo: Todo) = viewModelScope.launch {
        todoRepository.add(todo)
        getList()
    }

    fun update(todo: Todo) = viewModelScope.launch {
        todoRepository.update(todo)
        getList()
    }

    fun getList() = viewModelScope.launch{
        _todoList.value = todoRepository.getList()
    }

    fun getTodo() = viewModelScope.launch {
        currentUtils.currentTodoKey.let {key ->
            currentUtils.currentTodoStatus.let { status ->
                _currentTodo.value = todoRepository.getByStatusAndKey(status, key)
            }
        }
    }

}