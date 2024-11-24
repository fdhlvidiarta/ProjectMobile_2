package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok2.kalkulatorbmi.ui.theme.components.AddWeightDialog
import com.kelompok2.kalkulatorbmi.ui.theme.components.SetGoalDialog
import com.kelompok2.kalkulatorbmi.utils.DateUtils
import com.kelompok2.kalkulatorbmi.viewmodel.WeightTrackerViewModel

@Composable
fun WeightTrackerContent(viewModel: WeightTrackerViewModel) {
    val userGoal by viewModel.userGoal.collectAsState(initial = null)
    val weightData by viewModel.weightData.collectAsState(initial = emptyList())
    val progress by viewModel.progress.collectAsState(initial = 0f)

    var showGoalDialog by remember { mutableStateOf(false) }
    var showAddWeightDialog by remember { mutableStateOf(false) }
    var showCongratulationDialog by remember { mutableStateOf(false) }

    // Tampilkan dialog jika progress mencapai 100%
    if (progress >= 1f && !showCongratulationDialog) {
        showCongratulationDialog = true
    }

    // Dialog untuk set berat awal dan tujuan
    if (showGoalDialog) {
        SetGoalDialog(onDismiss = { showGoalDialog = false }) { initial, target ->
            viewModel.setUserGoal(initial, target)
            showGoalDialog = false
        }
    }

    // Dialog untuk menambahkan data berat
    if (showAddWeightDialog) {
        AddWeightDialog(onDismiss = { showAddWeightDialog = false }) { weight ->
            viewModel.addWeightData(weight)
            showAddWeightDialog = false
        }
    }

    // Dialog selamat jika berat tujuan tercapai
    if (showCongratulationDialog) {
        CongratulationDialog(onDismiss = { showCongratulationDialog = false }) {
            viewModel.resetProgress()
            showCongratulationDialog = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (userGoal == null) {
                Button(
                    onClick = { showGoalDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Set Berat Awal dan Tujuan")
                }
            } else {
                userGoal?.let { goal ->
                    val currentWeight = weightData.lastOrNull()?.weightValue ?: goal.initialWeight
                    val difference = currentWeight - goal.initialWeight
                    val remaining = goal.targetWeight - currentWeight
                    val isLosingWeight = goal.initialWeight > goal.targetWeight

                    // InfoCard: Menampilkan informasi awal
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InfoCard(
                            title = "Mulai Berat",
                            value = "${goal.initialWeight} kg",
                            color = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                        InfoCard(
                            title = "Bobot Tujuan",
                            value = "${goal.targetWeight} kg",
                            color = MaterialTheme.colorScheme.error,
                            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    // InfoCard: Menampilkan status perolehan/penurunan berat
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InfoCard(
                            title = if (isLosingWeight) "Menurunkan" else "Memperoleh",
                            value = "${if (difference >= 0) "+" else ""}${difference} kg",
                            color = if (difference >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                        InfoCard(
                            title = "Sisa",
                            value = "${if (remaining >= 0) "+" else ""}${remaining} kg",
                            color = if (remaining >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    // Progress bar
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .progressSemantics(progress),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Kemajuan: ${(progress * 100).toInt()}%",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                // Daftar data berat dengan LazyColumn
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    itemsIndexed(weightData) { index, data ->
                        WeightDataCard(
                            weightValue = data.weightValue,
                            date = DateUtils.formatDate(data.date),
                            previousWeight = weightData.getOrNull(index - 1)?.weightValue, // Berat sebelumnya
                            initialWeight = userGoal?.initialWeight ?: 0f, // Berat awal dari userGoal
                            isLosingWeight = userGoal?.initialWeight ?: 0f > userGoal?.targetWeight ?: 0f, // Apakah goal untuk menurunkan berat
                            onDelete = { viewModel.deleteWeightData(data) } // Fungsi hapus data
                        )
                    }
                }

            }
        }

        // Tombol update berat
        if (userGoal != null) {
            Button(
                onClick = { showAddWeightDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("Update Berat")
            }
        }
    }
}

@Composable
fun CongratulationDialog(onDismiss: () -> Unit, onReset: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Selamat!") },
        text = { Text("Anda telah mencapai berat tujuan Anda!") },
        confirmButton = {
            Button(onClick = {
                onReset()
                onDismiss()
            }) {
                Text("Reset Data")
            }
        }
    )
}


@Composable
fun InfoCard(title: String, value: String, color: Color, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .width(140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = color
        )
    }
}

@Composable
fun WeightDataCard(
    weightValue: Float,
    date: String,
    previousWeight: Float?,
    initialWeight: Float,
    isLosingWeight: Boolean,
    onDelete: () -> Unit
) {
    // Jika previousWeight null (data pertama), bandingkan dengan initialWeight
    val compareWeight = previousWeight ?: initialWeight
    val change = weightValue - compareWeight
    val isGain = change >= 0

    // Mengatur warna dan ikon berdasarkan apakah menaikkan atau menurunkan berat
    val changeColor = if (isGain) {
        if (isLosingWeight) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    } else {
        if (isLosingWeight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    }
    val changeIcon = if (isGain) Icons.Filled.TrendingUp else Icons.Filled.TrendingDown

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bagian data berat
        Column {
            Text(
                text = "$weightValue kg",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Bagian perubahan berat
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            Icon(
                imageVector = changeIcon,
                contentDescription = null,
                tint = changeColor,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${if (change >= 0) "+" else ""}${"%.1f".format(change)} kg",
                color = changeColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }

        // Tombol hapus data
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Hapus",
            )
        }
    }
}