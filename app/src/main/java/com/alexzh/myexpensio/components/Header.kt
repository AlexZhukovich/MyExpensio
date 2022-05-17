package com.alexzh.myexpensio.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.alexzh.myexpensio.R

@Composable
fun Header(
    title: String,
    isNavigationIconDisplayed: Boolean = false,
    onNavigationAction: () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = title)
        },
        navigationIcon = if (isNavigationIconDisplayed) {
            {
                IconButton(onClick = onNavigationAction) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.navigation_back_contentDescription)
                    )
                }
            }
        } else {
            null
        },
        backgroundColor = backgroundColor,
        elevation = elevation
    )
}

@Preview
@Composable
fun Preview_HeaderWithTitleWithoutNavigation() {
    Header(
        title = "Test title",
    )
}

@Preview
@Composable
fun Preview_HeaderWithTitleWithNavigation() {
    Header(
        title = "Test title",
        isNavigationIconDisplayed = true
    )
}