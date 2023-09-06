package com.example.myaffirmations.JournalScreen.Repository

import com.example.myaffirmations.JournalScreen.DB.Note
import kotlinx.coroutines.flow.Flow

interface IGetNoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(Id:Int):Note?

    suspend fun insertNote(note:Note)

    suspend fun deleteNote(note: Note)

}