package com.alexzh.myexpensio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexzh.myexpensio.features.addexpense.AddExpenseScreen
import com.alexzh.myexpensio.features.expenses.ExpensesScreen
import com.alexzh.myexpensio.features.selectcategory.SelectCategoryScreen
import com.alexzh.myexpensio.navigation.Destination
import com.alexzh.myexpensio.ui.theme.MyExpensioTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyExpensioTheme {
                MyExpensioApp()
            }
        }
    }
}

@Composable
private fun MyExpensioApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Expenses.route) {
        composable(route = Destination.Expenses.route) {
            ExpensesScreen(
                onAdd = { navController.navigate(Destination.AddExpense.route) },
                viewModel = get()
            )
        }
        composable(route = Destination.AddExpense.route) {
            AddExpenseScreen(
                onNavigateBack = { navController.navigateUp() },
                onSelectCategory = { navController.navigate(Destination.SelectCategory.route) },
                selectedCategoryId = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.get<Int>("iconId")
            )
        }
        composable(route = Destination.SelectCategory.route) {
            SelectCategoryScreen(
                onNavigateBack = { navController.navigateUp() },
                onSelectCategory = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("iconId", it.id)
                    navController.popBackStack()
                }
            )
        }
    }
}