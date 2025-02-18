package com.example.codebreaker
import android.content.Intent
import com.example.codebreaker.databinding.ActivityMainmenuBinding
import android.os.Bundle
//import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class Activity_MainMenu : AppCompatActivity() {

    private lateinit var binding: ActivityMainmenuBinding
//    private lateinit var viewFlipper: ViewFlipper

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainmenuBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        viewFlipper = findViewById(R.id.main_menu)

        super.onCreate(savedInstanceState)

        initMainMenu()

    }

    private fun intentActivity(target : String){
        val i : Intent
        // play intent
        if (target == "Play"){
            i = Intent(this@Activity_MainMenu , Activity_Gameplay::class.java)
        }

        // highscore intent
        else if (target == "Highscore"){
            i = Intent(this@Activity_MainMenu , Activity_Highscore::class.java)
        }

        // setting intent
        else{
            i = Intent(this@Activity_MainMenu , Activity_Settings::class.java)

        }

        startActivity(i)
        finish()
    }

    private fun initMainMenu() {
        binding.menuHighscore.setOnClickListener {intentActivity("Highscore")}
        binding.menuPlay.setOnClickListener {intentActivity("Play")}
        binding.menuSetting.setOnClickListener {intentActivity("Settings")}
    }
}

