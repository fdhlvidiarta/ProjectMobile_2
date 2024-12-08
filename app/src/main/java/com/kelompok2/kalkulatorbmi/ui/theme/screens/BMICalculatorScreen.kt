package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.background
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
    var bmiResult by remember { mutableStateOf<Double?>(null) }
    var category by remember { mutableStateOf<String?>(null) }
    var suggestedWeightRange by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var lastHeightInput by remember { mutableStateOf<Double?>(null) }

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
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Tinggi Badan (cm)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    errorMessage = ""
                    val weightValue = weight.toDoubleOrNull()
                    val heightValue = height.toDoubleOrNull()

                    if (weight.isEmpty() || height.isEmpty()) {
                        errorMessage = "Semua data harus diisi!"
                    } else if (weightValue == null || heightValue == null || weightValue <= 0 || heightValue <= 0) {
                        errorMessage = "Masukkan data yang valid!"
                    } else {
                        val heightInMeters = heightValue / 100
                        bmiResult = weightValue / (heightInMeters * heightInMeters)
                        category = getBMICategory(bmiResult!!)
                        suggestedWeightRange = getSuggestedWeightRange(heightInMeters)
                        lastHeightInput = heightValue
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
                                        colors = getBMIColors()
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .drawBehind {
                                    val markerPosition = when {
                                        bmi < 16 -> size.width * 0.05f
                                        bmi in 16.0..17.0 -> size.width * 0.125f
                                        bmi in 17.0..18.5 -> size.width * 0.25f
                                        bmi in 18.5..24.9 -> size.width * 0.375f
                                        bmi in 25.0..29.9 -> size.width * 0.625f
                                        bmi in 30.0..35.0 -> size.width * 0.75f
                                        bmi in 35.0..40.0 -> size.width * 0.875f
                                        bmi > 40.0 -> size.width * 0.95f
                                        else -> size.width * 0.5f
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
                            listOf("16", "17", "18.5", "24.9", "25", "29.9", "35", "40").forEachIndexed { index, label ->
                                Text(
                                    label,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = if (index == 0) 16.dp else 0.dp, end = if (index == 7) 16.dp else 0.dp)
                                )
                            }
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
                                Text("Hasil BMI Anda", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                                Text(
                                    text = String.format("%.2f", bmi),
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = getBMIColor(bmi),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                Text(
                                    text = "Tinggi Badan: ${lastHeightInput?.toInt()} cm",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Kategori: $categoryText",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

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
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text(
                                            text = "Kisaran BMI Sehat: 18.5 - 25",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )

                                        Text(
                                            text = "Kisaran Berat Ideal: $suggestedWeightRange (kg)",
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
}


fun getBMICategory(bmi: Double): String {
    return when {
        bmi < 16 -> "Terlalu Kurus"
        bmi in 16.0..17.0 -> "Kurus Sedang"
        bmi in 17.0..18.5 -> "Kurus Ringan"
        bmi in 18.5..25.0 -> "Normal"
        bmi in 25.0..30.0 -> "Kelebihan Berat Badan"
        bmi in 30.0..35.0 -> "Obesitas Kelas I"
        bmi in 35.0..40.0 -> "Obesitas Kelas II"
        bmi > 40.0 -> "Obesitas Kelas III"
        else -> "Kategori Tidak Dikenal"
    }
}

fun getBMIColor(bmi: Double): Color {
    return when {
        bmi < 16 -> Color(0xFF1976D2)
        bmi in 16.0..17.0 -> Color(0xFF2196F3)
        bmi in 17.0..18.5 -> Color(0xFF03A9F4)
        bmi in 18.5..25.0 -> Color(0xFF4CAF50)
        bmi in 25.0..30.0 -> Color(0xFFFFEB3B)
        bmi in 30.0..35.0 -> Color(0xFFFF9800)
        bmi in 35.0..40.0 -> Color(0xFFF44336)
        bmi > 40.0 -> Color(0xFFD32F2F)
        else -> Color.Gray
    }
}

fun getBMIColors(): List<Color> {
    return listOf(
        Color(0xFF1976D2),
        Color(0xFF2196F3),
        Color(0xFF03A9F4),
        Color(0xFF4CAF50),
        Color(0xFF4CAF50),
        Color(0xFFFFEB3B),
        Color(0xFFFF9800),
        Color(0xFFF44336)
    )
}

fun getSuggestedWeightRange(heightInMeters: Double): String {
    val minWeight = 18.5 * heightInMeters * heightInMeters
    val maxWeight = 24.9 * heightInMeters * heightInMeters
    return "${String.format("%.1f", minWeight)} - ${String.format("%.1f", maxWeight)}"
}