package com.fair.hastag_randomizer.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentMainBinding
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class MainFragment: Fragment(R.layout.fragment_main) {
    
    private var _binding: FragmentMainBinding? = null
    private val viewBinding get() = _binding!!
    private var rand: Randomize = Randomize()
    private lateinit var myClipboard: ClipboardManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        myClipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        viewBinding.apply {

            toSettingPage.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_settingsFragment)
            }

            randomizeBtn.setOnClickListener {

                try {
                    rand.apply {
                        val userEntry = userInput.text.toString()
                        val hashTagList = randomizedHashTagList(validateHashTagList(userEntry), "full")
                        hashTagList.third.forEach {
                            addChip(it , chipContainer)
                        }
                        if(userInput.isVisible){
                            chipGroupContainer.visibility = View.VISIBLE
                            userInput.visibility = View.GONE
                        }

                        try {
                            copy(finalizeHashTags(hashTagList.second))
                        } catch(e: Exception) {
                            context.toast("unable to copy hashtags")}




                    }
                }catch(e: Exception) {
                    context.toast("Randomized and copied")}

            }

        }



    }

    private fun addChip(item: String, group: ChipGroup){
        val chip = Chip(context)
        chip.text = item
        chip.isCheckable = false
        group.addView(chip as View)
    }

    private fun copy(text: String) {
        val myClip = ClipData.newPlainText("NewRandom", text)
        myClipboard.setPrimaryClip(myClip!!)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_view, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_settings -> {
                view?.let { Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_settingsFragment) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}