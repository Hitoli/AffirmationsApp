package com.example.myaffirmations.AffirmationsScreen.Screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myaffirmations.AffirmationsScreen.Response.Links
import com.example.myaffirmations.AffirmationsScreen.Response.Result
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.ViewModel.AffimationsViewModel
import java.net.URL

@Composable
fun AffirmationsScreen (viewmodel:AffimationsViewModel= hiltViewModel(),context: Context) {
  val c = viewmodel._AffirmationStringList.value
   Log.e("QUOTES SCREEN",viewmodel._AffirmationStringList.value)
   Log.e("QUOTES SCREEN_",c)
    var d:URL
    val A:List<Result> = viewmodel._UnsplashImages.value
    Log.e("UNSPLASH IMAGES",A.toString())


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = c)
        Button(onClick = { viewmodel.getAffirmationString(context)}) {
            Text(text = "CALL")
        }

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(d)
                .build()
        )
        Image(painter = painter , contentDescription = null, modifier = Modifier.size(200.dp))



    }


}