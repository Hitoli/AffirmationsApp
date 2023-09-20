package com.example.myaffirmations.JournalScreen.Usecase.GetNotesByID

import com.example.myaffirmations.JournalScreen.DB.Note

interface IGetNotebyIdUsecase {
    suspend fun invoke(id:Int): Note?
}