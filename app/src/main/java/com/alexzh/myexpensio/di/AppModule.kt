package com.alexzh.myexpensio.di

import com.alexzh.myexpensio.core.AmountCurrencyFormatter
import com.alexzh.myexpensio.core.DefaultAmountCurrencyFormatter
import com.alexzh.myexpensio.core.icon.DefaultIconMapper
import com.alexzh.myexpensio.core.icon.IconMapper
import com.alexzh.myexpensio.data.DummySpendingCategoryRepository
import com.alexzh.myexpensio.data.SpendingCategoryRepository
import com.alexzh.myexpensio.features.expenses.ExpensesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<SpendingCategoryRepository> { DummySpendingCategoryRepository() }
    factory<AmountCurrencyFormatter> { DefaultAmountCurrencyFormatter() }
    factory<IconMapper> { DefaultIconMapper() }

    viewModel {
        ExpensesViewModel(
            get(),
            get(),
            get()
        )
    }
}