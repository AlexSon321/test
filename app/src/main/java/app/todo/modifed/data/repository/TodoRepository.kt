package app.todo.modifed.data.repository

import androidx.lifecycle.LiveData
import app.todo.modifed.data.dao.TodoDao
import app.todo.modifed.model.Todo

class TodoRepository(private val todoDao: TodoDao) {

    fun getListByStatus(status: Int): List<Todo> = todoDao.getByStatus(status)

    fun getByStatusAndKey(status: Int, key: Int): Todo = todoDao.get(status, key)

    suspend fun getList() = todoDao.getList()

    suspend fun add(todo: Todo) = todoDao.add(todo)

    suspend fun update(todo: Todo) = todoDao.update(todo)

}