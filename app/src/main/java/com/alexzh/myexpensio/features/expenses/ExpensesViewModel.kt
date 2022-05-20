package com.alexzh.myexpensio.features.expenses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.myexpensio.R
import com.alexzh.myexpensio.core.AmountCurrencyFormatter
import com.alexzh.myexpensio.core.icon.IconMapper
import com.alexzh.myexpensio.data.SpendingCategoryRepository
import com.alexzh.myexpensio.data.SpendingCategoryType
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpensesViewModel(
    private val repository: SpendingCategoryRepository,
    private val currencyFormatter: AmountCurrencyFormatter,
    private val iconMapper: IconMapper
) : ViewModel() {
    // TODO: FIX WITH SETTINGS
    private val currencyCode = "EUR"

    private val _uiState= mutableStateOf<ExpensesScreenState>(ExpensesScreenState.Loading)
    val uiState: State<ExpensesScreenState> = _uiState

    fun fetchSpendingCategories() {
        viewModelScope.launch {
            _uiState.value = ExpensesScreenState.Loading

            try {
                repository.fetchSpendingCategories(LocalDate.now(), LocalDate.now()).collect {
                    if (it.categories.isEmpty()) {
                        _uiState.value = ExpensesScreenState.Empty
                    } else {
                        val totalExpenses = TotalExpenses(
                            formattedBalance = currencyFormatter.format(it.balance.toDouble(), currencyCode),
                            formattedIncome = currencyFormatter.format(it.income.toDouble(), currencyCode),
                            formattedExpense = currencyFormatter.format(it.expense.toDouble(), currencyCode),
                            categories = it.categories.map { category ->
                                SpendingCategoryItem(
                                    id = category.id,
                                    name = category.name,
                                    formattedAmount = currencyFormatter.format(
                                        (if (category.categoryType == SpendingCategoryType.EXPENSE) category.amount.negate() else category.amount).toDouble(),
                                        currencyCode
                                    ),
                                    icon = iconMapper.getIcon(category.icon, R.drawable.ic_question_mark)
                                )
                            }
                        )
                        _uiState.value = ExpensesScreenState.Success(
                            totalExpenses
                        )
                    }
                }
            } catch (ex: Exception) {
                _uiState.value = ExpensesScreenState.Error
            }
        }
    }
}