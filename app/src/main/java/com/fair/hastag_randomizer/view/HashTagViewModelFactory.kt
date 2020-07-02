package com.fair.hastag_randomizer.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fair.hastag_randomizer.repository.RandomizeRepository

class HashTagViewModelFactory(private val repository: RandomizeRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return HashTagViewModel(repository) as T
    }
}