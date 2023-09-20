package com.example.myaffirmations.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myaffirmations.AffirmationsScreen.Screen.AffirmationsScreen
import com.example.myaffirmations.JournalScreen.Screen.NotesScreen
import com.example.myaffirmations.JournalScreen.Screen.addEditNoteScreen
import com.example.myaffirmations.JournalScreen.Utils.Screens
@Composable
fun NavGraphBuilder.navigation(navControllerse: NavHostController){

    NavHost(navControllerse, startDestination = Screens.Notescreen.route){
        composable(route = Screens.Notescreen.route){
            NotesScreen(navController = navControllerse)
        }
        composable(
            route = Screens.addEditNoteScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ){
            val color = it.arguments?.getInt("noteColor")?:-1
            addEditNoteScreen(navController = navControllerse, notecolor = color)
        }
    }
}