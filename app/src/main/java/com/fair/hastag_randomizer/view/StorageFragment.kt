package com.fair.hastag_randomizer.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentStorageBinding
import com.fair.hastag_randomizer.repository.RandomizeRepository
import com.fair.hastag_randomizer.repository.storage.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.RandomizeEntity
import com.fair.hastag_randomizer.repository.storage.RandomizeRecyclerAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class StorageFragment: Fragment(R.layout.fragment_storage) {

   private var _binding: FragmentStorageBinding? = null
   private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStorageBinding.bind(view)

        val db = context?.let { RandomizeDatabase(it) }
        val repository = RandomizeRepository(db as RandomizeDatabase)
        val factory = HashTagViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(HashTagViewModel::class.java)

        viewBinding.apply {
            val adapter = RandomizeRecyclerAdapter(listOf(), viewModel)

            savedRecycler.layoutManager = LinearLayoutManager(context)
            savedRecycler.adapter = adapter

            viewModel.getAllHashTags().observe(viewLifecycleOwner, Observer {
                adapter.hashtag = it
                adapter.notifyDataSetChanged()
            })

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}