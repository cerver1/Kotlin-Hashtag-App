package com.fair.hastag_randomizer.repository

import com.fair.hastag_randomizer.repository.storage.room.RandomizeDatabase
import com.fair.hastag_randomizer.repository.storage.room.RandomizeEntity

class RandomizeRepository(private val db: RandomizeDatabase
){

    suspend fun update(hashtag: RandomizeEntity) = db.RandomizeDao().update(hashtag)
    suspend fun delete(hashtag: RandomizeEntity) = db.RandomizeDao().delete(hashtag)
    suspend fun insert(hashtag: RandomizeEntity) = db.RandomizeDao().insertAll(hashtag)
    fun getAllHashTags() = db.RandomizeDao().getAll()
}