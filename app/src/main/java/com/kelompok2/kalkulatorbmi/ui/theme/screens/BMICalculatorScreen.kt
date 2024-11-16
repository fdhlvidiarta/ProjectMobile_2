package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculatorScreen(navController: NavController) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    var category by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Indeks Massa Tubuh") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = MaterialTheme.colorScheme.scrim
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Masukkan Berat dan Tinggi Anda",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Berat Badan (kg)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Tinggi Badan (cm)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val weightValue = weight.toDoubleOrNull()
                    val heightValue = height.toDoubleOrNull()
                    if (weightValue != null && heightValue != null) {
                        val heightInMeters = heightValue / 100
                        result = weightValue / (heightInMeters * heightInMeters)
                        category = getBMICategory(result!!)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Hitung",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Box untuk menampilkan hasil BMI dan kategori di bagian bawah
            if (result != null && category != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(color = MaterialTheme.colorScheme.outlineVariant, shape = MaterialTheme.shapes.large)
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Hasil BMI Anda",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = String.format("%.2f", result),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Kategori: $category",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                }
            }
        }
    }
}

// Fungsi untuk menentukan kategori berdasarkan nilai BMI
fun getBMICategory(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Berat Badan Kurang"
        bmi <= 24.9 -> "Berat Badan Normal"
        bmi <= 29.9 -> "Kelebihan Berat Badan"
        else -> "Obesitas"
    }
}