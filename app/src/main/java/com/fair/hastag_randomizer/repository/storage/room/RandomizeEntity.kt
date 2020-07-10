package com.fair.hastag_randomizer.repository.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RandomizeEntity (
    @ColumnInfo(name = "hashtag") @PrimaryKey val hashtag: String
)