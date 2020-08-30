package com.fair.hastag_randomizer.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.fair.hastag_randomizer.R

class SettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)
    }

}