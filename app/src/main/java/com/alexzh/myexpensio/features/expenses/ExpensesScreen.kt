package com.alexzh.myexpensio.features.expenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
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
import com.alexzh.myexpensio.data.Expense
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun ExpensesScreen(
    onAdd: () -> Unit,
) {
    val list = mutableListOf<Expense>()
    repeat(30) {
        list.add(
            Expense(
                icon = Icons.Default.AccountBox,
                name = "Tickets",
                category = "Transportation",
                amount = BigDecimal(150.56).setScale(2, RoundingMode.HALF_UP),
                date = "May 5, 2022"
            )
        )
    }

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


        LazyColumn {
            item {
                Summary()
            }

            itemsIndexed(list) { index, item ->
                TransactionItem(item)

                if (index < list.lastIndex) {
                    Divider(modifier = Modifier.padding(start = 56.dp))
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth().height(64.dp))
            }
        }
    }
}

@Composable
private fun TransactionItem(expense: Expense) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(56.dp),
            imageVector = expense.icon,
            contentDescription = ""
        )

        Column(modifier = Modifier.weight(1.0f)) {
            Text(expense.name, fontSize = 16.sp)
            Text(expense.category, fontSize = 12.sp)
        }

        Column(
            modifier = Modifier.padding(end = 8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(expense.amount.toString(), fontSize = 16.sp)
            Text(expense.date, fontSize = 12.sp)
        }
    }
}

@Composable
private fun Summary() {
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
                    text = "$ 4000,00",
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
                            text = "$ 5300,25",
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
                            text = "$ 1300,25",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}
