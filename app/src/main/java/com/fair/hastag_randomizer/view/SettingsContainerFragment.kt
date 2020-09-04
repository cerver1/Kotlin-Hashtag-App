package com.fair.hastag_randomizer.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentSettingsContainerBinding

class SettingsContainerFragment: Fragment(R.layout.fragment_settings_container) {

    private var _binding : FragmentSettingsContainerBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsContainerBinding.bind(view)


        viewBinding.apply {

            mainSettingToolbar.setNavigationIcon(R.drawable.ic_home)
            mainSettingToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_settingsContainerFragment_to_mainFragment)
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.content, SettingsFragment() )
                .commit()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}