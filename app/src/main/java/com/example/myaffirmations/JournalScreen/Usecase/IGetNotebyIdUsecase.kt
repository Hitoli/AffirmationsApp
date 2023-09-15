package com.example.myaffirmations.JournalScreen.Usecase

import com.example.myaffirmations.JournalScreen.DB.Note

interface IGetNotebyIdUsecase {
    suspend fun invoke(id:Int): Note?
}