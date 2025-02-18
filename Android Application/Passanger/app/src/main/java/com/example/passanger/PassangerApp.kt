@file:Suppress("SameParameterValue")

package com.example.passanger

//region imports
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.example.passanger.databinding.ActivityMainBinding
import java.io.IOException
import java.text.NumberFormat

//endregion

class PassangerApp : AppCompatActivity() {
    //region Init/Variables declaration
    private lateinit var binding: ActivityMainBinding
    private var userPassword = ""
    private var newUser = false
    private var filePath = ""
    private var savedPassword = ""
    private var savedDetails = mutableListOf("")

    private var catNum = ""

    private lateinit var viewFlipper: ViewFlipper
    //endregion

    // Upon app launch
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewFlipper = findViewById(R.id.flipperino)

        accessFiles("Login").toString()
        super.onCreate(savedInstanceState)
        initNumpad()

    }

    // Functions for app

    //region Shared functions

    private fun accessFiles(currentScreen: String) {

        if (currentScreen == "Login") {
            filePath = "details.txt"
        } else if (currentScreen == "KMS") {
            filePath = "passanger.txt"
        }
        readFiles(filePath, false)

    }

    @SuppressLint("SetTextI18n")
    private fun readFiles(filePath: String, bypass: Boolean) {
        if (bypass) {
            val content =
                openFileInput(filePath).bufferedReader().use { it.readText() }
            savedPassword = content
        } else {
            try {
                val content =
                    openFileInput(filePath).bufferedReader().use { it.readText() }
                savedPassword = content
                binding.loginPassword.text = "Enter Password"

            } catch (e: IOException) {
                newUser = true

                binding.loginPassword.text = "Create New Password"

                e.printStackTrace()
                Toast.makeText(this, "New User Detected", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun writeFiles(filePath: String, data: String) {

        if (data.isBlank()) {
            Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            openFileOutput(filePath, MODE_PRIVATE).use { fos ->
                fos.write(data.toByteArray())
            }
            binding.loginPassword.text = ""
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {

            e.printStackTrace()
            Toast.makeText(this, "Error has occurred", Toast.LENGTH_SHORT).show()
        }
    }

    // Forcing reset to save changes to device
    private fun forcedReset() {
        val ctx = applicationContext
        val pm = ctx.packageManager
        val intent = pm.getLaunchIntentForPackage(ctx.packageName)
        val mainIntent = Intent.makeRestartActivityTask(intent!!.component)
        ctx.startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }

    //endregion

    //region Login Specific functions
    private fun initNumpad() {
        //<editor-fold desc="numpad init">
        binding.numpad9.setOnClickListener { loginPassword("9") }
        binding.numpad8.setOnClickListener { loginPassword("8") }
        binding.numpad7.setOnClickListener { loginPassword("7") }
        binding.numpad6.setOnClickListener { loginPassword("6") }
        binding.numpad5.setOnClickListener { loginPassword("5") }
        binding.numpad4.setOnClickListener { loginPassword("4") }
        binding.numpad3.setOnClickListener { loginPassword("3") }
        binding.numpad2.setOnClickListener { loginPassword("2") }
        binding.numpad1.setOnClickListener { loginPassword("1") }
        binding.numpad0.setOnClickListener { loginPassword("0") }

        binding.deleteButton.setOnClickListener { passwordBackspace() }

        binding.loginButton.setOnClickListener { loginAccount() }

        // find a way for popup
        binding.loginReset.setOnClickListener { loginReset(binding.loginReset) }
        //</editor-fold>
    }

    private fun passwordBackspace() {
        if (binding.loginPassword.text.length > 1) {
            binding.loginPassword.text = binding.loginPassword.text.dropLast(1)
            userPassword = userPassword.dropLast(1)
        } else if (binding.loginPassword.text.length == 1) {
            binding.loginPassword.text = binding.loginPassword.text.dropLast(1)
            userPassword = userPassword.dropLast(1)
            binding.deleteButton.setAlpha(0f)
            binding.deleteButton.isClickable = false
            binding.deleteButton.isEnabled = false
        }
    }

    private fun loginPassword(loginDigit: String) {
        NumberFormat.getCurrencyInstance()
        userPassword += loginDigit
        binding.loginPassword.text = getString(R.string.loginPassword, userPassword)

        if (binding.loginPassword.text.isNotEmpty()) {
            binding.deleteButton.setAlpha(1.0f)
            binding.deleteButton.isClickable = true
            binding.deleteButton.isEnabled = true
        }
    }

    private fun loginReset(view: View) {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.login_reset)

        popup.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.resetButton -> {
                    Toast.makeText(this@PassangerApp, "Wiping App Data", Toast.LENGTH_SHORT).show()
                    deleteFile("details.txt")
                    checkFiles("wipe")

                    binding.loginPassword.text = ""
                    forcedReset()
                }
            }
            true
        }
        popup.show()
    }

    private fun loginAccount() {
        //region create new acc if new user
        if (newUser) {
            val data = binding.loginPassword.text.toString()
            writeFiles(filePath, data)
            newUser = false
            forcedReset()

        }
        //endregion
        //region login successful
        else if (userPassword == savedPassword) {
            initKMS()
            initPassword()
            viewFlipper.showNext()
        }
        //endregion
        //region login failed
        else {
            Toast.makeText(this@PassangerApp, "Incorrect Password", Toast.LENGTH_SHORT).show()
        }
        //endregion

        userPassword = ""
        binding.loginPassword.text = ""
    }
    //endregion

    //region KMS Specific functions
    // region Main KMS
    private fun initKMS() {

        binding.savedPass1.setOnClickListener { onClick(binding.savedPass1) }
        binding.savedPass2.setOnClickListener { onClick(binding.savedPass2) }
        binding.savedPass3.setOnClickListener { onClick(binding.savedPass3) }
        binding.savedPass4.setOnClickListener { onClick(binding.savedPass4) }
        binding.savedPass5.setOnClickListener { onClick(binding.savedPass5) }

        binding.editPass1.setOnClickListener { editPassword(binding.editPass1) }
        binding.editPass2.setOnClickListener { editPassword(binding.editPass2) }
        binding.editPass3.setOnClickListener { editPassword(binding.editPass3) }
        binding.editPass4.setOnClickListener { editPassword(binding.editPass4) }
        binding.editPass5.setOnClickListener { editPassword(binding.editPass5) }

        checkFiles("check")

    }

    private fun checkFiles(action: String) {

        val files = mutableListOf("")
        files.add("passKey1.txt")
        files.add("passKey2.txt")
        files.add("passKey3.txt")
        files.add("passKey4.txt")
        files.add("passKey5.txt")

        for (file in files) {
            try {
                readFiles(file, true)
                // to check which segment of string belongs to whom
                if (action == "check") {
                    val details = savedPassword.split("%")

                    when (file) {
                        "passKey1.txt" -> binding.savedPass1.text = details[0]
                        "passKey2.txt" -> binding.savedPass2.text = details[0]
                        "passKey3.txt" -> binding.savedPass3.text = details[0]
                        "passKey4.txt" -> binding.savedPass4.text = details[0]
                        "passKey5.txt" -> binding.savedPass5.text = details[0]
                    }
                }

                // to check which files are present for wiping purpose
                if (action == "wipe") {
                    when (file) {
                        "passKey1.txt" -> deleteFile("passKey1.txt")
                        "passKey2.txt" -> deleteFile("passKey2.txt")
                        "passKey3.txt" -> deleteFile("passKey3.txt")
                        "passKey4.txt" -> deleteFile("passKey4.txt")
                        "passKey5.txt" -> deleteFile("passKey5.txt")
                    }
                }

                // to check which files exists [ for edit dropdown detection ]
                if (action == "obtain") {
                    if (file.dropLast(4) == catNum) {
                        savedDetails = savedPassword.split("%").toMutableList()
                    }

                }

            } catch (e: IOException) {
                continue
            }

        }
    }

    private fun onClick(pushedButton: Button) {
        val buttonText = pushedButton.text
        val objID = pushedButton.id.toString()

        when (objID) {
            "2131296665" -> catNum = "passKey1"
            "2131296666" -> catNum = "passKey2"
            "2131296667" -> catNum = "passKey3"
            "2131296668" -> catNum = "passKey4"
            "2131296669" -> catNum = "passKey5"
        }
        //region if password not saved
        if (buttonText == "Create Password") {
            viewFlipper.showNext()
        }
        //endregion
        else {
            checkFiles("obtain")
            // output stored in savedPassword

            binding.displayUsername.text = savedDetails[1]
            binding.displayPassword.text = savedDetails[2]
            viewFlipper.setDisplayedChild(3)

        }
    }

    private fun savePassword(combiUsPw: String) {
        if (catNum.isBlank()) {
            return
        } else {
            val fileName = "$catNum.txt"
            writeFiles(fileName, combiUsPw)
            viewFlipper.showNext()
        }
    }

    private fun editPassword(view: View) {
        val popup = PopupMenu(this, view)
        // obtain pressed button id and assign it to its category grp
        val objID = view.id.toString()
        when (objID) {
            "2131296440" -> catNum = "passKey1"
            "2131296441" -> catNum = "passKey2"
            "2131296442" -> catNum = "passKey3"
            "2131296443" -> catNum = "passKey4"
            "2131296444" -> catNum = "passKey5"
        }

        popup.inflate(R.menu.edit_password)

        popup.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.deletePassword -> {
                    Toast.makeText(this@PassangerApp, item.title, Toast.LENGTH_SHORT).show()
                    val targetFile = "$catNum.txt"
                    deleteFile(targetFile)

                    val defString = "Create Password"
                    when (targetFile) {
                        "passKey1.txt" -> binding.savedPass1.text = defString
                        "passKey2.txt" -> binding.savedPass2.text = defString
                        "passKey3.txt" -> binding.savedPass3.text = defString
                        "passKey4.txt" -> binding.savedPass4.text = defString
                        "passKey5.txt" -> binding.savedPass5.text = defString
                    }

                }

                R.id.editCurrentPassword -> {
                    binding.slotDescriptor.text = null
                    binding.createUser.text = null
                    binding.createPassword.text = null
                    savedDetails.clear()

                    try {
                        checkFiles("obtain")
                        binding.slotDescriptor.setText(savedDetails[0])
                        binding.createUser.setText(savedDetails[1])
                        binding.createPassword.setText(savedDetails[2])
                    } catch (e : Exception){
                        Toast.makeText(this@PassangerApp, "creating new save",
                            Toast.LENGTH_SHORT).show()
                    }
                    viewFlipper.showNext()

                }
            }

            true
        }

        popup.show()
    }

    //endregion
    //region Password Editor
    private fun initPassword() {
        binding.confirmPassword.setOnClickListener { modifyPassword() }
        binding.back.setOnClickListener { viewFlipper.setDisplayedChild(1) }
    }

    private fun modifyPassword() {
        // Acquire button calls via catnum
        if (binding.slotDescriptor.text.isBlank() || binding.createUser.text.isBlank() || binding.createPassword.text.isBlank()) {
            Toast.makeText(this@PassangerApp, "Invalid Input", Toast.LENGTH_LONG).show()
        } else {
            val slotDescriptor = binding.slotDescriptor.text.toString()
            val chosenUser = binding.createUser.text.toString()
            val chosenPass = binding.createPassword.text.toString()
            val combiUserPass = "$slotDescriptor%$chosenUser%$chosenPass"

            when (catNum) {
                "passKey1" -> binding.savedPass1.text = slotDescriptor
                "passKey2" -> binding.savedPass2.text = slotDescriptor
                "passKey3" -> binding.savedPass3.text = slotDescriptor
                "passKey4" -> binding.savedPass4.text = slotDescriptor
                "passKey5" -> binding.savedPass5.text = slotDescriptor
            }

            deleteFile("$catNum.txt")


            //region Reset text field after confirmation
            binding.slotDescriptor.text = null
            binding.createUser.text = null
            binding.createPassword.text = null
            //endregion

            savePassword(combiUserPass)
            viewFlipper.setDisplayedChild(1)
        }
    }
    //endregion
    //endregion

}

