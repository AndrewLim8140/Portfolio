package com.example.codebreaker
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.codebreaker.databinding.ActivitySettingBinding

class Activity_Settings : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)

        binding.settingBack.setOnClickListener { back() }
        binding.settingClear.setOnClickListener { clear() }

        setContentView(binding.root)

        super.onCreate(savedInstanceState)
    }

    private fun clear(){
        Toast.makeText(this,"Wiping Scoreboard",  Toast.LENGTH_SHORT).show()
        deleteFile(Activity_FilesManager.FileContents.filePath)
        forcedReset()
    }

    private fun back(){
        val intent = Intent(this, Activity_MainMenu::class.java)
        startActivity(intent)
    }

    private fun forcedReset() {
        val ctx = applicationContext
        val pm = ctx.packageManager
        val intent = pm.getLaunchIntentForPackage(ctx.packageName)
        val mainIntent = Intent.makeRestartActivityTask(intent!!.component)
        ctx.startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }
}

