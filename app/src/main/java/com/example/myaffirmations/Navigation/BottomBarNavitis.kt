package com.example.myaffirmations.Navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.myaffirmations.AffirmationsScreen.Screen.AffirmationsScreen
import com.example.myaffirmations.JournalScreen.Screen.NotesScreen
import com.example.myaffirmations.JournalScreen.Screen.addEditNoteScreen
import com.example.myaffirmations.JournalScreen.Utils.Screens

@Composable
fun BottoBarNavitis(navHostController: NavHostController,context: Context) {

    NavHost(navHostController, startDestination = BottomBarNav.HomeScreen.route) {
        composable(route = BottomBarNav.JournalScreen.route) {
            NotesScreen(navController = navHostController)
        }
        composable(route = BottomBarNav.HomeScreen.route) {
            AffirmationsScreen(context = context)
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
            addEditNoteScreen(navController = navHostController, notecolor = color)
        }
    }
}
 //fun NavGraphBuilder.navigationHostJournal(navHostController: NavHostController){
   //  navigation(route = BottomBarNav.JournalScreen.route, startDestination = BottomBarNav.JournalScreen.route){

     //}
 //}