package app.todo.modifed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import androidx.navigation.NavController
import app.todo.modifed.databinding.ActivityMainBinding
import app.todo.modifed.model.TodoStatus
import app.todo.modifed.ui.adapter.ViewPagerAdapter
import app.todo.modifed.utils.CurrentUtils
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkConfiguration
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = Slide()
        window.exitTransition = Slide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vPager.adapter = ViewPagerAdapter(this)
        binding.tabLayout.tabIconTint = null

        TabLayoutMediator(binding.tabLayout, binding.vPager){
                tab,pos ->
            when(pos){
                0 -> {
                    tab.text = getString(R.string.todoList)
                }
                1 -> {
                    tab.text = getString(R.string.doneList)
                }
            }
        }.attach()

        setContentView(binding.root)
    }
}