package app.todo.modifed.ui.done

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.todo.modifed.R
import app.todo.modifed.databinding.FragmentDoneListBinding
import app.todo.modifed.model.Todo
import app.todo.modifed.model.TodoStatus
import app.todo.modifed.ui.adapter.TodoAdapter
import app.todo.modifed.ui.interfaces.TodoInterface
import app.todo.modifed.ui.viewmodel.TodoViewModel

class DoneListFragment : Fragment(), TodoInterface{

    private val viewModel: TodoViewModel by viewModels()

    private var _binding: FragmentDoneListBinding? = null
    private val binding: FragmentDoneListBinding get() = _binding!!

    private var adapter = TodoAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneListBinding.inflate(inflater, container, false)

        binding.recView.layoutManager = LinearLayoutManager(requireContext())

        binding.recView.adapter = adapter

        viewModel.todoList.observe(viewLifecycleOwner){list ->
            adapter.data = list.filter { it.todoStatus == TodoStatus.DONE.index }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
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

    }

}