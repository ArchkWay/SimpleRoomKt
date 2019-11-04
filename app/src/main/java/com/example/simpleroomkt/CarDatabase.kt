package com.example.simpleroomkt

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database

@Database(entities = [CarNote::class], version = 1)
abstract class CarDatabase : RoomDatabase() {

    abstract fun appCarDao(): CarDao
    companion object {
        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "CarDatabase")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
