package com.alexzh.myexpensio.features.expenses

sealed class ExpensesScreenState {
    object Loading: ExpensesScreenState()
    data class Success(val data: TotalExpenses) : ExpensesScreenState()
    object Empty: ExpensesScreenState()
    object Error: ExpensesScreenState()
}
