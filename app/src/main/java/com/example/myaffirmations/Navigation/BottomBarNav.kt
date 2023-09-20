package com.example.myaffirmations.Navigation

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNav(var route:String, var title:String, var icon:ImageVector){

    object HomeScreen:BottomBarNav(route = "Home", title = "Home", icon = Icons.Default.Home)

    object JournalScreen:BottomBarNav(route = "Journal", title = "Journal", icon = Icons.Default.Create)
}
