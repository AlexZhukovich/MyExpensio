package com.alexzh.myexpensio.features.expenses

data class TotalExpenses(
    val formattedBalance: String,
    val formattedIncome: String,
    val formattedExpense: String,
    val categories: List<SpendingCategoryItem>
)
