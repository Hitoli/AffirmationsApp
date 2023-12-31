package com.example.myaffirmations.JournalScreen.Usecase.DeleteNotes

import com.example.myaffirmations.JournalScreen.DB.Note
import kotlinx.coroutines.flow.Flow

interface IDeleteNotesUsecase {
    suspend fun invoke(note: Note)
}