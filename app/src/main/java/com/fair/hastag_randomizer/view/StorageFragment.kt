package com.fair.hastag_randomizer.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.repository.storage.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.RandomizeEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StorageFragment: Fragment(R.layout.fragment_storage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val db = context?.let { RandomizeDatabase(it) }
        val db = context?.let {
            Room.databaseBuilder(
                it, RandomizeDatabase::class.java,
                "randomize.db"
            ).build()
        }
        /**
        GlobalScope.launch {
        db?.RandomizeDao()?.insertAll(RandomizeEntity(1234,"#cerve"))
        if (db != null) {
        data = db.RandomizeDao().getAll()
        }
        }
        }
        }*/
    }}