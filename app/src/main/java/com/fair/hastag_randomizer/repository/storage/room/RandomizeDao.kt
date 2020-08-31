package com.fair.hastag_randomizer.repository.storage.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RandomizeDao {

    @Query("SELECT * FROM randomizeentity")
    fun getAll(): LiveData<List<RandomizeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hashtag: RandomizeEntity)

    @Delete
    suspend fun delete(hashtag: RandomizeEntity)

}