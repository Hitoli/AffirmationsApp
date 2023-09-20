package com.example.myaffirmations.JournalScreen.Usecase.AddNotes

import com.example.myaffirmations.JournalScreen.DB.Note


interface IAddNotesUsecase {
    suspend fun invoke(notes: Note)
}