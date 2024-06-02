package com.example.redeemers_faz_com.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Board::class, Key::class], version = 3) // Defines the Room database settings with specified entities and version
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDAO // Abstract method to retrieve BoardDAO
    abstract fun keysDao(): KeysDAO // Abstract method to retrieve KeysDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null // Volatile variable to ensure changes are visible to all threads

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) { // Ensures only one thread can execute this block at a time
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build() // Builds the Room database instance
                INSTANCE = instance // Sets the INSTANCE variable to the newly created database instance
                instance
            }
        }
    }
}
