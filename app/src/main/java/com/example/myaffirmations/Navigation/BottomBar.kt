@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myaffirmations.Navigation

import android.content.Context
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myaffirmations.JournalScreen.Utils.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottombarNavigation(navController: NavHostController,context: Context) {

    //navigation(navControllerse = navController)

    Scaffold(
        bottomBar = { Bottombar(navController = navController)}
    ) {
        val pad =it
        BottoBarNavitis(navHostController = navController, context = context)

    }

}

@Composable
fun Bottombar(navController:NavHostController) {

    val Screens = listOf(
        BottomBarNav.HomeScreen,
        BottomBarNav.JournalScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navDestination = navBackStackEntry?.destination

    NavigationBar(modifier = Modifier
        .fillMaxWidth()
        .height(74.dp)
        .graphicsLayer {
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            clip = true
        }) {
        Screens.forEach {
            scr->
            AddItem(screen = scr, currentDestination =navDestination , navController =navController )
        }

    }

}


@Composable
fun RowScope.AddItem(
    screen:BottomBarNav,currentDestination:NavDestination?,navController: NavHostController
) {
NavigationBarItem( label = {
                           Text(text = screen.title)
}, icon = {
          Icon(imageVector = screen.icon, contentDescription = screen.title)
}, selected = currentDestination?.hierarchy?.any {
    it.route == screen.route
}==true,
    onClick = {
        navController.navigate(screen.route)
    }
    )
}



