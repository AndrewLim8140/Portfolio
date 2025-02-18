@file:Suppress("SameParameterValue", "DEPRECATION")

package com.example.passanger

//region imports
import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity

//endregion
class Passanger : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            val i = Intent(
                this@Passanger,
                PassangerApp::class.java
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

