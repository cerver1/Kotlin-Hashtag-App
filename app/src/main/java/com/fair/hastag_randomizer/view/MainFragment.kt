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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentMainBinding
import com.fair.hastag_randomizer.repository.Randomize
import com.fair.hastag_randomizer.repository.RandomizeRepository
import com.fair.hastag_randomizer.repository.snack
import com.fair.hastag_randomizer.repository.storage.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.RandomizeEntity
import com.fair.hastag_randomizer.repository.toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import java.util.*


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

            saveBtn.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_storageFragment)
            }

            randomizeBtn.setOnClickListener {
                val userEntry = userInput.text.toString()
                //view.snack("Your message")
                onCustomSnack(view, userEntry)


                try {
                    rand.apply {

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

    private fun onCustomSnack(view: View, incomingData: String){
        val snackbar = Snackbar.make(view, "save new #hashtags?", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.setAction("Save"){
            val dm = Randomize()
            val db = context?.let { RandomizeDatabase(it) }
            val repository = RandomizeRepository(db as RandomizeDatabase)
            val factory = HashTagViewModelFactory(repository)
            val viewModel = ViewModelProvider(this, factory).get(HashTagViewModel::class.java)

            for(i in dm.newTest(incomingData)){
                viewModel.update(RandomizeEntity(i))
            }


        }
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}