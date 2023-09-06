package com.example.myaffirmations.JournalScreen.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :Id")
    suspend fun getNoteByID(Id:Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}