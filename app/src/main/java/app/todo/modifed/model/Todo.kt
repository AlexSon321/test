package app.todo.modifed.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("todoTable")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val key: Int?,
    val title: String,
    val body: String,
    val startTime: LocalDateTime,
    val todoStatus: Int,
    val finishTime: LocalDateTime? = null,
    ): AbstractModel() {
    override fun getModelId(): String = startTime.toString()
}
