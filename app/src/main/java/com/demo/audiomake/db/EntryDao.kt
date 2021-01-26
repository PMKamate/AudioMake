package com.demo.audiomake.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.audiomake.db.EntryDB

/**
 * DAO inteface to access table methods
 * */
@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: EntryDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entries: List<EntryDB>)

    @Query("SELECT * FROM entry")
    suspend fun getAllEntry(): List<EntryDB>

    @Query("SELECT COUNT(*) from entry")
    suspend fun entryCount(): Int

}