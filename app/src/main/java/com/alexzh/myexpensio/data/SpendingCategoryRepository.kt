package com.alexzh.myexpensio.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface SpendingCategoryRepository {

    suspend fun fetchSpendingCategories(
        startDate: LocalDate,
        endDate: LocalDate
    ) : Flow<SpendingCategoryResult>
}