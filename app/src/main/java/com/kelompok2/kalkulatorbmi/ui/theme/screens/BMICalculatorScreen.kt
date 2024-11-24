package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculatorScreen(navController: NavController) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Laki-laki") }
    var bmiResult by remember { mutableStateOf<Double?>(null) }
    var category by remember { mutableStateOf<String?>(null) }
    var suggestedWeightRange by remember { mutableStateOf("") }
    var inputHeightText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Masukkan Data Anda",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Berat Badan (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Tinggi Badan (cm)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Umur (tahun)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Jenis Kelamin", modifier = Modifier.weight(1f))
                RadioButton(
                    selected = selectedGender == "Laki-laki",
                    onClick = { selectedGender = "Laki-laki" }
                )
                Text("Laki-laki", modifier = Modifier.clickable { selectedGender = "Laki-laki" })
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = selectedGender == "Perempuan",
                    onClick = { selectedGender = "Perempuan" }
                )
                Text("Perempuan", modifier = Modifier.clickable { selectedGender = "Perempuan" })
            }

            Button(
                onClick = {
                    errorMessage = ""
                    gender = selectedGender
                    val weightValue = weight.toDoubleOrNull()
                    val heightValue = height.toDoubleOrNull()
                    val ageValue = age.toIntOrNull()

                    if (weight.isEmpty() || height.isEmpty() || age.isEmpty() || selectedGender.isEmpty()) {
                        errorMessage = "Semua data harus diisi!"
                    } else if (weightValue == null || heightValue == null || ageValue == null || weightValue <= 0 || heightValue <= 0 || ageValue <= 0) {
                        errorMessage = "Masukkan data yang valid!"
                    } else {
                        val heightInMeters = heightValue / 100
                        bmiResult = weightValue / (heightInMeters * heightInMeters)
                        category = getBMICategory(bmiResult!!, gender, ageValue)
                        suggestedWeightRange = getSuggestedWeightRange(heightInMeters, gender, ageValue)
                        inputHeightText = "Tinggi badan: $height cm"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Hitung", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            bmiResult?.let { bmi ->
                category?.let { categoryText ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF2196F3),
                                            Color(0xFF4CAF50),
                                            Color(0xFFEAB200),
                                            Color(0xFFFF1200)
                                        )
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .drawBehind {
                                    val markerPosition = when {
                                        bmi < 18.5 -> size.width * 0.1f
                                        bmi < 24.9 -> size.width * 0.4f
                                        bmi < 29.9 -> size.width * 0.7f
                                        else -> size.width * 0.9f
                                    }
                                    val triangleHeight = 20.dp.toPx()
                                    drawLine(
                                        color = Color.Black,
                                        start = Offset(markerPosition, 0f),
                                        end = Offset(markerPosition, triangleHeight),
                                        strokeWidth = 4.dp.toPx()
                                    )
                                }
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "18.5",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 23.dp)
                            )
                            Text(
                                "24.9",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                            Text(
                                "29.9",
                                fontSize = 12.sp,
                                textAlign = TextAlign.End,
                                color = Color.Black,
                                modifier = Modifier.padding(end = 23.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.outlineVariant, shape = MaterialTheme.shapes.large)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Hasil BMI Anda", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(
                                    text = String.format("%.2f", bmi),
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                Text("Kategori: $categoryText",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(inputHeightText, fontSize = 15.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = MaterialTheme.colorScheme.secondary,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Saran berat ideal $gender: $suggestedWeightRange (kg)",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getBMICategory(bmi: Double, gender: String, age: Int): String {
    return when {
        age >= 60 -> {
            when {
                bmi < 22 -> "Berat Badan Kurus"
                bmi <= 27 -> "Berat Badan Normal"
                bmi <= 32 -> "Kelebihan Berat Badan"
                else -> "Obesitas"
            }
        }
        gender == "Laki-laki" -> {
            when {
                bmi < 18.5 -> "Berat Badan Kurus"
                bmi <= 24.9 -> "Berat Badan Normal"
                bmi <= 29.9 -> "Kelebihan Berat Badan"
                else -> "Obesitas"
            }
        }
        else -> {
            when {
                bmi < 18.5 -> "Berat Badan Kurus"
                bmi <= 22.9 -> "Berat Badan Normal"
                bmi <= 27.9 -> "Kelebihan Berat Badan"
                else -> "Obesitas"
            }
        }
    }
}

fun getSuggestedWeightRange(heightInMeters: Double, gender: String, age: Int): String {
    val minWeight: Double
    val maxWeight: Double

    if (age >= 60) {
        if (gender == "Laki-laki") {
            minWeight = 18.5 * heightInMeters * heightInMeters
            maxWeight = 23.0 * heightInMeters * heightInMeters
        } else {
            minWeight = 17.5 * heightInMeters * heightInMeters
            maxWeight = 22.5 * heightInMeters * heightInMeters
        }
    } else {
        if (gender == "Laki-laki") {
            minWeight = 20.0 * heightInMeters * heightInMeters
            maxWeight = 25.0 * heightInMeters * heightInMeters
        } else {
            minWeight = 18.5 * heightInMeters * heightInMeters
            maxWeight = 24.0 * heightInMeters * heightInMeters
        }
    }
    return "${String.format("%.1f", minWeight)} - ${String.format("%.1f", maxWeight)}"
}