@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myaffirmations.JournalScreen.Screen

import android.graphics.drawable.AnimatedImageDrawable
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myaffirmations.JournalScreen.Utils.Screens
import com.example.myaffirmations.JournalScreen.Utils.notesEvent
import com.example.myaffirmations.JournalScreen.Viewmodel.noteViewmodel
import com.example.myaffirmations.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(viewmodel: noteViewmodel= hiltViewModel(),navController:NavController) {

    val state = viewmodel.state.value
    val snackbarhoststate = remember{SnackbarHostState()}
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.background(
        brush = Brush.linearGradient(
            0f to Color(0xffaed2ff).copy(alpha = 0.93f),
            1f to Color(0xff27005d).copy(alpha = 0.90f)
        ))) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.soulup),
                contentDescription = "Logo",
                Modifier
                    .height(70.dp)
                    .width(100.dp)
                    .padding(16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.loginimage),
                contentDescription = "Logo",
                Modifier
                    .height(70.dp)
                    .width(100.dp)
                    .padding(16.dp)
            )

        }


        Scaffold(modifier =  Modifier.background(
            brush = Brush.linearGradient(
                0f to Color(0xffaed2ff).copy(alpha = 0.93f),
                1f to Color(0xff27005d).copy(alpha = 0.90f)
            )), snackbarHost = { SnackbarHost(snackbarhoststate) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screens.addEditNoteScreen.route) },
                    Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(bottom = 65.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "ADD FLOATING BUTTON"
                    )
                }
            }

        ) {
            val pad = it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xffaed2ff).copy(alpha = 0.93f),
                            1f to Color(0xff27005d).copy(alpha = 0.90f)
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color = Color(0xFF9400FF)),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = { viewmodel.onEvent(notesEvent.ToggleOrderNotesEvent) }) {
                        Image(painter = painterResource(id = R.drawable.categorize), contentDescription = "LIST", alignment = Alignment.Center, modifier = Modifier.size(16.dp))
                    }
                }
                Column(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)) {
                    AnimatedVisibility(
                        visible = state.isOrderSectionVisible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Box(
                            modifier = Modifier
                                .width(362.dp)
                                .height(146.dp)
                                .background(
                                    color = Color(0x1A000000),
                                    shape = RoundedCornerShape(size = 32.dp)
                                )
                        ){

                            OrderSection(modifier = Modifier
                                .padding(6.dp),
                                NoteOrder = state.noteOrders,
                                onOrderChange = { Orders ->
                                    viewmodel.onEvent(notesEvent.Order(Orders))
                                })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.notes) { note ->
                        NoteItem(note = note, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screens.addEditNoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            }, onDeleteClick = {
                            viewmodel.onEvent(notesEvent.notesDelete(note))

                            scope.launch {
                                val result = snackbarhoststate.showSnackbar(
                                    message = "Notes Deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
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

}