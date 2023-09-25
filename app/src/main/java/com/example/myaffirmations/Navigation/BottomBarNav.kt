package com.example.myaffirmations.Navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.myaffirmations.R

sealed class BottomBarNav(var route:String, var title:String, var icon:ImageVector){

    object HomeScreen:BottomBarNav(route = "Home", title = "Home", icon = Icons.Rounded.Home)

    object JournalScreen:BottomBarNav(route = "Journal", title = "Journal", icon = Icons.Rounded.Create)
}
