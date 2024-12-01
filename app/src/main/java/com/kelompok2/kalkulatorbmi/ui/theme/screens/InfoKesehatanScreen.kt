package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok2.kalkulatorbmi.R

@Composable
fun InfoKesehatanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        HealthInfoCard(
            imageRes = R.drawable.dada_ayam,
            description = "Dada ayam tanpa kulit adalah sumber protein tinggi dan rendah lemak. Cocok untuk diet tinggi protein tanpa kalori berlebih."
        )
        Spacer(modifier = Modifier.height(16.dp))

        HealthInfoCard(
            imageRes = R.drawable.telur_ayam,
            description = "Telur adalah sumber protein berkualitas tinggi dan rendah gula. Mengandung berbagai vitamin dan mineral penting."
        )
        Spacer(modifier = Modifier.height(16.dp))

        HealthInfoCard(
            imageRes = R.drawable.kacang_kacangan,
            description = "Kacang-kacangan seperti almond dan kacang merah mengandung protein tinggi serta serat yang mendukung pencernaan, tanpa tambahan gula."
        )
        Spacer(modifier = Modifier.height(16.dp))

        HealthInfoCard(
            imageRes = R.drawable.ikan_salmon,
            description = "Ikan salmon kaya akan protein dan asam lemak omega-3 yang baik untuk kesehatan jantung dan otak, rendah gula."
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Data baru
        HealthInfoCard(
            imageRes = R.drawable.f2e01cbc_f853_4639_8755_5b46eca40bc1, // Pastikan gambar brokoli tersedia
            description = "Brokoli adalah sumber serat, vitamin C, dan K yang tinggi. Cocok untuk mendukung diet sehat dan menjaga kesehatan pencernaan."
        )
        Spacer(modifier = Modifier.height(16.dp))

        HealthInfoCard(
            imageRes = R.drawable.alpukat, // Ganti dengan gambar alpukat
            description = "Alpukat mengandung lemak sehat, vitamin E, dan serat, baik untuk kesehatan kulit dan pencernaan."
        )
        Spacer(modifier = Modifier.height(16.dp))
        HealthInfoCard(
            imageRes = R.drawable.bayam_hijau_organik_1_removebg_preview, // Ganti dengan gambar bayam
            description = "Bayam mengandung zat besi, kalsium, dan vitamin K yang mendukung kesehatan tulang dan membantu pembentukan darah."
        )
    }
}


@Composable
fun HealthInfoCard(imageRes: Int, description: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar di sebelah kiri
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Health Tip Image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )
            // Deskripsi teks
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
