package com.fair.hastag_randomizer.repository.storage

import androidx.room.*

@Dao
interface RandomizeDao {

    @Query("SELECT * FROM randomizeentity")
    fun getAll(): List<RandomizeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hashtag: RandomizeEntity)

    @Delete
    suspend fun delete(hashtag: RandomizeEntity)

    @Update
    suspend fun update(hashtag: RandomizeEntity)

}