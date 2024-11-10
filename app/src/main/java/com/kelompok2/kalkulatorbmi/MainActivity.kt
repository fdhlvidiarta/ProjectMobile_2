package com.kelompok2.kalkulatorbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.kelompok2.kalkulatorbmi.navigation.AppNavigation
import com.kelompok2.kalkulatorbmi.navigation.AppNavigation
import com.kelompok2.kalkulatorbmi.ui.theme.KalkulatorBMITheme
import com.kelompok2.kalkulatorbmi.ui.theme.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalkulatorBMITheme {
                MaterialTheme {
                    Surface {
                        MainScreen()
                    }
                }
            }

        }
    }
}