package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeanBodyMassScreen(navController: NavController) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var leanBodyMassResult by remember { mutableStateOf<Double?>(null) }
    var recommendation by remember { mutableStateOf("") }
    var advice by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Massa Tubuh Tanpa Lemak") },
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
                RadioButton(selected = gender == "Laki-laki", onClick = { gender = "Laki-laki" })
                Text("Laki-laki", modifier = Modifier.clickable { gender = "Laki-laki" })
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = gender == "Perempuan", onClick = { gender = "Perempuan" })
                Text("Perempuan", modifier = Modifier.clickable { gender = "Perempuan" })
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(
                onClick = {
                    val weightValue = weight.toDoubleOrNull()
                    val heightValue = height.toDoubleOrNull()
                    val ageValue = age.toIntOrNull()

                    if (weightValue == null || weightValue <= 0) {
                        errorMessage = "Berat badan tidak valid."
                        return@Button
                    }

                    if (heightValue == null || heightValue <= 0) {
                        errorMessage = "Tinggi badan tidak valid."
                        return@Button
                    }

                    if (ageValue == null || ageValue <= 0) {
                        errorMessage = "Umur tidak valid."
                        return@Button
                    }

                    if (gender.isEmpty()) {
                        errorMessage = "Pilih jenis kelamin."
                        return@Button
                    }

                    errorMessage = ""
                    leanBodyMassResult = calculateLeanBodyMass(weightValue, heightValue, ageValue, gender)
                    if (leanBodyMassResult != null) {
                        recommendation = if (leanBodyMassResult!! > 60) {
                            "Massa tubuh tanpa lemak Anda sangat baik! Tetap jaga pola makan sehat dan olahraga teratur."
                        } else {
                            "Massa tubuh tanpa lemak Anda cukup baik. Anda bisa meningkatkan massa otot dengan olahraga rutin seperti angkat beban."
                        }

                        advice = when {
                            weightValue / (heightValue / 100).pow(2) < 18.5 -> {
                                "Anda berada dalam kategori kurus. Tingkatkan asupan kalori dan protein, konsumsi makanan bergizi seperti alpukat, selai kacang, ayam, ikan, dan telur. Selain itu, lakukan latihan beban dan latihan kekuatan secara rutin untuk membantu membangun massa otot!"
                            }
                            weightValue / (heightValue / 100).pow(2) >= 30 -> {
                                "Anda berada di kategori obesitas. Pertimbangkan pola makan rendah kalori dan lakukan olahraga aerobik secara rutin seperti jogging atau bersepeda!"
                            }
                            else -> {
                                "Kondisi tubuh Anda dalam batas normal. Terus pertahankan pola hidup sehat!"
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Hitung", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            leanBodyMassResult?.let { lbm ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = MaterialTheme.shapes.large
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Hasil Massa Tubuh Tanpa Lemak Anda",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = String.format("%.2f kg", lbm),
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = recommendation,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
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
                                Text(
                                    text = advice,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun calculateLeanBodyMass(weight: Double, height: Double, age: Int, gender: String): Double? {
    return if (age <= 14) {
        // Formula untuk usia dibawah 15 tahun
        val eECV = 0.0215 * weight.pow(0.6469) * height.pow(0.7236)
        3.8 * eECV
    } else {
        when (gender) {
            "Laki-laki" -> 0.407 * weight + 0.267 * height - 19.2
            "Perempuan" -> 0.252 * weight + 0.473 * height - 48.3
            else -> null
        }
    }
}

