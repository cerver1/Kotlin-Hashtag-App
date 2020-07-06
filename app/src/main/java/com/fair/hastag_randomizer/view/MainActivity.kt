package com.fair.hastag_randomizer.view


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.fair.hastag_randomizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
     /**
     
      wish_list

      Updated Readme

      Global toolbar for the app(main purpose to give the settings page a toolbar)

      Clean code for better MVVM Arch

      Onboarding of the app

      Search feature in saved Hashtags for easier sorting

      Testing for different use-cases instead of pasting -> {
        Assume the user won't do the right thing what if they paste a hashtag without a hash symbol?
        what if they paste some words with hashtags and others without? }

      Categorized Storage of saved Hashtags(group by similar name, etc)
     
     */

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




}
}