package com.alexzh.myexpensio.data

import androidx.compose.ui.graphics.vector.ImageVector
import java.math.BigDecimal

data class Expense(
    val icon: ImageVector,
    val name: String,
    val category: String,
    val amount: BigDecimal,
    val date: String
)
