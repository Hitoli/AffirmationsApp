package com.example.myaffirmations.JournalScreen.Utils

import javax.inject.Inject

sealed class noteOrder @Inject constructor(val ordertype:noteOrderType){
    class Title(ordertype: noteOrderType):noteOrder(ordertype)
    class Date(ordertype: noteOrderType):noteOrder(ordertype)
    class Color(ordertype: noteOrderType):noteOrder(ordertype)

}
