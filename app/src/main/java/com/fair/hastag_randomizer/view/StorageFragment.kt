package com.fair.hastag_randomizer.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.databinding.FragmentStorageBinding
import com.fair.hastag_randomizer.repository.RandomizeRepository
import com.fair.hastag_randomizer.repository.storage.room.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.room.RandomizeEntity
import com.fair.hastag_randomizer.repository.storage.room.RandomizeRecyclerAdapter


class StorageFragment: Fragment(R.layout.fragment_storage) {

    private var _binding: FragmentStorageBinding? = null
    private val viewBinding get() = _binding!!

    private lateinit var viewModel : HashTagViewModel

    private lateinit var mainAdapter: RandomizeRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStorageBinding.bind(view)

        val db = context?.let {
            RandomizeDatabase(
                it
            )
        }
        val repository = RandomizeRepository(db as RandomizeDatabase)
        val factory = HashTagViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HashTagViewModel::class.java)

        viewBinding.apply {

            storeToolbar.setNavigationIcon(R.drawable.ic_home)
            storeToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_storageFragment_to_mainFragment)
            }

            storageSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                   return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mainAdapter.filter.filter(newText)
                    return false
                }
            })

            viewModel.getAllHashTags().observe(viewLifecycleOwner, Observer {
                mainAdapter = RandomizeRecyclerAdapter(it as ArrayList<RandomizeEntity>)
                savedRecycler.layoutManager = LinearLayoutManager(context)
                savedRecycler.adapter = mainAdapter
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

