package com.example.myaffirmations.JournalScreen.DB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myaffirmations.ui.theme.BlueMunsell
import com.example.myaffirmations.ui.theme.Emerald
import com.example.myaffirmations.ui.theme.Fandango
import com.example.myaffirmations.ui.theme.Maize
import com.example.myaffirmations.ui.theme.Sandybrown

@Entity
data class Note(
    @PrimaryKey
    val id:Int?=null,
    val title:String,
    val content:String,
    val timeStamp:String,
    val color:Int
){
    companion object{
        val colorInt = listOf(Sandybrown, Maize, Emerald, BlueMunsell, Fandango)
    }
}

class invalidNotesException(message:String):Exception(message)