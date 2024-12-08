package com.kelompok2.kalkulatorbmi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.annotation.DrawableRes
import com.kelompok2.kalkulatorbmi.R
import com.kelompok2.kalkulatorbmi.ui.theme.screens.*

sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int?) {
    object Splash : Screen("splash_screen", "Splash", null)
    object Home : Screen("home_screen", "Home", R.drawable.ic_home)
    object BMICalculator : Screen("bmi_calculator", "Indeks Massa Tubuh", null)
    object LeanBodyMass : Screen("lean_body_mass", "Lean Body Mass", null)
    object WeightTracker : Screen("weight_tracker_screen", "Pelacak", R.drawable.ic_weight)
    object Rumus : Screen("rumus_screen", "Rumus", R.drawable.ic_formula)
    object InfoKesehatan : Screen("info_screen", "Kesehatan", R.drawable.ic_more)
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(Screen.BMICalculator.route) {
            BMICalculatorScreen(navController = navController)
        }
        composable(Screen.LeanBodyMass.route) {
            LeanBodyMassScreen(navController = navController)
        }
        composable(Screen.WeightTracker.route) {
            WeightTrackerScreen()
        }
        composable(Screen.Rumus.route) {
            RumusScreen()
        }
        composable(Screen.InfoKesehatan.route) {
            InfoKesehatanScreen(navController = navController) // Ensure InfoKesehatanScreen is implemented
        }
        composable("detail_dada_ayam") {
            DetailDadaAyamScreen() // Implement your own screen or ensure this is correct
        }
        composable("detail_telur") {
            DetailTelurScreen() // Implement your own screen or ensure this is correct
        }
        composable("detail_kacang") {
            DetailKacangScreen() // Implement your own screen or ensure this is correct
        }
        composable("detail_renang") {
            DetailRenangSehatScreen() // Implement your own screen or ensure this is correct
        }
        composable("detail_lari") {
            DetailLariSehatScreen() // Implement your own screen or ensure this is correct
        }
        composable("detail_angkat_beban") {
            DetailAngkatBebanSehatScreen() // Implement your own screen or ensure this is correct
        }



    }
}
