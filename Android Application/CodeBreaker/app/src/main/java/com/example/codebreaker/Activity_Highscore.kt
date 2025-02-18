package com.example.codebreaker
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.codebreaker.Activity_FilesManager.FileContents
import com.example.codebreaker.databinding.ActivityHighscoreBinding
import java.io.IOException

class Activity_Highscore : AppCompatActivity() {

    private lateinit var binding: ActivityHighscoreBinding

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {
        FileContents.names = mutableListOf()
        FileContents.scores = mutableListOf()
        readFiles()

        binding = ActivityHighscoreBinding.inflate(layoutInflater)
        binding.highscoreBack.setOnClickListener { back() }
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        initHighScoreContainer()

    }

    private fun back(){
        val intent = Intent(this, Activity_MainMenu::class.java)
        startActivity(intent)
    }

    private fun readFiles() {
        val tempEntry: List<String>

        var tempStorage = ""
        try {
            tempStorage =
                openFileInput(FileContents.filePath).bufferedReader().use { it.readText() }
            tempEntry = tempStorage.split(FileContents.entrySplitter)
            for (entry in tempEntry) {
                println(entry)
                val details = entry.split(FileContents.nameSplitter)
                println(details)
                    if (details.isEmpty()){println("pop")}
                    FileContents.names.add(details[0])
                    FileContents.scores.add(details[1])}


        } catch (e: IOException) {
            println("File not found")
            openFileOutput(FileContents.filePath, MODE_PRIVATE).use { fos ->
                fos.write(tempStorage.toByteArray())

            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            Toast.makeText(this,"No Highscore Detected",  Toast.LENGTH_SHORT).show()
        }
    }

    private fun initHighScoreContainer() {
        var count = 1
        if (FileContents.names.isNotEmpty()) {
            try {
                for (name in FileContents.names) {
                    val highscoreText =
                        FileContents.names[count - 1] + " [" + FileContents.scores[count - 1] + "]"
                    when (count) {
                        1 -> binding.highscore1.text = highscoreText
                        2 -> binding.highscore2.text = highscoreText
                        3 -> binding.highscore3.text = highscoreText
                        4 -> binding.highscore4.text = highscoreText
                        5 -> binding.highscore5.text = highscoreText
                        6 -> binding.highscore6.text = highscoreText
                        7 -> binding.highscore7.text = highscoreText
                        8 -> binding.highscore8.text = highscoreText
                    }
                    count += 1
                }

                //        Setting button's visiblity
                if (binding.highscore1.text.isNotBlank()) {
                    binding.highscore1.visibility = View.VISIBLE
                    binding.highscore1.isEnabled = true
                } else {
                    binding.highscore1.visibility = View.INVISIBLE
                    binding.highscore1.isEnabled = false
                }
                if (binding.highscore2.text.isNotBlank()) {
                    binding.highscore2.visibility = View.VISIBLE
                    binding.highscore2.isEnabled = true
                } else {
                    binding.highscore2.visibility = View.INVISIBLE
                    binding.highscore2.isEnabled = false
                }
                if (binding.highscore3.text.isNotBlank()) {
                    binding.highscore3.visibility = View.VISIBLE
                    binding.highscore3.isEnabled = true
                } else {
                    binding.highscore3.visibility = View.INVISIBLE
                    binding.highscore3.isEnabled = false
                }
                if (binding.highscore4.text.isNotBlank()) {
                    binding.highscore4.visibility = View.VISIBLE
                    binding.highscore4.isEnabled = true
                } else {
                    binding.highscore4.visibility = View.INVISIBLE
                    binding.highscore4.isEnabled = false
                }
                if (binding.highscore5.text.isNotBlank()) {
                    binding.highscore5.visibility = View.VISIBLE
                    binding.highscore5.isEnabled = true
                } else {
                    binding.highscore5.visibility = View.INVISIBLE
                    binding.highscore5.isEnabled = false
                }
                if (binding.highscore6.text.isNotBlank()) {
                    binding.highscore6.visibility = View.VISIBLE
                    binding.highscore6.isEnabled = true
                } else {
                    binding.highscore6.visibility = View.INVISIBLE
                    binding.highscore6.isEnabled = false
                }
                if (binding.highscore7.text.isNotBlank()) {
                    binding.highscore7.visibility = View.VISIBLE
                    binding.highscore7.isEnabled = true
                } else {
                    binding.highscore7.visibility = View.INVISIBLE
                    binding.highscore7.isEnabled = false
                }
                if (binding.highscore8.text.isNotBlank()) {
                    binding.highscore8.visibility = View.VISIBLE
                    binding.highscore8.isEnabled = true
                } else {
                    binding.highscore8.visibility = View.INVISIBLE
                    binding.highscore8.isEnabled = false
                }
            } catch (e : java.lang.IndexOutOfBoundsException){
                Toast.makeText(this,"No Highscore Detected",  Toast.LENGTH_SHORT).show()
            }

        }
    }
}

