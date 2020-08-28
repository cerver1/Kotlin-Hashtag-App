package com.fair.hastag_randomizer.repository

import com.fair.hastag_randomizer.repository.storage.room.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.room.RandomizeEntity

class RandomizeRepository(private val db: RandomizeDatabase){

    suspend fun update(hashtag: RandomizeEntity) = db.randomizeDao().update(hashtag)
    suspend fun delete(hashtag: RandomizeEntity) = db.randomizeDao().delete(hashtag)
    suspend fun insert(hashtag: RandomizeEntity) = db.randomizeDao().insertAll(hashtag)
    fun getAllHashTags() = db.randomizeDao().getAll()
}