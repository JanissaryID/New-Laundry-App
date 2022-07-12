package com.example.laundryapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleRadioButtons(items: List<String>) : String {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
//    val items = listOf("Tunai", "Qris")
//    val items = listOf("Tunai", "Qris")
    Column() {
//        Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = MaterialTheme.colorScheme.primary,
                        selectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = item,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    return selectedValue.value
}