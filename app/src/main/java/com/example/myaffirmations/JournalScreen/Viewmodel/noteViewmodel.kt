package com.example.myaffirmations.JournalScreen.Viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.Usecase.IAddNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.IDeleteNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.IGetNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.noteUsecase
import com.example.myaffirmations.JournalScreen.Utils.noteOrder
import com.example.myaffirmations.JournalScreen.Utils.noteOrderType
import com.example.myaffirmations.JournalScreen.Utils.noteStates
import com.example.myaffirmations.JournalScreen.Utils.notesEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class noteViewmodel @Inject constructor( val getusecase:IGetNotesUsecase, val deleteusecase:IDeleteNotesUsecase, val addnotesusecase:IAddNotesUsecase):ViewModel() {

    private val _states = mutableStateOf(noteStates())
    val state: State<noteStates> = _states

    private var notesjob: Job?=null

    private var notesRececnt:Note?=null

    init {
        viewModelScope.launch {
            getNotes(noteOrder.Date(noteOrderType.Descending))
        }
    }

    fun onEvent(events: notesEvent){
        when(events){
            is notesEvent.Order->{

                if(state.value.noteOrders::class == events.notesorder::class && state.value.noteOrders.ordertype== events.notesorder.ordertype){
                    return
                }
                viewModelScope.launch {
                    getNotes(events.notesorder)
                }
            }
            is notesEvent.notesDelete->{
                viewModelScope.launch {
                    deleteusecase.invoke(events.notes)
                    notesRececnt = events.notes
                }
            }
            is notesEvent.RestoreNote->{
                viewModelScope.launch{
                    addnotesusecase.invoke(notesRececnt?:return@launch)
                    notesRececnt = null
                }
            }
            is notesEvent.ToggleOrderNotesEvent->{
                _states.value =state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private suspend fun getNotes(notesorder: noteOrder) {

        notesjob?.cancel()
        notesjob = getusecase.invoke(notesorder).onEach {
            notes -> _states.value = state.value.copy(notes= notes, noteOrders = notesorder)
        }.launchIn(viewModelScope)

    }
}