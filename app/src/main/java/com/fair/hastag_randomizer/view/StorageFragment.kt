package com.fair.hastag_randomizer.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
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

    private lateinit var displayList: MutableList<RandomizeEntity>
    private lateinit var arrayList: ArrayList<RandomizeEntity>

    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    private lateinit var viewModel : HashTagViewModel

    private lateinit var mainAdapter: RandomizeRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

            storeToolbar.inflateMenu(R.menu.storage_menu)
            storeToolbar.setNavigationIcon(R.drawable.ic_home)
            storeToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_storageFragment_to_mainFragment)
            }

            mainAdapter = RandomizeRecyclerAdapter(arrayListOf(), viewModel)

            savedRecycler.layoutManager = LinearLayoutManager(context)
            savedRecycler.adapter = mainAdapter

            viewModel.getAllHashTags().observe(viewLifecycleOwner, {
                displayList = it as MutableList<RandomizeEntity>
                mainAdapter.hashtag = displayList
                mainAdapter.notifyDataSetChanged()

            })

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.storage_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun queryAttempt(searchText: String) {
        viewModel.getAllHashTags().observe(viewLifecycleOwner, {
            it.forEach { item ->
                if (item.hashtag.contains(searchText)) {
                    arrayList.add(item)
                }
            }
            mainAdapter.hashtag = arrayList
            mainAdapter.notifyDataSetChanged()

        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

