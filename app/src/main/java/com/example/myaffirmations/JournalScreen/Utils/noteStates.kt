package com.example.myaffirmations.JournalScreen.Utils

import com.example.myaffirmations.JournalScreen.DB.Note

data class noteStates(
    val notes: List<Note> = emptyList(),
    val noteOrders:noteOrder = noteOrder.Date(ordertype = noteOrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
