package com.fair.hastag_randomizer.view

import android.os.Bundle
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
import java.util.*
import kotlin.collections.ArrayList


class StorageFragment: Fragment(R.layout.fragment_storage) {

    private var _binding: FragmentStorageBinding? = null
    private val viewBinding get() = _binding!!
    private lateinit var displayList: ArrayList<RandomizeEntity>
    private lateinit var arrayList: ArrayList<RandomizeEntity>

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
        val viewModel = ViewModelProvider(this, factory).get(HashTagViewModel::class.java)

        viewBinding.apply {


            storeToolbar.inflateMenu(R.menu.menu_parent)
            storeToolbar.setNavigationIcon(R.drawable.ic_home)
            storeToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_storageFragment_to_mainFragment)
            }

            storeToolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_search -> {
                        (item as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(p0: String?): Boolean {
                                return true
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                if (newText.isNullOrEmpty()) {
                                    displayList.clear()
                                    val search = newText?.toLowerCase(Locale.ROOT)
                                    arrayList.forEach { obj ->

                                            if (obj.hashtag.toLowerCase(Locale.ROOT).contains(search!!)) {
                                                displayList.add(obj)
                                            }
                                    }

                                    savedRecycler.adapter?.notifyDataSetChanged()

                                }
                                else {
                                    displayList.clear()
                                    displayList.addAll(arrayList)
                                    savedRecycler.adapter?.notifyDataSetChanged()
                                }
                                return true
                            }
                        })
                        true }
                    else -> super.onOptionsItemSelected(item)
                }
            }

            val adapter = RandomizeRecyclerAdapter(arrayListOf(), viewModel)

            savedRecycler.layoutManager = LinearLayoutManager(context)
            savedRecycler.adapter = adapter

            viewModel.getAllHashTags().observe(viewLifecycleOwner, Observer {
                adapter.hashtag = it as ArrayList<RandomizeEntity>
                displayList = it
                adapter.notifyDataSetChanged()

            })

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

