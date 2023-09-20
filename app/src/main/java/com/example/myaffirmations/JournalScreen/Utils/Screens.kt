package com.example.myaffirmations.JournalScreen.Utils

sealed class Screens(val route:String){
    object Notescreen:Screens("Notes_Screeen")
    object addEditNoteScreen:Screens("Add_Edit_Notes_Screen")
}


