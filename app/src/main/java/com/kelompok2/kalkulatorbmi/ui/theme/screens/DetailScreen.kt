package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kelompok2.kalkulatorbmi.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackButton(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
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

@Composable
fun DetailDadaAyamScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(title = "Detail Dada Ayam", navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Latar belakang putih
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            // Gambar di bagian atas tanpa bayangan
            Image(
                painter = painterResource(id = R.drawable.dada_ayam),
                contentDescription = "Dada Ayam",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Judul besar tanpa bayangan atau efek tambahan
            Text(
                text = "Manfaat Dada Ayam",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Teks konten
            Text(
                text = "Dada ayam adalah salah satu sumber protein hewani yang sangat populer dan sering direkomendasikan dalam pola makan sehat. Berikut beberapa manfaat dada ayam untuk kesehatan:",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Poin-poin manfaat
            val benefits = listOf(
                "Sumber Protein Berkualitas Tinggi" to "Dada ayam mengandung protein yang sangat baik untuk pembentukan dan perbaikan jaringan tubuh.",
                "Rendah Lemak" to "Dibandingkan bagian ayam lainnya, dada ayam memiliki kandungan lemak yang lebih rendah.",
                "Mendukung Penurunan Berat Badan" to "Kandungan protein tinggi membantu memberikan rasa kenyang lebih lama.",
                "Kaya Nutrisi Penting" to "Dada ayam mengandung vitamin dan mineral seperti vitamin B6, fosfor, dan selenium.",
                "Mendukung Pembentukan Otot" to "Protein dalam dada ayam membantu pemulihan dan pertumbuhan massa otot."
            )

            benefits.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        // Teks manfaat
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun DetailTelurScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(title = "Detail Telur", navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Latar belakang putih
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Gambar di bagian atas tanpa bayangan
            Image(
                painter = painterResource(id = R.drawable.telur_ayam),
                contentDescription = "Telur",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Judul besar tanpa bayangan atau efek tambahan
            Text(
                text = "Manfaat Telur",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deskripsi Umum
            Text(
                text = "Telur adalah salah satu makanan yang kaya akan gizi dan sangat mudah diolah. Berikut beberapa manfaat telur untuk kesehatan:",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Daftar Manfaat
            val benefits = listOf(
                "Sumber Protein Berkualitas Tinggi" to "Telur mengandung semua asam amino esensial yang dibutuhkan tubuh untuk membangun otot dan memperbaiki jaringan.",
                "Mendukung Kesehatan Otak" to "Kandungan kolin dalam telur sangat penting untuk fungsi otak, termasuk meningkatkan daya ingat dan konsentrasi.",
                "Baik untuk Kesehatan Mata" to "Telur mengandung lutein dan zeaxanthin, dua antioksidan yang mendukung kesehatan mata dan mencegah degenerasi makula.",
                "Mendukung Pengendalian Berat Badan" to "Kandungan protein tinggi pada telur memberikan rasa kenyang lebih lama, membantu mengontrol asupan kalori sepanjang hari."
            )

            benefits.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailKacangScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(title = "Detail Kacang", navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Latar belakang putih
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Gambar di bagian atas tanpa bayangan
            Image(
                painter = painterResource(id = R.drawable.kacang_kacangan),
                contentDescription = "Kacang-kacangan",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Judul besar tanpa bayangan atau efek tambahan
            Text(
                text = "Manfaat Kacang-kacangan",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deskripsi Umum
            Text(
                text = "Kacang-kacangan adalah sumber gizi penting yang kaya akan protein nabati, lemak sehat, dan serat. Berikut beberapa manfaat kacang-kacangan untuk kesehatan:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Daftar Manfaat
            val benefits = listOf(
                "Mendukung Kesehatan Jantung" to "Kandungan lemak tak jenuh dalam kacang membantu menurunkan kadar kolesterol jahat dan meningkatkan kesehatan jantung.",
                "Kaya Akan Antioksidan" to "Kacang mengandung vitamin E dan senyawa polifenol yang membantu melawan radikal bebas dalam tubuh.",
                "Meningkatkan Energi" to "Kacang-kacangan adalah camilan yang padat energi, cocok untuk meningkatkan stamina dan daya tahan tubuh.",
                "Mendukung Kesehatan Pencernaan" to "Serat yang tinggi dalam kacang membantu memperlancar pencernaan dan mencegah sembelit."
            )

            benefits.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRenangSehatScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(title = "Detail Renang", navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Latar belakang putih
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Gambar di bagian atas tanpa bayangan
            Image(
                painter = painterResource(id = R.drawable.renang), // Pastikan resource gambar tersedia
                contentDescription = "Renang Sehat",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Judul besar
            Text(
                text = "Manfaat Renang Sehat",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deskripsi Umum
            Text(
                text = "Renang adalah salah satu olahraga yang sangat baik untuk kesehatan tubuh. Berikut adalah beberapa manfaat renang untuk kesehatan:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Daftar Manfaat
            val benefits = listOf(
                "Meningkatkan Kesehatan Jantung" to "Renang dapat meningkatkan kapasitas jantung dan paru-paru, serta meningkatkan aliran darah, yang berdampak baik pada kesehatan kardiovaskular.",
                "Membantu Meningkatkan Fleksibilitas" to "Olahraga ini melibatkan banyak gerakan tubuh yang berbeda, yang membantu meningkatkan fleksibilitas dan kekuatan otot.",
                "Meredakan Stres dan Meningkatkan Kesehatan Mental" to "Renang dapat meredakan ketegangan pada tubuh dan pikiran, serta meningkatkan suasana hati dengan merangsang pelepasan endorfin.",
                "Mengurangi Risiko Cedera" to "Karena melibatkan gerakan yang lembut dan tidak memberikan dampak yang keras pada sendi, renang adalah olahraga yang ideal untuk semua usia, termasuk mereka yang memiliki masalah sendi."
            )

            benefits.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DetailLariSehatScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(title = "Detail Lari", navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Latar belakang putih
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Gambar di bagian atas tanpa bayangan
            Image(
                painter = painterResource(id = R.drawable.lari), // Pastikan resource gambar tersedia
                contentDescription = "Lari Sehat",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Judul besar
            Text(
                text = "Manfaat Lari Sehat",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deskripsi Umum
            Text(
                text = "Lari adalah olahraga yang mudah diakses dan sangat bermanfaat untuk kesehatan tubuh. Berikut adalah beberapa manfaat lari untuk kesehatan:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Daftar Manfaat
            val benefits = listOf(
                "Meningkatkan Kesehatan Jantung" to "Lari dapat meningkatkan kapasitas jantung dan paru-paru, serta memperbaiki aliran darah ke seluruh tubuh, yang dapat mengurangi risiko penyakit jantung.",
                "Membantu Menurunkan Berat Badan" to "Lari adalah aktivitas yang membakar kalori dengan cepat, sehingga sangat efektif untuk membantu menurunkan berat badan atau menjaga berat badan ideal.",
                "Meningkatkan Kekuatan Otot dan Keseimbangan" to "Meskipun lari lebih fokus pada kardiovaskular, olahraga ini juga dapat memperkuat otot-otot kaki dan meningkatkan keseimbangan tubuh secara keseluruhan.",
                "Meningkatkan Kesehatan Mental" to "Lari dapat membantu mengurangi stres, kecemasan, dan depresi, serta meningkatkan mood dengan melepaskan endorfin yang dikenal sebagai hormon kebahagiaan."
            )

            benefits.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailAngkatBebanSehatScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(title = "Detail Angkat Beban", navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Latar belakang putih
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Gambar di bagian atas tanpa bayangan
            Image(
                painter = painterResource(id = R.drawable.angkat_beban), // Pastikan resource gambar tersedia
                contentDescription = "Angkat Beban Sehat",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Judul besar
            Text(
                text = "Manfaat Angkat Beban Sehat",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Deskripsi Umum
            Text(
                text = "Angkat beban adalah salah satu bentuk latihan fisik yang bertujuan untuk memperkuat otot dan meningkatkan kekuatan tubuh. Berikut adalah beberapa manfaat angkat beban untuk kesehatan:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Daftar Manfaat
            val benefits = listOf(
                "Meningkatkan Kekuatan Otot" to "Angkat beban adalah cara yang efektif untuk membangun dan memperkuat otot. Otot yang lebih kuat dapat meningkatkan kemampuan tubuh dalam melakukan berbagai aktivitas fisik.",
                "Meningkatkan Kepadatan Tulang" to "Latihan angkat beban dapat merangsang pembentukan tulang yang lebih padat, yang membantu mencegah osteoporosis, terutama pada usia lanjut.",
                "Meningkatkan Metabolisme" to "Angkat beban dapat meningkatkan laju metabolisme tubuh, yang membantu pembakaran kalori lebih efektif bahkan saat istirahat, mendukung penurunan berat badan atau pemeliharaan berat badan ideal.",
                "Meningkatkan Keseimbangan dan Koordinasi" to "Latihan beban membantu melatih keseimbangan dan koordinasi tubuh, terutama saat mengangkat beban dengan gerakan yang tepat, sehingga membantu mengurangi risiko cedera."
            )

            benefits.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}