package com.example.codebreaker

import androidx.appcompat.app.AppCompatActivity

class Activity_FilesManager : AppCompatActivity() {

    object FileContents {
        var names = mutableListOf<String>()
        var scores = mutableListOf<String>()
        const val entrySplitter = "▓"
        const val nameSplitter = "░"
        var filePath = "highScore.txt"
    }
}

