package com.fair.hastag_randomizer.repository.storage.shared

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreference(context: Context?) {

    private val defaultPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveR(value: String){
        val editor: SharedPreferences.Editor = defaultPreferences.edit()
        editor.putString("ReturnSize", value)
        editor.apply()
    }

    val get = { KEY_NAME: String -> defaultPreferences.getString(KEY_NAME, "")}
}