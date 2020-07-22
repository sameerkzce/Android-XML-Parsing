package com.example.sooryenapp.framwork.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sooryenapp.dto.EntryDto

/**
 * DAO inteface to access table methods
 * */
@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: EntryDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entries: List<EntryDto>)

    @Query("SELECT * FROM entry")
    suspend fun getAllEntry(): List<EntryDto>

    @Query("SELECT COUNT(*) from entry")
    suspend fun entryCount(): Int

}