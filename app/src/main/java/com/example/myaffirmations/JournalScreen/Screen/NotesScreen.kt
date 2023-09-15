@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myaffirmations.JournalScreen.Screen

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myaffirmations.JournalScreen.Utils.Screens
import com.example.myaffirmations.JournalScreen.Utils.notesEvent
import com.example.myaffirmations.JournalScreen.Viewmodel.noteViewmodel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(viewmodel: noteViewmodel= hiltViewModel(),navController:NavController, onClick:()->Unit) {

    val state = viewmodel.state.value
    val snackbarhoststate = remember{SnackbarHostState()}
    val scope = rememberCoroutineScope()


    Scaffold( modifier = Modifier, snackbarHost = {SnackbarHost(snackbarhoststate)},
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddEditNoteScreen.route) },Modifier.background(MaterialTheme.colorScheme.primary)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "ADD FLOATING BUTTON")
            }
        }

    ) {
        val pad = it
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Your Note", style = MaterialTheme.typography.headlineLarge)
                IconButton(onClick ={ viewmodel.onEvent(notesEvent.ToggleOrderNotesEvent) }) {
                
                    Image(imageVector = Icons.Default.List, contentDescription ="SORTING" )
                }
            }
            AnimatedVisibility(visible = state.isOrderSectionVisible, enter = fadeIn()+ slideInVertically(), exit = fadeOut()+ slideOutVertically()) {
                OrderSection(modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp), onOrderChange ={
                    viewmodel.onEvent(notesEvent.Order(it))
                } )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.notes){
                    notes-> NoteItem(note = notes, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                               Screens.AddEditNoteScreen.route + "?noteId=${notes.id}&noteColor=${notes.color}"
                    }, onDeleteClick = {viewmodel.onEvent(notesEvent.notesDelete(notes))
                    scope.launch {
                        val result = snackbarhoststate.showSnackbar(message = "Notes Deleted", actionLabel = "Undo")
                        if(result == SnackbarResult.ActionPerformed){
                            viewmodel.onEvent(notesEvent.RestoreNote)
                        }
                    }

                    })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}