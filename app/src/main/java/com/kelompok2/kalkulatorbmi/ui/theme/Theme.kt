package com.kelompok2.kalkulatorbmi.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Definisi warna
private val LightColors = lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = Color.White,
    secondary = SecondaryGreen,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    scrim = Custtom,
    primaryContainer = Hitam
)

private val DarkColors = darkColorScheme(
    primary = PrimaryGreen,
    onPrimary = Color.White,
    secondary = SecondaryGreen,
    onSecondary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.DarkGray,
    onSurface = Color.White,
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun KalkulatorBMITheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}