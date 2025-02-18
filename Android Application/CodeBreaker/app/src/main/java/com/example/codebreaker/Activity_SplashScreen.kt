package com.example.codebreaker
import android.annotation.SuppressLint
import android.content.Intent
import com.example.codebreaker.databinding.ActivityMainmenuBinding
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.TransitionManager

@SuppressLint("CustomSplashScreen")
class Activity_SplashScreen : AppCompatActivity() {

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            val i = Intent(
                this@Activity_SplashScreen,
                Activity_MainMenu::class.java
            )
            // on below line we are
            // starting a new activity.

            startActivity(i)

            // on the below line we are finishing
            // our current activity.
            finish()
        }, 1000)
    }


}

