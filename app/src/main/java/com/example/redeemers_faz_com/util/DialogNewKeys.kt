package com.example.redeemers_faz_com.util

import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.redeemers_faz_com.KeyboardReport


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogNewKeys(
    newKeys: Int,
    icons: List<ImageVector>,
    getIcon: (icon : String) -> ImageVector,
    onDismissRequest: () -> Unit,
    onConfirmation: (key : Int, name : String, command : String, icon : String) -> Unit
) {
    var keysName by remember { mutableStateOf("") }
    var optionsIcons = mutableListOf<String>()
    icons.forEach { icon ->
        optionsIcons.add(icon.name)
        println("icon : ${icon.name}")
    }
    var optionsCommands = mutableListOf<String>()
    KeyboardReport.KeyEventMap.keys.forEach { key ->
        optionsCommands.add(KeyEvent.keyCodeToString(key))
    }
    var expandedIcons by remember { mutableStateOf(false) }
    var expandedCommands by remember { mutableStateOf(false) }
    var selectedOptionIcon by remember { mutableStateOf(optionsIcons[0]) }
    var selectedOptionCommand by remember { mutableStateOf(optionsCommands[0]) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Add a new key",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                )
                TextField(
                    value = keysName,
                    onValueChange = { keysName = it },
                    label = { Text("Key Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                ExposedDropdownMenuBox(
                    expanded = expandedIcons,
                    onExpandedChange = { expandedIcons = it },
                    modifier = Modifier.padding(16.dp),
                ) {
                    TextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOptionIcon,
                        onValueChange = {},
                        label = { Text("Icon") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedIcons)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = expandedIcons,
                        onDismissRequest = { expandedIcons = false },
                    ) {
                        optionsIcons.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Icon(imageVector = getIcon(selectionOption), contentDescription = "Icon")},
                                onClick = {
                                    selectedOptionIcon = selectionOption
                                    expandedIcons = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                ExposedDropdownMenuBox(
                    expanded = expandedCommands,
                    onExpandedChange = { expandedCommands = it },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    TextField(
                        // The `menuAnchor` modifier must be passed to the text field for correctness.
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOptionCommand,
                        onValueChange = {},
                        label = { Text("Command") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCommands) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = expandedCommands,
                        onDismissRequest = { expandedCommands = false },
                    ) {
                        optionsCommands.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedOptionCommand = selectionOption
                                    expandedCommands = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation(newKeys, keysName, selectedOptionCommand, selectedOptionIcon) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}