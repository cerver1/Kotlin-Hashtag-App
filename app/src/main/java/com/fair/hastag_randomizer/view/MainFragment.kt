package com.fair.hastag_randomizer.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentMainBinding
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.snack
import com.fair.hastag_randomizer.repository.toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar


class MainFragment: Fragment(R.layout.fragment_main) {
    
    private var _binding: FragmentMainBinding? = null
    private val viewBinding get() = _binding!!
    private var rand: Randomize = Randomize()
    private lateinit var myClipboard: ClipboardManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        _binding = FragmentMainBinding.bind(view)
        myClipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        viewBinding.apply {

            mainToolbar.inflateMenu(R.menu.menu_view)
            mainToolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_settings -> {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_mainFragment_to_settingsFragment)
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
            }

            randomizeBtn.setOnClickListener {

                //view.snack("Your message")
                onCustomSnack(view)


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

    private fun onCustomSnack(view: View){
        val snackbar = Snackbar.make(view, "save new #hashtags?", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.setAction("Save"){

        }
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}