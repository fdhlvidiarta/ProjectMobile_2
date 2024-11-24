package com.kelompok2.kalkulatorbmi.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SetGoalDialog(onDismiss: () -> Unit, onConfirm: (Float, Float) -> Unit) {
    var initialWeight by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    val initial = initialWeight.toFloatOrNull()
                    val target = targetWeight.toFloatOrNull()
                    if (initial != null && target != null && initial != target) {
                        onConfirm(initial, target)
                    }
                }
            ) {
                Text("Set")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        },
        title = { Text("Set Berat Awal dan Tujuan") },
        text = {
            Column {
                OutlinedTextField(
                    value = initialWeight,
                    onValueChange = { input ->
                        initialWeight = input.filter { it.isDigit() || it == '.' }
                    },
                    label = { Text("Berat Awal (kg)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = targetWeight,
                    onValueChange = { input ->
                        targetWeight = input.filter { it.isDigit() || it == '.' }
                    },
                    label = { Text("Berat Tujuan (kg)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    )
}