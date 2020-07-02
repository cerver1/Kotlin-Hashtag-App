package com.fair.hastag_randomizer.view


import android.content.ClipboardManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import com.fair.hastag_randomizer.databinding.ActivityMainBinding
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.storage.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.SharedQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
     /**
     
         TO_DO_

         Needs README update 

         Needs Radio buttons for user output size selection

         Needs to be modeled after notes app OnePlus
     
     */

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




}
}