package com.example.codebreaker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.paris.extensions.style
import com.example.codebreaker.databinding.ActivityGamefieldBinding
import kotlin.properties.Delegates
import kotlin.random.Random

class Activity_Gameplay : AppCompatActivity() {
    private lateinit var masterCombination : MutableList<String>
    private lateinit var binding: ActivityGamefieldBinding

    // var for gameplay
    private var currentGuess by Delegates.notNull<Int>()
    private var  currentTries by Delegates.notNull<Int>()
    private lateinit var combination : MutableList<String>
    private var colorCountRed = 0
    private var colorCountGreen = 0
    private var colorCountBlue = 0
    private var colorCountYellow = 0

    // var for scoring
    private var pointWin = 4
    private var pointLose = 0
    private var pointSemi = 2
    private var scoreTriesMax = 5
    private var scoreTriesMultiplier = 1

    object UserScoreHandler {
        var userScore = 0
    }

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGamefieldBinding.inflate(layoutInflater)
        masterCombination = ArrayList()
        combination = ArrayList()
        currentGuess = 1
        currentTries = 1

        setContentView(binding.root)
        setCombination()

        super.onCreate(savedInstanceState)

        initButtons()
    }

    private fun initButtons(){
        binding.gameplayButtonRed.setOnClickListener { userGuess("red") }
        binding.gameplayButtonGreen.setOnClickListener { userGuess("green") }
        binding.gameplayButtonBlue.setOnClickListener { userGuess("blue") }
        binding.gameplayButtonYellow.setOnClickListener { userGuess("yellow") }
        binding.gameplayButtonGrey.setOnClickListener { deleteGuess() }

    }

    private fun userGuess(color : String) {
        when (currentTries) {

            1 -> when (currentGuess) {
                1 -> when (color) {
                    "red" -> binding.gameplayDisplayR1C1.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR1C1.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR1C1.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR1C1.style(R.style.gameplay_yellow)
                }

                2 -> when (color) {
                    "red" -> binding.gameplayDisplayR1C2.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR1C2.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR1C2.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR1C2.style(R.style.gameplay_yellow)
                }

                3 -> when (color) {
                    "red" -> binding.gameplayDisplayR1C3.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR1C3.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR1C3.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR1C3.style(R.style.gameplay_yellow)
                }

                4 -> when (color) {
                    "red" -> binding.gameplayDisplayR1C4.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR1C4.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR1C4.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR1C4.style(R.style.gameplay_yellow)
                }

                5 -> when (color) {
                    "red" -> binding.gameplayDisplayR1C5.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR1C5.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR1C5.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR1C5.style(R.style.gameplay_yellow)
                }
            }

            2 -> when (currentGuess) {
                1 -> when (color) {
                    "red" -> binding.gameplayDisplayR2C1.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR2C1.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR2C1.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR2C1.style(R.style.gameplay_yellow)
                }

                2 -> when (color) {
                    "red" -> binding.gameplayDisplayR2C2.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR2C2.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR2C2.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR2C2.style(R.style.gameplay_yellow)
                }

                3 -> when (color) {
                    "red" -> binding.gameplayDisplayR2C3.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR2C3.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR2C3.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR2C3.style(R.style.gameplay_yellow)
                }

                4 -> when (color) {
                    "red" -> binding.gameplayDisplayR2C4.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR2C4.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR2C4.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR2C4.style(R.style.gameplay_yellow)
                }

                5 -> when (color) {
                    "red" -> binding.gameplayDisplayR2C5.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR2C5.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR2C5.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR2C5.style(R.style.gameplay_yellow)
                }
            }

            3 -> when (currentGuess) {
                1 -> when (color) {
                    "red" -> binding.gameplayDisplayR3C1.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR3C1.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR3C1.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR3C1.style(R.style.gameplay_yellow)
                }

                2 -> when (color) {
                    "red" -> binding.gameplayDisplayR3C2.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR3C2.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR3C2.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR3C2.style(R.style.gameplay_yellow)
                }

                3 -> when (color) {
                    "red" -> binding.gameplayDisplayR3C3.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR3C3.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR3C3.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR3C3.style(R.style.gameplay_yellow)
                }

                4 -> when (color) {
                    "red" -> binding.gameplayDisplayR3C4.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR3C4.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR3C4.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR3C4.style(R.style.gameplay_yellow)
                }

                5 -> when (color) {
                    "red" -> binding.gameplayDisplayR3C5.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR3C5.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR3C5.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR3C5.style(R.style.gameplay_yellow)
                }
            }

            4 -> when (currentGuess) {
                1 -> when (color) {
                    "red" -> binding.gameplayDisplayR4C1.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR4C1.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR4C1.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR4C1.style(R.style.gameplay_yellow)
                }

                2 -> when (color) {
                    "red" -> binding.gameplayDisplayR4C2.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR4C2.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR4C2.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR4C2.style(R.style.gameplay_yellow)
                }

                3 -> when (color) {
                    "red" -> binding.gameplayDisplayR4C3.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR4C3.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR4C3.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR4C3.style(R.style.gameplay_yellow)
                }

                4 -> when (color) {
                    "red" -> binding.gameplayDisplayR4C4.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR4C4.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR4C4.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR4C4.style(R.style.gameplay_yellow)
                }

                5 -> when (color) {
                    "red" -> binding.gameplayDisplayR4C5.style(R.style.gameplay_red)
                    "green" -> binding.gameplayDisplayR4C5.style(R.style.gameplay_green)
                    "blue" -> binding.gameplayDisplayR4C5.style(R.style.gameplay_blue)
                    "yellow" -> binding.gameplayDisplayR4C5.style(R.style.gameplay_yellow)
                }
            }
        }


        currentGuess += 1
        combination.add(color)

        if (currentGuess > 5) {
            if (!checkGuess()) {
                if (currentTries < 4){
                    currentTries += 1
                    currentGuess = 1
                }
                else{
                    Toast.makeText(this,"GameOver Nice Attempt",  Toast.LENGTH_SHORT).show()

                    displayPopup()
                }
            }
            else{
                Toast.makeText(this,"Correct Combination Found! Congrats",  Toast.LENGTH_SHORT).show()
                displayPopup()
            }
            combination.clear()

        }


    }

    private fun displayPopup(){
        val intent = Intent(this, ActivityPopup::class.java)
        startActivity(intent)
    }

    private  fun checkGuess(): Boolean {
        println("master$masterCombination")
        println("combi$combination")
        var userNum = 0
        var masterNum = 0
        var correctFlag = 0
        var semiFlag = 0
        var skipFlag = false
        var focusedColor = 0
        val checkedNumber = mutableListOf<Int>()
        var userRedCount = colorCountRed
        var userBlueCount = colorCountBlue
        var userGreenCount = colorCountGreen
        var userYellowCount = colorCountYellow
        val correctGuess = mutableListOf<Int>()
        val semiGuess = mutableListOf<Int>()
        val wrongGuess = mutableListOf<Int>()

//        preamble check
        for (i in combination){
            if (masterCombination[userNum] == combination[userNum]){
                correctGuess.add(userNum+1)
                checkedNumber.add(userNum+1)
                when (i) {
                    "red" -> userRedCount -= 1
                    "green" -> userGreenCount -= 1
                    "blue" -> userBlueCount -= 1
                    "yellow" -> userYellowCount -= 1
                }
            }
            userNum += 1
        }
        userNum = 0

        for (userColor in combination) {
            when (userColor) {
                "red" -> focusedColor = userRedCount
                "green" -> focusedColor = userGreenCount
                "blue" -> focusedColor = userBlueCount
                "yellow" -> focusedColor = userYellowCount
            }
            userNum += 1

            for (masterColor in masterCombination) {

                masterNum += 1

                if(!skipFlag) {
                    if( ((userColor == masterColor) && masterNum == userNum)&& (!checkedNumber.contains(masterNum))) {
                        correctFlag += 1
                        skipFlag = true
                        checkedNumber.add(masterNum)
                        when (userColor) {
                            "red" -> userRedCount -= 1
                            "green" -> userGreenCount -= 1
                            "blue" -> userBlueCount -= 1
                            "yellow" -> userYellowCount -= 1
                        }
                    } else if ((userColor == masterColor && focusedColor >= 1) && (!checkedNumber.contains(masterNum))){
                        semiFlag += 1
                        skipFlag = true
                        checkedNumber.add(masterNum)
                        when (userColor) {
                            "red" -> userRedCount -= 1
                            "green" -> userGreenCount -= 1
                            "blue" -> userBlueCount -= 1
                            "yellow" -> userYellowCount -= 1
                        }
                    }

                    if (correctFlag > 0) {
                        correctGuess.add(userNum)
                        correctFlag = 0
                    }
                    else if (semiFlag > 0) {
                        if (!correctGuess.contains(userNum)){semiGuess.add(userNum)}

                        semiFlag = 0
                        correctFlag = 0
                    }

                }
            }
            masterNum = 0
            skipFlag = false
        }
        userNum = 0
        for (number : String in combination){
            userNum += 1
            if (!(correctGuess.contains(userNum)||(semiGuess.contains(userNum)))){
                wrongGuess.add(userNum)
            }
        }


        for (correctNum in correctGuess){
            if (correctNum == 1) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C1.style(R.style.gameplay_green)
                    2 -> binding.gameplayCheckR2C1.style(R.style.gameplay_green)
                    3 -> binding.gameplayCheckR3C1.style(R.style.gameplay_green)
                    4 -> binding.gameplayCheckR4C1.style(R.style.gameplay_green)
                }
            }
            if (correctNum == 2) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C2.style(R.style.gameplay_green)
                    2 -> binding.gameplayCheckR2C2.style(R.style.gameplay_green)
                    3 -> binding.gameplayCheckR3C2.style(R.style.gameplay_green)
                    4 -> binding.gameplayCheckR4C2.style(R.style.gameplay_green)
                }
            }
            if (correctNum == 3) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C3.style(R.style.gameplay_green)
                    2 -> binding.gameplayCheckR2C3.style(R.style.gameplay_green)
                    3 -> binding.gameplayCheckR3C3.style(R.style.gameplay_green)
                    4 -> binding.gameplayCheckR4C3.style(R.style.gameplay_green)
                }
            }
            if (correctNum == 4) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C4.style(R.style.gameplay_green)
                    2 -> binding.gameplayCheckR2C4.style(R.style.gameplay_green)
                    3 -> binding.gameplayCheckR3C4.style(R.style.gameplay_green)
                    4 -> binding.gameplayCheckR4C4.style(R.style.gameplay_green)
                }
            }
            if (correctNum == 5) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C5.style(R.style.gameplay_green)
                    2 -> binding.gameplayCheckR2C5.style(R.style.gameplay_green)
                    3 -> binding.gameplayCheckR3C5.style(R.style.gameplay_green)
                    4 -> binding.gameplayCheckR4C5.style(R.style.gameplay_green)
                }
            }
        }

        for (semiNum in semiGuess){
            if (semiNum == 1) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C1.style(R.style.gameplay_yellow)
                    2 -> binding.gameplayCheckR2C1.style(R.style.gameplay_yellow)
                    3 -> binding.gameplayCheckR3C1.style(R.style.gameplay_yellow)
                    4 -> binding.gameplayCheckR4C1.style(R.style.gameplay_yellow)
                }
            }
            if (semiNum == 2) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C2.style(R.style.gameplay_yellow)
                    2 -> binding.gameplayCheckR2C2.style(R.style.gameplay_yellow)
                    3 -> binding.gameplayCheckR3C2.style(R.style.gameplay_yellow)
                    4 -> binding.gameplayCheckR4C2.style(R.style.gameplay_yellow)
                }
            }
            if (semiNum == 3) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C3.style(R.style.gameplay_yellow)
                    2 -> binding.gameplayCheckR2C3.style(R.style.gameplay_yellow)
                    3 -> binding.gameplayCheckR3C3.style(R.style.gameplay_yellow)
                    4 -> binding.gameplayCheckR4C3.style(R.style.gameplay_yellow)
                }
            }
            if (semiNum == 4) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C4.style(R.style.gameplay_yellow)
                    2 -> binding.gameplayCheckR2C4.style(R.style.gameplay_yellow)
                    3 -> binding.gameplayCheckR3C4.style(R.style.gameplay_yellow)
                    4 -> binding.gameplayCheckR4C4.style(R.style.gameplay_yellow)
                }
            }
            if (semiNum == 5) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C5.style(R.style.gameplay_yellow)
                    2 -> binding.gameplayCheckR2C5.style(R.style.gameplay_yellow)
                    3 -> binding.gameplayCheckR3C5.style(R.style.gameplay_yellow)
                    4 -> binding.gameplayCheckR4C5.style(R.style.gameplay_yellow)
                }
            }
        }

        for (wrongNum in wrongGuess){
            if (wrongNum == 1) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C1.style(R.style.gameplay_red)
                    2 -> binding.gameplayCheckR2C1.style(R.style.gameplay_red)
                    3 -> binding.gameplayCheckR3C1.style(R.style.gameplay_red)
                    4 -> binding.gameplayCheckR4C1.style(R.style.gameplay_red)
                }
            }
            if (wrongNum == 2) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C2.style(R.style.gameplay_red)
                    2 -> binding.gameplayCheckR2C2.style(R.style.gameplay_red)
                    3 -> binding.gameplayCheckR3C2.style(R.style.gameplay_red)
                    4 -> binding.gameplayCheckR4C2.style(R.style.gameplay_red)
                }
            }
            if (wrongNum == 3) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C3.style(R.style.gameplay_red)
                    2 -> binding.gameplayCheckR2C3.style(R.style.gameplay_red)
                    3 -> binding.gameplayCheckR3C3.style(R.style.gameplay_red)
                    4 -> binding.gameplayCheckR4C3.style(R.style.gameplay_red)
                }
            }
            if (wrongNum == 4) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C4.style(R.style.gameplay_red)
                    2 -> binding.gameplayCheckR2C4.style(R.style.gameplay_red)
                    3 -> binding.gameplayCheckR3C4.style(R.style.gameplay_red)
                    4 -> binding.gameplayCheckR4C4.style(R.style.gameplay_red)
                }
            }
            if (wrongNum == 5) {
                when (currentTries){
                    1 -> binding.gameplayCheckR1C5.style(R.style.gameplay_red)
                    2 -> binding.gameplayCheckR2C5.style(R.style.gameplay_red)
                    3 -> binding.gameplayCheckR3C5.style(R.style.gameplay_red)
                    4 -> binding.gameplayCheckR4C5.style(R.style.gameplay_red)
                }
            }
        }
        println("correct$correctGuess")
        println("semi$semiGuess")
        println("wrong$wrongGuess")

        UserScoreHandler.userScore = (pointWin * correctGuess.count() + pointLose * wrongGuess.count() + pointSemi * semiGuess.count())
        UserScoreHandler.userScore *= (scoreTriesMax - (currentTries / scoreTriesMultiplier))

        if (correctGuess.count() >= 5){
            correctGuess.clear()
            semiGuess.clear()
            wrongGuess.clear()
            return true
        }
        else{
            correctGuess.clear()
            semiGuess.clear()
            wrongGuess.clear()
            return false
        }

    }

    private fun setCombination(){
        var count = 0
        var color = ""

        while (count != 5){
            val colorID = Random.nextInt(1, 4)
            when (colorID){
                1 -> color = "red"
                2 -> color = "green"
                3 -> color = "blue"
                4 -> color = "yellow"

            }
            masterCombination.add(color)
            when (colorID){
                1 -> colorCountRed += 1
                2 -> colorCountGreen += 1
                3 -> colorCountBlue += 1
                4 -> colorCountYellow += 1
            }
            count += 1
        }
    }

    private fun deleteGuess(){
        if (combination.isNotEmpty()) {
            when (currentTries) {

                1 -> when (currentGuess-1) {
                    1 -> binding.gameplayDisplayR1C1.style(R.style.gameplay_grey)

                    2 -> binding.gameplayDisplayR1C2.style(R.style.gameplay_grey)

                    3 -> binding.gameplayDisplayR1C3.style(R.style.gameplay_grey)

                    4 -> binding.gameplayDisplayR1C4.style(R.style.gameplay_grey)

                    5 -> binding.gameplayDisplayR1C5.style(R.style.gameplay_grey)
                }

                2 -> when (currentGuess-1) {
                    1 -> binding.gameplayDisplayR2C1.style(R.style.gameplay_grey)

                    2 -> binding.gameplayDisplayR2C2.style(R.style.gameplay_grey)

                    3 -> binding.gameplayDisplayR2C3.style(R.style.gameplay_grey)

                    4 -> binding.gameplayDisplayR2C4.style(R.style.gameplay_grey)

                    5 -> binding.gameplayDisplayR2C5.style(R.style.gameplay_grey)
                }

                3 -> when (currentGuess-1) {
                    1 -> binding.gameplayDisplayR3C1.style(R.style.gameplay_grey)

                    2 -> binding.gameplayDisplayR3C2.style(R.style.gameplay_grey)

                    3 -> binding.gameplayDisplayR3C3.style(R.style.gameplay_grey)

                    4 -> binding.gameplayDisplayR3C4.style(R.style.gameplay_grey)

                    5 -> binding.gameplayDisplayR3C5.style(R.style.gameplay_grey)
                }

                4 -> when (currentGuess-1) {
                    1 -> binding.gameplayDisplayR4C1.style(R.style.gameplay_grey)

                    2 -> binding.gameplayDisplayR4C2.style(R.style.gameplay_grey)

                    3 -> binding.gameplayDisplayR4C3.style(R.style.gameplay_grey)

                    4 -> binding.gameplayDisplayR4C4.style(R.style.gameplay_grey)

                    5 -> binding.gameplayDisplayR4C5.style(R.style.gameplay_grey)
                }
            }
            currentGuess -= 1
            combination.removeAt(currentGuess -1)

            if (currentGuess <= 1) {combination.clear()}
        }else{Toast.makeText(this,"Combination Empty, Unable To Delete",  Toast.LENGTH_SHORT).show()
        }
    }
}