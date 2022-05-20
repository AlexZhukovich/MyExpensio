package com.alexzh.myexpensio.core.icon

import androidx.annotation.DrawableRes
import com.alexzh.myexpensio.R

class DefaultIconMapper : IconMapper {
    private val icons = mapOf(
        "Bills & Utilities" to R.drawable.ic_receipt,
        "Education" to R.drawable.ic_school,
        "Entertainment" to R.drawable.ic_local_activity,
        "Fitness & Sport" to R.drawable.ic_fitness_center,
        "Food & Dining" to R.drawable.ic_ramen_dining,
        "Gifts" to R.drawable.ic_card_giftcard,
        "Groceries" to R.drawable.ic_shopping_bag,
        "Healthcare" to R.drawable.ic_favorite_border,
        "Home" to R.drawable.ic_home,
        "Salary" to R.drawable.ic_paid,
        "Shopping" to R.drawable.ic_shopping_cart,
        "Taxes" to R.drawable.ic_attach_money,
        "Transportation" to R.drawable.ic_commute,
        "Travel" to R.drawable.ic_flight,
        "Uncategorized" to R.drawable.ic_question_mark,
    )

    override fun getIcon(
        name: String,
        @DrawableRes default: Int
    ): Int {
        return icons[name] ?: default
    }
}