package com.example.myaffirmations.JournalScreen.Screen

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myaffirmations.JournalScreen.Utils.noteOrder
import com.example.myaffirmations.JournalScreen.Utils.noteOrderType

@Composable
fun OrderSection(
    modifier: Modifier,
    NoteOrder:noteOrder = (noteOrder.Date(ordertype = noteOrderType.Descending)),
    onOrderChange:(noteOrder)->Unit
) {
    Column(modifier = modifier) {
        Row(modifier = modifier.fillMaxSize()) {
            DefaultRadioButton(text = "Title", checked =NoteOrder is noteOrder.Title , onCheck = { onOrderChange(noteOrder.Title(NoteOrder.ordertype)) })
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(text = "Date", checked =NoteOrder is noteOrder.Date , onCheck = { onOrderChange(noteOrder.Title(NoteOrder.ordertype)) })
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(text = "Color", checked =NoteOrder is noteOrder.Color , onCheck = { onOrderChange(noteOrder.Title(NoteOrder.ordertype)) })
            Spacer(modifier = modifier.width(8.dp))
        }
        Spacer(modifier = modifier.width(16.dp))
        Row(modifier) {
            DefaultRadioButton(text = "Ascending", checked =NoteOrder.ordertype is noteOrderType.Ascending , onCheck = { onOrderChange(NoteOrder.Copy(noteOrderType.Ascending)) })
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(text = "Descending", checked =NoteOrder.ordertype is noteOrderType.Descending , onCheck = { onOrderChange((NoteOrder.Copy(noteOrderType.Descending))) })
            Spacer(modifier = modifier.width(8.dp))
        }
    }

}