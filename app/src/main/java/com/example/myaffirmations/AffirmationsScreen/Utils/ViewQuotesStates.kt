package com.example.myaffirmations.AffirmationsScreen.Utils

sealed class ViewQuotesStates {

    object Loading:ViewQuotesStates()
    class Success(val mutableString: MutableList<String>):ViewQuotesStates()
    class Failure(val string: String):ViewQuotesStates()
}