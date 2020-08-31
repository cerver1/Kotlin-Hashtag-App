package com.fair.hastag_randomizer.view

import androidx.lifecycle.ViewModel
import com.fair.hastag_randomizer.repository.RandomizeRepository
import com.fair.hastag_randomizer.repository.storage.room.RandomizeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HashTagViewModel(private val repository: RandomizeRepository): ViewModel() {

    fun insert(hashtag: RandomizeEntity) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(hashtag)
    }
    fun delete(hashtag: RandomizeEntity) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(hashtag)
    }
    fun getAllHashTags() = repository.getAllHashTags()




}