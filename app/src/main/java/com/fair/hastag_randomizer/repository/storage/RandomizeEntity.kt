package com.fair.hastag_randomizer.repository.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RandomizeEntity (
    @PrimaryKey(autoGenerate = true)var id: Int,
    @ColumnInfo(name = "hashtag") val hashtag: String
)