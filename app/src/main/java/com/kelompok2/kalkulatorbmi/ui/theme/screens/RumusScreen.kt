package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok2.kalkulatorbmi.R

// Data class untuk menampung informasi rumus
data class RumusItem(
    val title: String,
    val description: String,
    val rumus: String,
    val icon: Int,
    val notes: String
)

@Composable
fun RumusScreen() {
    val rumusList = listOf(
        RumusItem(
            title = "Indeks Massa Tubuh (BMI)",
            description = "BMI adalah ukuran yang digunakan untuk mengukur kekurangan atau kelebihan berat badan seseorang berdasarkan tinggi dan berat badan mereka. Secara khusus, nilai yang diperoleh dari perhitungan BMI digunakan untuk mengkategorikan apakah seseorang kekurangan berat badan, memiliki berat badan normal, kelebihan berat badan, atau obesitas tergantung pada rentang nilai yang diperoleh.",
            rumus = "BMI = Berat (kg) / (Tinggi (m))^2",
            icon = R.drawable.ic_org,
            notes = "BMI tidak dapat sepenuhnya akurat karena merupakan ukuran kelebihan berat badan, bukan kelebihan lemak tubuh. BMI lebih lanjut dipengaruhi oleh faktor-faktor seperti usia, jenis kelamin, etnis, massa otot, lemak tubuh, dan tingkat aktivitas."
        ),
        RumusItem(
            title = "Lean Body Mass (LBM)",
            description = "Massa tubuh tanpa lemak (LBM) adalah bagian dari komposisi tubuh yang didefinisikan sebagai selisih antara berat total tubuh dan berat lemak tubuh. Ini berarti bahwa massa semua organ kecuali lemak tubuh dihitung, termasuk tulang, otot, darah, kulit, dan segala sesuatu yang lain. Sementara persentase LBM biasanya tidak dihitung, rata-ratanya berkisar antara 60-90% dari total berat badan. Secara umum, pria memiliki proporsi LBM yang lebih tinggi dibandingkan wanita. Dosis beberapa agen anestesi, terutama obat-obatan yang larut dalam air, biasanya didasarkan pada LBM. Beberapa pemeriksaan medis juga menggunakan nilai LBM.",
            rumus = "Untuk Pria:\n" +
                    "LBM = 0.407 × Berat (kg) + 0.267 × Tinggi (cm) - 19.2\n \n" +
                    "Untuk Wanita:\n" +
                    "LBM = 0.252 × Berat (kg) + 0.473 × Tinggi (cm) - 48.3\n \n" +
                    "Untuk Anak Usia di Bawah 15 Tahun:\n" +
                    "LBM = 3.8 × (0.0215 × Berat^0.6469 × Tinggi^0.7236)",
            icon = R.drawable.ic_mttl,
            notes = "Kami menggunakan formula milik Peters untuk menghitung eLBM pada anak dibawah 15 tahun, dan formula milik Boer untuk usia diatas 14 tahun."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menampilkan daftar rumus dalam card
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(rumusList) { rumusItem ->
                RumusCard(rumusItem)
            }
        }
    }
}

@Composable
fun RumusCard(rumusItem: RumusItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            RumusCardHeader(rumusItem)
            Spacer(modifier = Modifier.height(8.dp))
            RumusCardDescription(rumusItem.description)
            RumusCardFormula(rumusItem.rumus)
            RumusCardNotes(rumusItem.notes)
        }
    }
}

@Composable
fun RumusCardHeader(rumusItem: RumusItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = rumusItem.icon),
            contentDescription = rumusItem.title,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = rumusItem.title,
            style = TextStyle(fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun RumusCardDescription(description: String) {
    Text(
        text = description,
        style = TextStyle(fontSize = 14.sp, color = Color.Gray),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun RumusCardFormula(rumus: String) {
    Text(
        text = "Rumus: $rumus",
        style = TextStyle(fontSize = 16.sp, color = Color.Black),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun RumusCardNotes(notes: String) {
    if (notes.isNotEmpty()) {
        Text(
            text = "Catatan: $notes",
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}