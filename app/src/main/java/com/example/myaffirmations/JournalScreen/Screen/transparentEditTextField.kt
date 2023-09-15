package com.example.myaffirmations.JournalScreen.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentHintTextField(
    text:String, modifier: Modifier= Modifier, hint:String, isHintVisible:Boolean=true, onValueChange:(String)->Unit, textStyle:TextStyle= TextStyle(),
    onFocusChange:(FocusState)->Unit, singleLine:Boolean= false) {

    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange = onValueChange, textStyle = textStyle, singleLine= singleLine, modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { onFocusChange(it) })

        if(isHintVisible){
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
    
}