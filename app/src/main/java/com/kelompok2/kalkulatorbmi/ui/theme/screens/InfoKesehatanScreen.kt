package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kelompok2.kalkulatorbmi.R

@Composable
fun InfoKesehatanScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)
    ) {
        SectionTitle(title = "Makanan Sehat")
        val healthInfoList = listOf(
            Triple(
                R.drawable.dada_ayam,
                "Dada ayam tanpa kulit adalah sumber protein tinggi dan rendah lemak. Cocok untuk diet tinggi protein tanpa kalori berlebih.",
                "detail_dada_ayam"
            ),
            Triple(
                R.drawable.telur_ayam,
                "Telur adalah sumber protein berkualitas tinggi dan rendah gula. Mengandung berbagai vitamin dan mineral penting.",
                "detail_telur"
            ),
            Triple(
                R.drawable.kacang_kacangan,
                "Kacang-kacangan seperti almond dan kacang merah mengandung protein tinggi serta serat yang mendukung pencernaan, tanpa tambahan gula.",
                "detail_kacang"
            )
        )

        healthInfoList.forEach { (imageRes, description, route) ->
            HealthInfoCard(
                imageRes = imageRes,
                description = description,
                onClick = { navController.navigate(route) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        SectionTitle(title = "Olahraga")
        val exerciseInfoList = listOf(
            Triple(
                R.drawable.lari,
                "Lari adalah olahraga kardio yang efektif untuk meningkatkan kebugaran jantung dan membakar kalori, serta meningkatkan stamina tubuh.",
                "detail_lari"
            ),
            Triple(
                R.drawable.renang,
                "Renang adalah latihan tubuh penuh yang melibatkan hampir semua kelompok otot, meningkatkan kekuatan dan fleksibilitas, serta mengurangi risiko cedera.",
                "detail_renang"
            ),
            Triple(
                R.drawable.angkat_beban,
                "Angkat beban dapat membantu meningkatkan massa otot, memperkuat tulang, dan meningkatkan metabolisme tubuh, yang sangat baik untuk kesehatan jangka panjang.",
                "detail_angkat_beban"
            )
        )

        exerciseInfoList.forEach { (imageRes, description, route) ->
            HealthInfoCard(
                imageRes = imageRes,
                description = description,
                onClick = { navController.navigate(route) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp), // Besarkan ukuran font
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = MaterialTheme.colorScheme.onBackground // Ubah warna menjadi hitam (atau sesuai tema)
    )
}

@Composable
fun HealthInfoCard(imageRes: Int, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f) // Agar teks tidak keluar dari baris
            )
        }
    }
}
