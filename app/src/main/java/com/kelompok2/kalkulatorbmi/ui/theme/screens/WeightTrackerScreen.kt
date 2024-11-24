package com.kelompok2.kalkulatorbmi.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.kelompok2.kalkulatorbmi.data.AppDatabase
import com.kelompok2.kalkulatorbmi.viewmodel.WeightTrackerViewModel
import com.kelompok2.kalkulatorbmi.viewmodel.WeightTrackerViewModelFactory

@Composable
fun WeightTrackerScreen() {
    val context = LocalContext.current
    val database = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weight_tracker_db"
        ).build()
    }

    val viewModel: WeightTrackerViewModel = viewModel(
        factory = WeightTrackerViewModelFactory(database.weightDao())
    )

    WeightTrackerContent(viewModel = viewModel)
}