package app.todo.modifed.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {

    @TypeConverter
    fun fromString(value: String?): LocalDateTime? =
        if (value != null) LocalDateTime.from(DATE_FORMATTER.parse(value)) else null

    @TypeConverter
    fun toString(localDateTime: LocalDateTime?): String? =
        if (localDateTime != null) localDateTime.format(DATE_FORMATTER) else null

    companion object {
        var DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }
}