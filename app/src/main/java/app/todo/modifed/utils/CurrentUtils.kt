package app.todo.modifed.utils

import android.content.Context

class CurrentUtils(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    var currentTodoKey: Int
        get() = sharedPreferences.getInt(CURRENT_KEY, 0)
        set(value){
            with(sharedPreferences.edit()){
                putInt(CURRENT_KEY, value)
                apply()
            }
        }

    var currentTodoStatus: Int
        get() = sharedPreferences.getInt(CURRENT_STATUS, 0)
        set(value){
            with(sharedPreferences.edit()){
                putInt(CURRENT_STATUS, value)
                apply()
            }
        }

    companion object{
        const val SHARED_PREFERENCES_NAME = "MyPrefs"
        const val CURRENT_KEY = "current_key"
        const val CURRENT_STATUS = "current_status"
    }
}