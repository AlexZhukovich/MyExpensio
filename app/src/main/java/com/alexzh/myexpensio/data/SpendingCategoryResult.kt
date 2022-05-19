package com.alexzh.myexpensio.data

import java.math.BigDecimal

data class SpendingCategoryResult(
    val balance: BigDecimal,
    val income: BigDecimal,
    val expense: BigDecimal,
    val categories: List<SpendingCategory>
)
