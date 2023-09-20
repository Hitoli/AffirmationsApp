package com.example.myaffirmations.Navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(context: Context) {
    val navControllers = rememberNavController()
    BottombarNavigation(navController = navControllers, context = context)
}