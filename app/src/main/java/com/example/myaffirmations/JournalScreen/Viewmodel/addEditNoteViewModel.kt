package com.example.myaffirmations.JournalScreen.Viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.DB.invalidNotesException
import com.example.myaffirmations.JournalScreen.Usecase.IGetNotebyIdUsecase
import com.example.myaffirmations.JournalScreen.Usecase.addNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.noteUsecase
import com.example.myaffirmations.JournalScreen.Utils.AddEditNoteEvent
import com.example.myaffirmations.JournalScreen.Utils.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class addEditNoteViewModel @Inject constructor(val Igetnotebyid:IGetNotebyIdUsecase, val addnotes:addNotesUsecase, savedStateHandle: SavedStateHandle):ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter Title..."
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter Content..."
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.colorInt.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId:Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let {noteId->
            if(noteId!=-1){
                viewModelScope.launch {
                    Igetnotebyid.invoke(noteId)?.also {note->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }

        }
    }

    fun onEvent(event:AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.EnteredTitle->{
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus->{
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent->{
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeColor->{
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote ->{
                viewModelScope.launch {
                    try{
                        addnotes.invoke( Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
                            timeStamp = System.currentTimeMillis().toString(),
                            color = noteColor.value,
                            id = currentNoteId
                        ))
                        _eventFlow.emit(UiEvents.SaveNote)
                    }catch (e:invalidNotesException){
                        _eventFlow.emit(
                            UiEvents.ShowSnackbar(
                                message = e.message?:"Couldn't Save Note"
                            )
                        )

                    }
                }
            }

            else -> {}
        }
    }

    sealed class UiEvents{
        data class ShowSnackbar(val message:String):UiEvents()
        object SaveNote:UiEvents()
    }

}