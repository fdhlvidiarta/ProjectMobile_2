package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok2.kalkulatorbmi.R
import com.kelompok2.kalkulatorbmi.navigation.Screen

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    var isNavigating by remember { mutableStateOf(false) }

    if (!isNavigating) { // Hanya tampilkan konten jika tidak dalam navigasi
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selamat Datang di Kalkulator Kesehatan",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Tombol Indeks Massa Tubuh
            HomeButton(
                label = "Indeks Massa Tubuh",
                icon = R.drawable.ic_org, // Ikon sesuai desain
                onClick = {
                    isNavigating = true // Setel state ke true untuk menyembunyikan konten
                    onNavigate(Screen.BMICalculator.route)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Massa Tubuh Tanpa Lemak
            HomeButton(
                label = "Massa Tubuh Tanpa Lemak",
                icon = R.drawable.ic_mttl, // Ikon sesuai desain
                onClick = {
                    isNavigating = true // Setel state ke true untuk menyembunyikan konten
                    onNavigate(Screen.LeanBodyMass.route)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeButton(label: String, icon: Int, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.scrim,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontSize = 18.sp)
        }
    }
}