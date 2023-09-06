package com.example.myaffirmations.JournalScreen.Usecase

import com.example.myaffirmations.JournalScreen.DB.Note


interface IAddNotesUsecase {
    suspend fun invoke(notes: Note)
}