package com.demo.audiomake.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/**
 * application database class
 * */
@Database(entities = [EntryDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DBNAME = "sooryenapp"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DBNAME
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

    abstract fun entryDao(): EntryDao
}