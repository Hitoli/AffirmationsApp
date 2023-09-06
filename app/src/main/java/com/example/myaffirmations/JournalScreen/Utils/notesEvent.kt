package com.example.myaffirmations.JournalScreen.Utils

import com.example.myaffirmations.JournalScreen.DB.Note


sealed class notesEvent{
    data class Order(val notesorder:noteOrder):notesEvent()
    data class notesDelete(val notes:Note):notesEvent()
    object RestoreNote:notesEvent()
    object ToggleOrderNotesEvent:notesEvent()
}
