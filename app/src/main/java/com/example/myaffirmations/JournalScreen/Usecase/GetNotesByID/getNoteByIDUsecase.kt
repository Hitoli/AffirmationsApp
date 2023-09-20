package com.example.myaffirmations.JournalScreen.Usecase.GetNotesByID

import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.Repository.noteRepository
import javax.inject.Inject

class IGetNotesByIDUsecase @Inject constructor(val repoIgetNotebyID:noteRepository):
    IGetNotebyIdUsecase {
    override suspend fun invoke(id: Int): Note? {
       return repoIgetNotebyID.getNoteById(id)
    }

}