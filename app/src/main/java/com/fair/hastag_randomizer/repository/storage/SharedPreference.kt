package com.fair.hastag_randomizer.repository.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

        private val prefName = "HashTagRandomize"
        private val sharedPrefs: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

        fun saveBoolean (KEY_NAME:String, value:Boolean){
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putBoolean(KEY_NAME, value)
            editor.apply()
        }

        fun getBooleanValue(KEY_NAME: String):Boolean? {
            return sharedPrefs.getBoolean(KEY_NAME,false)
        }

        fun saveString(KEY_NAME: String, value: String){
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putString(KEY_NAME, value)
            editor.apply()
        }
        fun getStringValue(KEY_NAME: String):String? {
            return sharedPrefs.getString(KEY_NAME, "")
        }

    }