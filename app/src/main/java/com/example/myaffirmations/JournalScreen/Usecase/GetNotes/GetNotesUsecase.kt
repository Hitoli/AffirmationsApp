package com.example.myaffirmations.JournalScreen.Usecase.GetNotes

import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.Repository.IGetNoteRepository
import com.example.myaffirmations.JournalScreen.Utils.noteOrder
import com.example.myaffirmations.JournalScreen.Utils.noteOrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class getNotesUsecase @Inject constructor(val noteRepo:IGetNoteRepository): IGetNotesUsecase {
    override suspend fun invoke(noteorder: noteOrder): Flow<List<Note>> {
        return noteRepo.getNotes().map { Notes->
            when(noteorder.ordertype){
                is noteOrderType.Ascending->{
                    when(noteorder){
                         is noteOrder.Title -> Notes.sortedBy { it.title.lowercase() }
                        is noteOrder.Date -> Notes.sortedBy { it.timeStamp }
                        is noteOrder.Color -> Notes.sortedBy { it.color }
                    }
                }
                is noteOrderType.Descending->{
                    when(noteorder){
                        is noteOrder.Title -> Notes.sortedByDescending { it.title.lowercase() }
                        is noteOrder.Date -> Notes.sortedByDescending { it.timeStamp }
                        is noteOrder.Color -> Notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}