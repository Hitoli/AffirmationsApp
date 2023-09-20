package com.example.myaffirmations.JournalScreen.Screen

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myaffirmations.JournalScreen.DB.Note
import com.example.myaffirmations.JournalScreen.Utils.AddEditNoteEvent
import com.example.myaffirmations.JournalScreen.Viewmodel.addEditNoteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addEditNoteScreen(
    navController: NavController, notecolor:Int, addEditNoteViewModel: addEditNoteViewModel = hiltViewModel()
) {
    val titleState = addEditNoteViewModel.noteTitle.value
    val scope = rememberCoroutineScope()
    val contentState = addEditNoteViewModel.noteContent.value
    val snackbarhoststate = remember{ SnackbarHostState() }
    val animatablecolor = remember{
        Animatable(Color(if(notecolor!=-1)notecolor else addEditNoteViewModel.noteColor.value))
    }

    LaunchedEffect(key1 = true){
        addEditNoteViewModel.eventFlow.collectLatest { event->
            when(event){
                is addEditNoteViewModel.UiEvents.ShowSnackbar ->{
                    snackbarhoststate.showSnackbar(message = event.message)
                }
                is addEditNoteViewModel.UiEvents.SaveNote ->{
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                addEditNoteViewModel.onEvent(AddEditNoteEvent.SaveNote)
            },Modifier.background(MaterialTheme.colorScheme.primary).padding(bottom = 65.dp)) {
                Icon(imageVector = Icons.Default.Send, contentDescription ="Save note")

            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(animatablecolor.value)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Note.colorInt.forEach{
                    color->
                    val colorInt = color.toArgb()
                    Box(modifier = Modifier
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color = color)
                        .border(
                            width = 3.dp,
                            color = if (addEditNoteViewModel.noteColor.value == colorInt) {
                                Color.Black
                            } else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            scope.launch {
                                animatablecolor.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(
                                        durationMillis = 500
                                    )
                                )
                            }
                            addEditNoteViewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    addEditNoteViewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    addEditNoteViewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                }, isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    addEditNoteViewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    addEditNoteViewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                }, isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
    
}