package com.alexzh.myexpensio.di

import com.alexzh.myexpensio.core.AmountCurrencyFormatter
import com.alexzh.myexpensio.core.DefaultAmountCurrencyFormatter
import com.alexzh.myexpensio.data.DummySpendingCategoryRepository
import com.alexzh.myexpensio.data.SpendingCategoryRepository
import com.alexzh.myexpensio.features.expenses.ExpensesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<SpendingCategoryRepository> {
        DummySpendingCategoryRepository()
    }
    factory<AmountCurrencyFormatter> {
        DefaultAmountCurrencyFormatter()
    }
    viewModel {
        ExpensesViewModel(
            get(),
            get()
        )
    }
}