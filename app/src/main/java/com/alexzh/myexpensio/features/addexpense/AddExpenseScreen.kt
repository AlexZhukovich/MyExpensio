package com.alexzh.myexpensio.features.addexpense

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.myexpensio.R
import com.alexzh.myexpensio.components.Header
import com.alexzh.myexpensio.components.OutlineDatePicker
import com.alexzh.myexpensio.components.OutlineDropDownList
import com.alexzh.myexpensio.data.FakeTransactionIconProvider
import com.alexzh.myexpensio.data.TransactionIcon
import java.time.LocalDate

@Composable
fun AddExpenseScreen(
    onNavigateBack: () -> Unit,
    onSelectCategory: () -> Unit,
    selectedCategoryId: Int? = null,
    iconProvider: FakeTransactionIconProvider = FakeTransactionIconProvider()
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.addNewExpense_title),
                isNavigationIconDisplayed = true,
                onNavigationAction = { onNavigateBack() },
            )
        }
    ) {
        val transactionTypes = listOf(
            stringResource(R.string.addNewExpense_transactionType_incomeLabel),
            stringResource(R.string.addNewExpense_transactionType_expenseLabel),
        )
        val transactionIcon = selectedCategoryId?.let { iconProvider.getIconById(it) }
        val transactionType = remember {
            mutableStateOf(transactionTypes.last())
        }
        val transactionAmount = remember { mutableStateOf("0") }
        val selectedDate = remember { mutableStateOf(LocalDate.now()) }
        val note = remember { mutableStateOf("") }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
        ) {

            item {
                SelectCategory(transactionIcon, onSelectCategory)
            }

            item {
                OutlineDropDownList(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    label = { Text(stringResource(R.string.addNewExpense_transactionType_label)) },
                    selectedValue = transactionType.value,
                    items = listOf(
                        stringResource(R.string.addNewExpense_transactionType_incomeLabel),
                        stringResource(R.string.addNewExpense_transactionType_expenseLabel),
                    ),
                    onValueChange = { transactionType.value = it }
                )
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = transactionAmount.value,
                    onValueChange = { transactionAmount.value = it },
                    label = { Text(stringResource(R.string.addNewExpense_amount_label)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            item {
                OutlineDatePicker(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    label = { Text(stringResource(R.string.addNewExpense_transactionDate_label)) },
                    value = selectedDate.value,
                    onValueChange = { selectedDate.value = it }
                )
            }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = note.value,
                    onValueChange = { note.value = it },
                    label = { Text(stringResource(R.string.addNewExpense_note_label)) }
                )
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = {}
                ) {
                    Text(
                        text = stringResource(R.string.addNewExpense_addExpenseButton)
                    )
                }
            }
        }
    }
}

@Composable
fun SelectCategory(
    transactionIcon: TransactionIcon?,
    onSelectCategory: () -> Unit
) {
    val categoryIcon = transactionIcon?.icon ?: Icons.Default.Add
    Surface(
        modifier = Modifier.fillMaxWidth()
            .height(160.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.size(80.dp)
                    .clickable { onSelectCategory() },
                color = MaterialTheme.colors.primary
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = categoryIcon,
                    contentDescription = stringResource(R.string.selectCategory_selectCategory_contentDescription),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp)
                    .clickable { onSelectCategory() },
                text = transactionIcon?.name ?: stringResource(R.string.selectCategory_selectCategory_contentDescription),
                fontSize = 16.sp,
            )
        }
    }
}