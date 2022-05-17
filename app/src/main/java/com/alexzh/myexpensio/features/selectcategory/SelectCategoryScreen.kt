package com.alexzh.myexpensio.features.selectcategory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexzh.myexpensio.R
import com.alexzh.myexpensio.components.Header
import com.alexzh.myexpensio.data.FakeTransactionIconProvider
import com.alexzh.myexpensio.data.TransactionIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectCategoryScreen(
    onNavigateBack: () -> Unit,
    onSelectCategory: (TransactionIcon) -> Unit,
    iconProvider: FakeTransactionIconProvider = FakeTransactionIconProvider()
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.selectCategory_title),
                isNavigationIconDisplayed = true,
                onNavigationAction = { onNavigateBack() },
                elevation = 0.dp
            )
        },
        content = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(iconProvider.getIcons()) {
                    Card(
                        modifier = Modifier.padding(4.dp)
                            .clickable { onSelectCategory(it) },
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier.size(60.dp),
                                imageVector = it.icon,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onPrimary
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = it.name,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
            }
        }
    )
}