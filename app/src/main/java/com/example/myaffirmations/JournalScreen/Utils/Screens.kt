package com.example.myaffirmations.JournalScreen.Utils

sealed class Screens(val route:String){
    object Notescreen:Screens("Notes_Screeen")
    object AddEditNoteScreen:Screens("Add_Edit_Notes_Screen")
}


