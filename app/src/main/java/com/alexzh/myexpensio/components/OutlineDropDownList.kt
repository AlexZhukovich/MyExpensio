package com.alexzh.myexpensio.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.alexzh.myexpensio.R

@Composable
fun OutlineDropDownList(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    selectedValue: String,
    items: List<String>,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val isExpanded = remember { mutableStateOf(false) }
    val menuItemSize = remember { mutableStateOf(Size.Zero) }
    val icon = if (isExpanded.value) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    val iconContentDescription = if (isExpanded.value) {
        stringResource(R.string.expand_menu_items)
    } else {
        stringResource(R.string.collapse_menu_items)
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isExpanded.value = it.isFocused }
                .onGloballyPositioned { menuItemSize.value = it.size.toSize() },
            label = label,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = iconContentDescription,
                    Modifier.clickable { isExpanded.value = !isExpanded.value }
                )
            }
        )
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = {
                isExpanded.value = false
                focusManager.clearFocus()
            },
            modifier = Modifier
                .width(with(LocalDensity.current){menuItemSize.value.width.toDp()})
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onValueChange(item)
                    isExpanded.value = false
                    focusManager.clearFocus()
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 400)
@Composable
fun Preview_OutlineDropDownList() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    val selectedValue = remember { mutableStateOf(items.first()) }

    OutlineDropDownList(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        label = { Text("Test") },
        selectedValue = selectedValue.value,
        items = items,
        onValueChange = { selectedValue.value = it }
    )
}
