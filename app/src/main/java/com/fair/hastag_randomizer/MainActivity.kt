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
                
                val validTags = validateHashTagList(textInput.text)
                val randomTags = randomizedHashTagList(validTags,"random")

                textOutput.text = finalizeHashTags(randomTags.second)
                textOutputSized.text = randomTags.first
                
                copyTxt(textOutput)
                Toast.makeText(this@MainActivity, "Randomized and Copied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun copyTxt(text: TextView) {
        val myClip = ClipData.newPlainText("NewRandom", text.text)
        myClipboard.setPrimaryClip(myClip!!)
    }
    

private fun finalizeHashTags(randomizedHashTags: String): String{

    return randomizedHashTags.replace("\\s".toRegex(), "").replace("[", "#").replace(",", "#")
        .replace("\\s".toRegex(), "").removeSuffix("]")

}

private fun validateHashTagList(hashTags: String): MutableList<String>{

    val withSet = hashTags.split("#").toSet().toMutableList()
    withSet.remove("")

    return withSet
}


private fun randomizedHashTagList(list: MutableList<String>, size: String ): Pair<Int,String> {

    val randomizedList = list.sorted().reversed().shuffled()

    val adjustSize = if (randomizedList.size >= 30) 30 else randomizedList.size
    
    val random = Random.nextInt(0, 30).toString().toInt()
    
    // radio button group for to select size
    return when(size) {
        "small" -> Pair((adjustSize / 4), randomizedList.take(adjustSize / 4).toString())
        "medium" -> Pair((adjustSize / 2), randomizedList.take( adjustSize / 2).toString())
        "full" -> Pair(adjustSize, randomizedList.take(adjustSize).toString())
        "random" -> Pair(random, randomizedList.take(random).toString())
        else -> Pair(0, "Something went wrong :(")
    }

}
    
}
