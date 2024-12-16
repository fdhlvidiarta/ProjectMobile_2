package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok2.kalkulatorbmi.R
import com.kelompok2.kalkulatorbmi.navigation.Screen

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    var isNavigating by remember { mutableStateOf(false) }

    if (!isNavigating) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header dengan efek shimmer
            ShimmerBox {
                Image(
                    painter = painterResource(id = R.drawable.bg_home),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Overlay abu-abu semi-transparan
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = 0.7f
                        }
                        .background(Color.Gray)
                )
                // Teks judul dan deskripsi
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Selamat Datang!",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp,
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(2f, 2f),
                                blurRadius = 3f
                            )

                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Hitung BMI Anda Dengan Mudah Dan Mulai Hidup Sehat Dengan Mengontrol Berat Badan Anda.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontSize = 16.sp,
                            shadow = Shadow(
                                color = Color.Gray,
                                offset = Offset(2f, 2f),
                                blurRadius = 3f
                            )
                        ),
                        textAlign = TextAlign.Start
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol fitur
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeButton(
                    label = "Indeks Massa Tubuh",
                    icon = R.drawable.ic_org,
                    onClick = {
                        isNavigating = true
                        onNavigate(Screen.BMICalculator.route)
                    }
                )

                HomeButton(
                    label = "Massa Tubuh Tanpa Lemak",
                    icon = R.drawable.ic_mttl,
                    onClick = {
                        isNavigating = true
                        onNavigate(Screen.LeanBodyMass.route)
                    }
                )
            }
        }
    }
}

@Composable
fun ShimmerBox(content: @Composable BoxScope.() -> Unit) {
    val shimmerOffset = remember { Animatable(-200f) }
    val shimmerWidth = 150f

    LaunchedEffect(Unit) {
        while (true) {
            shimmerOffset.animateTo(
                targetValue = 2000f,
                animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            )
            shimmerOffset.snapTo(-200f) // Reset posisi awal
            kotlinx.coroutines.delay(3000) // Jeda 3 detik
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .drawWithCache {
                // Modifikasi gradien dengan kemiringan
                val gradient = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = 0.5f),
                        Color.Transparent
                    ),
                    start = Offset(shimmerOffset.value, 0f),
                    end = Offset(shimmerOffset.value + shimmerWidth, shimmerWidth / 2)
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.SrcOver)
                }
            },
        content = content
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeButton(label: String, icon: Int, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = CardDefaults.cardElevation(4.dp)
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
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
