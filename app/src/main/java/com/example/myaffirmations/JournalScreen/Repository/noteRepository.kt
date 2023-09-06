package com.example.myaffirmations.JournalScreen.Repository

import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.DB.NoteDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class noteRepository @Inject constructor(val noteDAO: NoteDAO):IGetNoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDAO.getNotes()
    }

    override suspend fun getNoteById(Id: Int): Note? {
        return noteDAO.getNoteByID(Id)
    }

    override suspend fun insertNote(note: Note) {
        return noteDAO.InsertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDAO.deleteNote(note)
    }
}