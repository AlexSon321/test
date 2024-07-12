package app.todo.modifed.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.todo.modifed.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("SELECT * from todoTable WHERE todoStatus =:todoStatus AND `key` =:key")
    fun get(todoStatus: Int, key: Int): Todo

    @Query("SELECT * from todoTable WHERE todoStatus = :todoStatus")
    fun getByStatus(todoStatus: Int): List<Todo>

    @Query("SELECT * from todoTable")
    suspend fun getList(): List<Todo>
}
