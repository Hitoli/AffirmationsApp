package com.example.myaffirmations.JournalScreen.Usecase.GetNotes

import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.Utils.noteOrder
import com.example.myaffirmations.JournalScreen.Utils.noteOrderType
import kotlinx.coroutines.flow.Flow

interface IGetNotesUsecase {
    suspend fun invoke(noteorder: noteOrder = noteOrder.Date(noteOrderType.Descending)): Flow<List<Note>>
}