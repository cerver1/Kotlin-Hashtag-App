package com.fair.hastag_randomizer

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var myClipboard: ClipboardManager? = null
    private  var myClip:ClipData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val windowView = window
        windowView.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            WindowManager.LayoutParams.TYPE_STATUS_BAR
        )

         myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?


        val randomize = findViewById<MaterialButton>(R.id.randomBtn)
        val clear = findViewById<MaterialButton>(R.id.clearBtn)
        val display = findViewById<TextView>(R.id.outputText)
        val displaySize = findViewById<TextView>(R.id.outputTextSized)
        val input = findViewById<TextInputEditText>(R.id.inputText)


        clear.setOnClickListener { input.setText("") }
        randomize.setOnClickListener {

        val listMain= (input.text)!!.removePrefix("#").replace("\\s".toRegex(),"")
            .split("#").sorted().reversed().shuffled().distinct().take(29).toString()
            .trim().replace("[","#").replace(",","#")
            .replace("\\s".toRegex(),"").removeSuffix("]")

            val listMain2= (input.text)!!.removePrefix("#").replace("\\s".toRegex(),"")
                .split("#").sorted().reversed().shuffled().distinct().take(29).size.toString()

            display.text = listMain
            displaySize.text = listMain2

            copyTxt(display)

            Toast.makeText(this, "Randomized and Copied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyTxt(text: TextView){
        myClip = ClipData.newPlainText("NewRandom", text.text)
        myClipboard?.setPrimaryClip(myClip!!)
    }


}