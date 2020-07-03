package com.fair.hastag_randomizer.view


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.fair.hastag_randomizer.databinding.ActivityMainBinding

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