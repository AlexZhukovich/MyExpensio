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
                        name = "salary",
                        amount = BigDecimal(4_000),
                        categoryType = SpendingCategoryType.INCOME,
                    ),
                    SpendingCategory(
                        id = 2L,
                        name = "groceries",
                        amount = BigDecimal(1_000),
                        categoryType = SpendingCategoryType.INCOME,
                    ),
                    SpendingCategory(
                        id = 3L,
                        name = "transportation",
                        amount = BigDecimal(500),
                        categoryType = SpendingCategoryType.INCOME,
                    ),
                    SpendingCategory(
                        id = 4L,
                        name = "utilities",
                        amount = BigDecimal(1_500),
                        categoryType = SpendingCategoryType.INCOME,
                    ),
                )
            )
        )
    }

}