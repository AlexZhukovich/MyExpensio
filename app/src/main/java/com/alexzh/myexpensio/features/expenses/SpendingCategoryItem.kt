package com.alexzh.myexpensio.features.expenses

import androidx.annotation.DrawableRes

data class SpendingCategoryItem(
    val id: Long,
    val name: String,
    val formattedAmount: String,
    @DrawableRes val icon: Int
)
