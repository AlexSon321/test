package app.todo.modifed.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.todo.modifed.ui.done.DoneListFragment
import app.todo.modifed.ui.todo.TodoListFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TodoListFragment()
            else -> DoneListFragment()
        }

    }

}