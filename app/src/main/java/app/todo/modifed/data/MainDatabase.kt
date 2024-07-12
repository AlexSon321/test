package app.todo.modifed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.todo.modifed.data.dao.TodoDao
import app.todo.modifed.model.Todo

@TypeConverters(*[LocalDateTimeConverter::class])
@Database(entities = [Todo::class], version = 2, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {

    abstract fun getDao(): TodoDao

    companion object{
        fun createDatabase(context: Context): MainDatabase{
            return Room.databaseBuilder(
                context,
                MainDatabase::class.java,
                "MainDatabase"
            ).fallbackToDestructiveMigration().build()
        }
    }

}