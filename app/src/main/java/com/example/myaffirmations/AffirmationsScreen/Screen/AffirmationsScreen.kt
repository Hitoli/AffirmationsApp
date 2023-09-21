package com.example.myaffirmations.AffirmationsScreen.Screen

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myaffirmations.AffirmationsScreen.Repository.UnsplashRespository
import com.example.myaffirmations.AffirmationsScreen.Response.Links
import com.example.myaffirmations.AffirmationsScreen.Response.Result
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.ViewModel.AffimationsViewModel
import java.net.URI
import java.net.URL
import java.nio.file.WatchEvent

@Composable
fun AffirmationsScreen (viewmodel:AffimationsViewModel= hiltViewModel(),context: Context) {
    val rememberQuotes = remember{
        viewmodel._AffirmationStringList
    }
    val rememberUnsplashImages = remember{
        viewmodel._UnsplashImages
    }
 Column(modifier = Modifier.padding(6.dp), verticalArrangement = Arrangement.Center, horizontalAlignment =Alignment.CenterHorizontally) {
     ImagesQuotes(rememberQuotes,rememberUnsplashImages)
 }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ImagesQuotes(rememberQuotes:MutableList<String>,rememberUnsplashImages:MutableList<String>) {

    val pagerstate = rememberPagerState()
    val ImagesUnsplash = rememberUnsplashImages
    val matrix = remember{ColorMatrix()}

    Scaffold(modifier = Modifier.padding(vertical = 48.dp)) {
        val pad =it
        HorizontalPager(pageCount =ImagesUnsplash.size, state =pagerstate ){
            index ->
            val pageOffset = (pagerstate.currentPage-index)+pagerstate.currentPageOffsetFraction
            val imageSize by animateFloatAsState(targetValue = if (pageOffset!=0.0f)0.75f else 1f, animationSpec = tween(durationMillis = 300),
                label = "Stoic"
            )
            LaunchedEffect(key1 =Unit ){
                matrix.setToScale(0.4f,0.4f,0.4f,1f)
            }
            Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center){
                AsyncImage(modifier = Modifier
                    .padding(bottom = 35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    },
                    model = ImageRequest.Builder(LocalContext.current).data(ImagesUnsplash.get(index)).build(),
                    contentScale = ContentScale.Crop, contentDescription ="Images", colorFilter = ColorFilter.colorMatrix(matrix))

                Text(text = rememberQuotes.get(index), color = Color.White, modifier = Modifier
                    .padding(16.dp)
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    })
            }
        }
    }
}