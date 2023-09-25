package com.example.myaffirmations.AffirmationsScreen.Screen

import LoadingScreen
import android.content.Context
import android.util.Log
import android.view.View
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myaffirmations.AffirmationsScreen.Repository.UnsplashRespository
import com.example.myaffirmations.AffirmationsScreen.Response.Links
import com.example.myaffirmations.AffirmationsScreen.Response.Result
import com.example.myaffirmations.AffirmationsScreen.Response.UnsplashResponse
import com.example.myaffirmations.AffirmationsScreen.Utils.FailureScreen
import com.example.myaffirmations.AffirmationsScreen.Utils.ViewQuotesStates
import com.example.myaffirmations.AffirmationsScreen.Utils.ViewStates
import com.example.myaffirmations.AffirmationsScreen.ViewModel.AffimationsViewModel
import com.example.myaffirmations.JournalScreen.Utils.Screens
import com.example.myaffirmations.JournalScreen.Utils.notesEvent
import com.example.myaffirmations.R
import com.example.myaffirmations.ui.theme.backgroundCharcoal
import kotlinx.coroutines.launch
import java.net.URI
import java.net.URL
import java.nio.file.WatchEvent
import javax.annotation.meta.When

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AffirmationsScreen (viewmodel:AffimationsViewModel= hiltViewModel(), context: Context) {
    val matrix = remember{ColorMatrix()}
    val pagerstate = rememberPagerState()
    LaunchedEffect(key1 =Unit ){
        matrix.setToScale(0.4f,0.4f,0.4f,1f)
    }
    val rememberQuotes by remember{
        viewmodel.AffirmationStringList
    }

    val rememberUnsplashImages by remember{
        viewmodel.UnsplashImages
    }


   when(val state = rememberUnsplashImages){
       is ViewStates.Success ->{
           when(val quotesStae = rememberQuotes){
               is ViewQuotesStates.Success->{
                   val rememberUnsplashimg:MutableList<String> = state.mutableString.toMutableList()
                   val rememberQuotes:MutableList<String> = quotesStae.mutableString.toMutableList()
                   ImagesQuotes(rememberQuotes = rememberQuotes, rememberUnsplashimg =rememberUnsplashimg,matrix,pagerstate)
               }

               else -> {}
           }


       }
       is ViewStates.Failure -> FailureScreen()
       is ViewStates.Loading -> LoadingScreen()
       else -> {}
   }




}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

@Composable
fun ImagesQuotes(rememberQuotes:MutableList<String>,rememberUnsplashimg:MutableList<String>,matrix: ColorMatrix,pagerstate: PagerState) {

    Column(modifier = Modifier.background(
        brush = Brush.linearGradient(
            0f to Color(0xffaed2ff).copy(alpha = 0.93f),
            1f to Color(0xff27005d).copy(alpha = 0.90f)
        ))) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(painter = painterResource(id = R.drawable.soulup), contentDescription ="Logo",
                Modifier
                    .height(70.dp)
                    .width(100.dp)
                    .padding(16.dp))
            Image(painter = painterResource(id = R.drawable.loginimage), contentDescription ="Logo",
                Modifier
                    .height(70.dp)
                    .width(100.dp)
                    .padding(16.dp))

        }
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)

        ){
            Scaffold(modifier = Modifier.background(Color(0xFF9400FF))
                .fillMaxSize(), floatingActionButton = {
                FloatingActionButton(
                    onClick = {  },
                    Modifier
                        .padding(bottom = 105.dp), shape = CircleShape, containerColor = Color(0xFF9400FF)
                ) {
                    Image(painter = painterResource(id = R.drawable.heart), contentDescription = "Heart", alignment = Alignment.Center,
                        modifier = Modifier.size(24.dp))

                }
            }
            ) {
                val pad =it



                HorizontalPager(pageCount =rememberUnsplashimg.size, state =pagerstate, modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xffaed2ff).copy(alpha = 0.93f),
                            1f to Color(0xff27005d).copy(alpha = 0.90f)
                        )
                    )
                    .align(Alignment.Center)){
                        index ->

                    val pageOffset = (pagerstate.currentPage-index)+pagerstate.currentPageOffsetFraction
                    val imageSize by animateFloatAsState(targetValue = if (pageOffset!=0.0f)0.75f else 1f, animationSpec = tween(durationMillis = 300),
                        label = "Stoic"
                    )


                    Box(modifier = Modifier.padding(10.dp), contentAlignment = Alignment.Center){

                        AsyncImage(modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 95.dp)
                            .clip(RoundedCornerShape(36.dp))
                            .graphicsLayer {
                                scaleX = imageSize
                                scaleY = imageSize
                            },
                            model = ImageRequest.Builder(LocalContext.current).data(rememberUnsplashimg.get(index)).build(),
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


    }



}