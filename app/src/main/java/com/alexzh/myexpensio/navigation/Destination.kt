package com.alexzh.myexpensio.navigation

sealed class Destination(val route: String) {
    object Expenses : Destination("expenses")
    object AddExpense : Destination("add-expense")
    object SelectCategory : Destination("select-category")
}