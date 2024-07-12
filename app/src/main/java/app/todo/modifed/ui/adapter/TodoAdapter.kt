package app.todo.modifed.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.todo.modifed.R
import app.todo.modifed.databinding.ItemTodoBinding
import app.todo.modifed.model.Todo
import app.todo.modifed.model.TodoStatus
import app.todo.modifed.ui.interfaces.TodoInterface
import app.todo.modifed.utils.ListDiffUtil
import java.time.format.DateTimeFormatter
import java.util.UUID

class TodoAdapter(private val todoInterface: TodoInterface): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var data: List<Todo> = emptyList()
        set(newValue) {
            val diffUtil = ListDiffUtil(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newValue
            Log.d(":TEST", "TEST1")

            diffUtilResult.dispatchUpdatesTo(this@TodoAdapter)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(view, parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = data[position]
        with(holder.binding){

            titleText.text = item.title
            body.text = item.body
            startTime.text = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(item.startTime)

            when(item.todoStatus){
                TodoStatus.DONE.index -> {
                    statusView.setBackgroundColor(ContextCompat.getColor(root.context, R.color.done))
                }
                TodoStatus.DEFAULT.index -> {
                    statusView.setBackgroundColor(ContextCompat.getColor(root.context, R.color.def))

                }
                TodoStatus.HIGH.index -> {
                    statusView.setBackgroundColor(ContextCompat.getColor(root.context, R.color.high))

                }
                TodoStatus.NORMAL.index -> {
                    statusView.setBackgroundColor(ContextCompat.getColor(root.context, R.color.normal))
                }
            }

            if(item.finishTime != null){
                finishTime.text = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(item.finishTime)
            }

            holder.itemView.setOnClickListener {
                if(item.todoStatus != TodoStatus.DONE.index){
                    todoInterface.onClick(item)
            }
            }
        }
    }

    class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

}