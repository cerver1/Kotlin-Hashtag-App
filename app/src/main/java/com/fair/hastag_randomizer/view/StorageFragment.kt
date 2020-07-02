package com.fair.hastag_randomizer.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.repository.RandomizeRepository
import com.fair.hastag_randomizer.repository.storage.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.RandomizeEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class StorageFragment: Fragment(R.layout.fragment_storage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = context?.let { RandomizeDatabase(it) }
        val repository = RandomizeRepository(db as RandomizeDatabase)
        val factory = HashTagViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(HashTagViewModel::class.java)

    }
}