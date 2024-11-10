package com.kelompok2.kalkulatorbmi.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kelompok2.kalkulatorbmi.navigation.Screen
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

@Composable
fun BottomNavigationBar(
    navController: NavController,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.Black,
    shape: Shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp) // Melengkung di sisi atas saja
) {
    val items = listOf(
        Screen.Home,
        Screen.Riwayat,
        Screen.Rumus,
        Screen.InfoKesehatan
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth() // Mengisi lebar layar sepenuhnya
            .clip(shape) // Memberikan sudut melengkung hanya di atas
            .background(color = backgroundColor), // Warna latar belakang NavigationBar
        containerColor = MaterialTheme.colorScheme.primary // Mengatur agar warna dasar transparan
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon!!),
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = contentColor,
                    unselectedIconColor = contentColor.copy(alpha = 0.5f),
                    selectedTextColor = contentColor,
                    unselectedTextColor = contentColor.copy(alpha = 0.5f),
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}