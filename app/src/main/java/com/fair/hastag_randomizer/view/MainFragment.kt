package com.fair.hastag_randomizer.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentMainBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainFragment: Fragment(R.layout.fragment_main) {
    
    private var _binding: FragmentMainBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        viewBinding.apply {

            randomizeBtn.setOnClickListener {
                val myList = listOf("#cerve","#instagram","#kotlin")
                myList.forEach{
                    addChip(it , chipContainer)

                }

            }

        }



    }

    fun addChip(item: String, group: ChipGroup){
        val chip = Chip(context)
        chip.text = item
        chip.chipIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_launcher_foreground)

        chip.isCheckable = false
        group.addView(chip as View)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}