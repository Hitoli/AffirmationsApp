package com.example.myaffirmations.AffirmationsScreen.Utils

sealed class ViewStates{
    object Loading:ViewStates()
    class Success(val mutableString: MutableList<String>):ViewStates()
    class Failure(val string: String):ViewStates()
}
