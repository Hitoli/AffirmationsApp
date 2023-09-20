package com.example.myaffirmations.JournalScreen.Usecase.DeleteNotes

import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.Repository.IGetNoteRepository
import javax.inject.Inject

class deleteNotesUsecase @Inject constructor(val noterepo:IGetNoteRepository): IDeleteNotesUsecase {
    override suspend fun invoke(note: Note)  {
        noterepo.deleteNote(note =note )
    }
}