package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kelompok2.kalkulatorbmi.navigation.AppNavigation
import com.kelompok2.kalkulatorbmi.navigation.Screen
import com.kelompok2.kalkulatorbmi.ui.theme.components.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // Mendapatkan rute saat ini
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Menentukan judul berdasarkan rute
    val title = getTitleForScreen(currentRoute)

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Splash.route && currentRoute != Screen.BMICalculator.route && currentRoute != Screen.LeanBodyMass.route) {
                TopAppBar(
                    title = { Text(text = title) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    )
                )
            }
        },
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.WeightTracker.route || currentRoute == Screen.Rumus.route || currentRoute == Screen.InfoKesehatan.route) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            AppNavigation(navController = navController)
        }
    }
}

// Fungsi untuk mendapatkan judul berdasarkan rute
@Composable
fun getTitleForScreen(route: String?): String {
    return when (route) {
        Screen.Home.route -> "Home"
        Screen.WeightTracker.route -> "Pelacak Berat"
        Screen.Rumus.route -> "Rumus"
        Screen.InfoKesehatan.route -> "Info Kesehatan"
        else -> "BMI Calculator" // Judul default atau untuk SplashScreen
    }
}