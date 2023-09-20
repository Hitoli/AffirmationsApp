package com.example.myaffirmations.JournalScreen.Usecase

import com.example.myaffirmations.JournalScreen.Usecase.DeleteNotes.deleteNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.GetNotes.getNotesUsecase

data class noteUsecase(
    val getNotesUsecase: getNotesUsecase, val deleteNotesUsecase: deleteNotesUsecase
)
