package com.fair.hastag_randomizer

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fair.hastag_randomizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var myClipboard: ClipboardManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.TYPE_STATUS_BAR
            )
        }

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        with(binding) {
            buttonClear.setOnClickListener { textInput.setText("") }
            buttonRandom.setOnClickListener {
                val listMain = (textInput.text)!!.removePrefix("#").replace("\\s".toRegex(), "")
                    .split("#").sorted().reversed().shuffled().distinct().take(29).toString()
                    .trim().replace("[", "#").replace(",", "#")
                    .replace("\\s".toRegex(), "").removeSuffix("]")

                val listMain2 = (textInput.text)!!.removePrefix("#").replace("\\s".toRegex(), "")
                    .split("#").sorted().reversed().shuffled().distinct().take(29).size.toString()

                textOutput.text = listMain
                textOutputSized.text = listMain2

                copyTxt(textOutput)
                Toast.makeText(this@MainActivity, "Randomized and Copied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun copyTxt(text: TextView) {
        val myClip = ClipData.newPlainText("NewRandom", text.text)
        myClipboard?.setPrimaryClip(myClip!!)
    }
}