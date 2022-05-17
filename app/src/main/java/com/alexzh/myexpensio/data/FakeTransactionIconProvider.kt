package com.alexzh.myexpensio.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

class FakeTransactionIconProvider {

    fun getIcons(): List<TransactionIcon> {
        return listOf(
            TransactionIcon(1, "Category 1", Icons.Filled.Add),
            TransactionIcon(2, "Category 2", Icons.Filled.KeyboardArrowUp),
            TransactionIcon(3, "Category 3", Icons.Filled.KeyboardArrowDown),
            TransactionIcon(4, "Category 4", Icons.Filled.KeyboardArrowLeft),
            TransactionIcon(5, "Category 5", Icons.Filled.KeyboardArrowRight),
            TransactionIcon(6, "Category 6", Icons.Filled.AccountBox),
            TransactionIcon(7, "Category 7", Icons.Filled.AccountCircle),
            TransactionIcon(8, "Category 8", Icons.Filled.Build),
            TransactionIcon(9, "Category 9", Icons.Filled.Check)
        )
    }

    fun getIconById(id: Int): TransactionIcon? {
        return getIcons().find { it.id == id }
    }
}