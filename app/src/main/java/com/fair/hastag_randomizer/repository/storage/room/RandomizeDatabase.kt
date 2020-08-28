package com.fair.hastag_randomizer.repository.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [RandomizeEntity::class], version = 1, exportSchema = false)
abstract class RandomizeDatabase: RoomDatabase() {
    abstract fun randomizeDao(): RandomizeDao

    companion object{
        @Volatile private var instance: RoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                )
                    .also{ instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            RandomizeDatabase::class.java, "randomize.db")
            .build()
    }

}


