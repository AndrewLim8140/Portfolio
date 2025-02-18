package com.example.codebreaker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.codebreaker.Activity_FilesManager.FileContents
import com.example.codebreaker.databinding.ActivitySethighscoreBinding
import java.io.IOException


class ActivityPopup : AppCompatActivity() {

    private lateinit var binding: ActivitySethighscoreBinding
    private var popupTitle = ""
    private var popupText = ""
    private var popupButton = ""
    private lateinit var tempStorage : String

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {
        tempStorage = ""
        binding = ActivitySethighscoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        popupTitle = bundle?.getString("popuptitle", "Title") ?: ""
        popupText = bundle?.getString("popuptext", "Text") ?: ""
        popupButton = bundle?.getString("popupbtn", "Button") ?: ""

        binding.createHighscoreButton.setOnClickListener { submit() }
        binding.createHighscoreScore.text = Activity_Gameplay.UserScoreHandler.userScore.toString()
    }

    private fun submit(){


        if (binding.createHighscoreScore.text.isBlank()){
            Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show()
        }else{
            save(binding.createHighscoreName.text.toString(),Activity_Gameplay.UserScoreHandler.userScore.toString())
            val intent = Intent(this, Activity_MainMenu::class.java)
            startActivity(intent)}
    }

    private fun readFiles() {
        val tempEntry: List<String>
        try {
            tempStorage = openFileInput(FileContents.filePath).bufferedReader().use { it.readText() }
            tempEntry = tempStorage.split(FileContents.entrySplitter)
            for (entry in tempEntry) {
                println(entry)
                val details = entry.split(FileContents.nameSplitter)
                print(details.isEmpty())
                if (details.isNotEmpty()) {
                    FileContents.names.add(details[0])
                    FileContents.scores.add(details[1])}
            }

        }catch(e: IOException){
            openFileOutput(FileContents.filePath, MODE_PRIVATE).use { fos ->
                fos.write(tempStorage.toByteArray())

            }
        }catch(e: java.lang.IndexOutOfBoundsException){
            FileContents.names.clear()
            FileContents.scores.clear()
        }
    }

    private fun save(name : String, score : String) {
        Toast.makeText(this,"Saving...",  Toast.LENGTH_SHORT).show()
        FileContents.names.clear()
        FileContents.scores.clear()
        readFiles()

        var count = 0
        var combined = ""
        // handling existence of file
        FileContents.names.add(name)
        FileContents.scores.add(score)


        println(FileContents.names +"< NAme   Score>"+ FileContents.scores)
        println(FileContents.names +"< UName   UScore>$score")

        // iterate through saved entries

        for (i in FileContents.names){
            combined += if (FileContents.names.count() - count == 1) {
                FileContents.names[count] + FileContents.nameSplitter + FileContents.scores[count]
            } else {
                FileContents.names[count] + FileContents.nameSplitter + FileContents.scores[count] + FileContents.entrySplitter
            }
                count += 1
        }

        openFileOutput(FileContents.filePath, MODE_PRIVATE).use { fos ->
            fos.write(combined.toByteArray())
        }
        readFiles()
        combined = ""

    }
}

