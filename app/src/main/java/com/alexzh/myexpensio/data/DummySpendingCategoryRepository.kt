package com.alexzh.myexpensio.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.math.BigDecimal
import java.time.LocalDate

class DummySpendingCategoryRepository : SpendingCategoryRepository {

    override suspend fun fetchSpendingCategories(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<SpendingCategoryResult> {
        return flowOf(
            SpendingCategoryResult(
                balance = BigDecimal(1_000),
                income = BigDecimal(4_000),
                expense = BigDecimal(3_000),
                categories = listOf(
                    SpendingCategory(
                        id = 1L,
                        name = "Salary",
                        amount = BigDecimal(4_000),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Salary"
                    ),
                    SpendingCategory(
                        id = 2L,
                        name = "Groceries",
                        amount = BigDecimal(1_000),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Groceries"
                    ),
                    SpendingCategory(
                        id = 3L,
                        name = "Transportation",
                        amount = BigDecimal(500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Transportation"
                    ),
                    SpendingCategory(
                        id = 4L,
                        name = "Utilities",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Bills & Utilities"
                    ),
                    SpendingCategory(
                        id = 5L,
                        name = "Education",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Education"
                    ),
                    SpendingCategory(
                        id = 6L,
                        name = "Something",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Uncategorized"
                    ),
                    SpendingCategory(
                        id = 7L,
                        name = "Cinema",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Entertainment"
                    ),
                    SpendingCategory(
                        id = 8L,
                        name = "Fitness & Sport",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Fitness & Sport"
                    ),
                    SpendingCategory(
                        id = 9L,
                        name = "Food & Dining",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Food & Dining"
                    ),
                    SpendingCategory(
                        id = 10L,
                        name = "Gifts",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Gifts"
                    ),
                    SpendingCategory(
                        id = 11L,
                        name = "Healthcare",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Healthcare"
                    ),
                    SpendingCategory(
                        id = 12L,
                        name = "Home",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Home"
                    ),
                    SpendingCategory(
                        id = 13L,
                        name = "Shopping",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Shopping"
                    ),
                    SpendingCategory(
                        id = 14L,
                        name = "Taxes",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Taxes"
                    ),
                    SpendingCategory(
                        id = 14L,
                        name = "Travel",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                        icon = "Travel"
                    ),
                )
            )
        )
    }

}