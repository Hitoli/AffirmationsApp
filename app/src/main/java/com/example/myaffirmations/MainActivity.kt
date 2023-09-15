package com.example.myaffirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myaffirmations.AffirmationsScreen.Screen.AffirmationsScreen
import com.example.myaffirmations.JournalScreen.Screen.NotesScreen
import com.example.myaffirmations.JournalScreen.Screen.addEditNoteScreen
import com.example.myaffirmations.JournalScreen.Utils.Screens
import com.example.myaffirmations.ui.theme.MyAffirmationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAffirmationsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val remembernav = rememberNavController()
                    NavHost(navController = remembernav, startDestination =Screens.Notescreen.route){
                        composable(route = Screens.Notescreen.route){
                            NotesScreen(navController = remembernav){}
                        }
                        composable(route = Screens.AddEditNoteScreen.route + "?noteId ={noteId}&noteColor={notecolor}", arguments = listOf(
                            navArgument(name = "noteId"){
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(name = "noteColor"){
                                type = NavType.IntType
                                defaultValue = -1
                            }

                        )){
                            val color = it.arguments?.getInt("noteColor")?:-1
                            addEditNoteScreen(navController = remembernav, notecolor = color)
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAffirmationsTheme {
        Greeting("Android")
    }
}