package com.fair.hastag_randomizer.view

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fair.hastag_randomizer.databinding.ActivityMainBinding
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.storage.SharedQuery
import com.fair.hastag_randomizer.repository.toast

class MainActivity : AppCompatActivity() {
    
     /**
     
         TO_DO_

         Needs README update 

         Needs Radio buttons for user output size selection

         Needs to be modeled after notes app OnePlus
     
     */

    private lateinit var myClipboard: ClipboardManager
    private lateinit var binding: ActivityMainBinding
    private var rand: Randomize = Randomize()
    private lateinit var saveNew: SharedQuery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveNew = SharedQuery(this)

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

                val validTags = rand.validateHashTagList(textInput.text.toString())
                val randomTags = rand.randomizedHashTagList(validTags,"random")

                textOutput.text = rand.finalizeHashTags(randomTags.second)
                // textOutputSized.text = randomTags.first.toString()

                copyTxt(textOutput)
                toast("Randomized and Copied")
            }
            buttonSave.setOnClickListener {
                    saveNew.searchHash(textInput.text.toString(), textOutputSized )

            }
            buttonSize.setOnClickListener {
                SizeDialog(this@MainActivity).showSizeDialog()
            }
        }
    }

    private fun copyTxt(text: TextView) {
        val myClip = ClipData.newPlainText("NewRandom", text.text)
        myClipboard.setPrimaryClip(myClip!!)
    }

    
}
