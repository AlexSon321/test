package app.todo.modifed.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.Slide
import app.todo.modifed.MainActivity
import app.todo.modifed.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.enterTransition = Slide()
        window.exitTransition = Slide()

        val intent = Intent(this, MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity).toBundle())
        }, 3000)

        setContentView(R.layout.activity_splash)
    }
}