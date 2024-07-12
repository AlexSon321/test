package app.todo.modifed.ui.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.todo.modifed.R
import app.todo.modifed.databinding.FragmentTodoListBinding
import app.todo.modifed.model.Todo
import app.todo.modifed.model.TodoStatus
import app.todo.modifed.ui.adapter.TodoAdapter
import app.todo.modifed.ui.interfaces.TodoInterface
import app.todo.modifed.ui.viewmodel.TodoViewModel
import app.todo.modifed.utils.CurrentUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodoListFragment : Fragment(), TodoInterface {

    private val viewModel: TodoViewModel by viewModels()

    private var adapter = TodoAdapter(this)

    private var _binding: FragmentTodoListBinding? = null
    private val binding: FragmentTodoListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.todoList.observe(viewLifecycleOwner){ list ->
            adapter.data = list.filterNot { it.todoStatus == TodoStatus.DONE.index }
        }



        binding.goToAddScreen.setOnClickListener {
            val layout = LayoutInflater.from(activity).inflate(R.layout.dialog_layout, null)
            val title = layout.findViewById<EditText>(R.id.titleEditText)
            val body = layout.findViewById<EditText>(R.id.bodyEditText)
            val checkHigh = layout.findViewById<CheckBox>(R.id.checkBox)
            val background = ContextCompat.getDrawable(requireContext(), R.drawable.dialog_background)

            MaterialAlertDialogBuilder(requireActivity())
                .setView(layout)
                .setTitle(getString(R.string.dialogTitle))
                .setBackground(background)
                .setPositiveButton(activity?.getString(R.string.add_task)
                ) { dialog, p1 ->
                    if (title.text.isNotEmpty() && body.text.isNotEmpty()) {

                        val status = if(checkHigh.isChecked) TodoStatus.HIGH.index else TodoStatus.NORMAL.index

                        val todo = Todo(
                            null,
                            title = title.text.toString(),
                            body = body.text.toString(),
                            startTime = LocalDateTime.now(),
                            todoStatus = status
                            )

                        viewModel.add(todo)
                    } else {
                    }
                }

                .setNegativeButton(requireActivity().getString(R.string.cancel_task)
                ) { dialog, p1 ->
                    dialog.dismiss()
                    Log.d("TEST", "TESTT")
                }
                .create().show()

        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.getList()
    }

    override fun onClick(todo: Todo) {
        val background = ContextCompat.getDrawable(requireContext(), R.drawable.dialog_background)

        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(getString(R.string.finishTitle))
            .setBackground(background)
            .setPositiveButton(activity?.getString(R.string.finish_task)
            ) { dialog, p1 ->
                val todoUpdate = Todo(todo.key, todo.title, todo.body, todo.startTime, TodoStatus.DONE.index, LocalDateTime.now())
                viewModel.update(todoUpdate)
            }

            .setNegativeButton(requireActivity().getString(R.string.cancel_task)
            ) { dialog, p1 ->
                dialog.dismiss()
            }
            .create().show()
    }

}