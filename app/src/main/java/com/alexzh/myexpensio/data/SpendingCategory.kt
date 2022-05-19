package com.alexzh.myexpensio.data

import java.math.BigDecimal

data class SpendingCategory(
    val id: Long,
    val name: String,
    val amount: BigDecimal,
    val categoryType: SpendingCategoryType
)
