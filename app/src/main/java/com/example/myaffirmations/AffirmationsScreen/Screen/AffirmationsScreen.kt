package com.example.myaffirmations.AffirmationsScreen.Screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myaffirmations.AffirmationsScreen.ViewModel.AffimationsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun AffirmationsScreen (viewmodel:AffimationsViewModel= hiltViewModel(),context: Context) {
  val c = viewmodel._AffirmationStringList.value
   Log.e("QUOTES SCREEN",viewmodel._AffirmationStringList.value)
   Log.e("QUOTES SCREEN_",c)

    Column {
        Text(text = c)
        Button(onClick = { viewmodel.buildingNotification(context)}) {
        Text(text = "ShowNotification")
        }
    }


}