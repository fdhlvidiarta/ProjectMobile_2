package com.kelompok2.kalkulatorbmi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kelompok2.kalkulatorbmi.data.WeightDao

class WeightTrackerViewModelFactory(private val weightDao: WeightDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeightTrackerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeightTrackerViewModel(weightDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}