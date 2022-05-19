package com.alexzh.myexpensio.features.expenses

import androidx.compose.ui.graphics.vector.ImageVector

data class SpendingCategoryItem(
    val id: Long,
    val name: String,
    val formattedAmount: String,
    val icon: ImageVector
)
