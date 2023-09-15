package com.example.myaffirmations.JournalScreen.Utils



sealed class noteOrder (val ordertype:noteOrderType){
    class Title(ordertype: noteOrderType):noteOrder(ordertype)
    class Date(ordertype: noteOrderType):noteOrder(ordertype)
    class Color(ordertype: noteOrderType):noteOrder(ordertype)

    fun Copy(ordertype: noteOrderType):noteOrder{
        return when(this){
            is Title ->Title(ordertype)
            is Date-> Date(ordertype)
            is Color ->Color(ordertype)
        }
    }

}
