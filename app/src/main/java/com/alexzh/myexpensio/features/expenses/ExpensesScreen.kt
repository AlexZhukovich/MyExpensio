package com.alexzh.myexpensio.features.expenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexzh.myexpensio.R
import com.alexzh.myexpensio.components.Header

@Composable
fun ExpensesScreen(
    onAdd: () -> Unit,
    viewModel: ExpensesViewModel
) {
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.app_name),
                elevation = 0.dp
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { onAdd() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.expenses_addButton_contentDescription)
                )
            }
        }
    ) {
        when (val result = uiState) {
            ExpensesScreenState.Loading -> LoadingContainer(it)
            is ExpensesScreenState.Success -> SuccessContainer(result.data, it)
            ExpensesScreenState.Empty -> MessageContainer(stringResource(R.string.expenses_no_data_this_month), it)
            ExpensesScreenState.Error -> MessageContainer(stringResource(R.string.expenses_unexpected_error), it)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetchSpendingCategories()
    }
}

@Composable
private fun LoadingContainer(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SuccessContainer(
    totalExpenses: TotalExpenses,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            Summary(
                totalExpenses.formattedBalance,
                totalExpenses.formattedIncome,
                totalExpenses.formattedExpense
            )
        }

        itemsIndexed(totalExpenses.categories) { index, item ->
            SpendingCategoryItem(item)

            if (index < totalExpenses.categories.lastIndex) {
                Divider(modifier = Modifier.padding(start = 64.dp))
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth().height(64.dp))
        }
    }
}

@Composable
private fun MessageContainer(
    message: String,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            fontSize = 24.sp
        )
    }
}

@Composable
private fun SpendingCategoryItem(item: SpendingCategoryItem) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.primary
        ) {
            Icon(
                modifier = Modifier.fillMaxSize().padding(4.dp),
                painter = painterResource(item.icon),
                contentDescription = null,
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier.weight(1.0f)
                .padding(horizontal = 8.dp)
        ) {
            Text(item.name, fontSize = 16.sp)
        }

        Column(
            modifier = Modifier.padding(end = 8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(item.formattedAmount, fontSize = 16.sp)
        }
    }
}

@Composable
private fun Summary(
    formattedBalance: String,
    formattedIncome: String,
    formattedExpense: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
            .height(160.dp),
        color = MaterialTheme.colors.primarySurface,
        elevation = AppBarDefaults.TopAppBarElevation
    ) {
        Column {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .weight(1.0f)
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.expenses_summary_balance_label),
                    fontSize = 10.sp,
                    color = Color.LightGray
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = formattedBalance,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "April, 2022 ",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1.0f)
                        .height(70.dp)
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.expenses_summary_totalIncome_label),
                        fontSize = 10.sp,
                        color = Color.LightGray
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.arrow_top_right_bold_box),
                            contentDescription = null,
                            tint = Color.Green
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = formattedIncome,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1.0f)
                        .height(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.expenses_summary_totalExpense_label),
                        fontSize = 10.sp,
                        color = Color.LightGray
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.arrow_bottom_left_bold_box),
                            contentDescription = null,
                            tint = Color.Red
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = formattedExpense,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}
