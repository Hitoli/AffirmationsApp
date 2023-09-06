package com.example.myaffirmations.JournalScreen.Usecase

import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.DB.invalidNotesException
import com.example.myaffirmations.JournalScreen.Repository.noteRepository
import com.example.myaffirmations.JournalScreen.Utils.notesEvent
import javax.inject.Inject

class addNotesUsecase @Inject constructor(val repo:noteRepository):IAddNotesUsecase {
    override suspend fun invoke(notes: Note) {
        @Throws(invalidNotesException::class)
        if(notes.title.isBlank()){
            throw invalidNotesException("Note's Title Cannot Be Empty.")
        }
        @Throws(invalidNotesException::class)
        if(notes.content.isBlank()){
            throw invalidNotesException("Note's Content Cannot Be Empty")
        }
        repo.insertNote(notes)

    }
}