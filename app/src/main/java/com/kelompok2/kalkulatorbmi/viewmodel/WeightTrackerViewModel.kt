package com.kelompok2.kalkulatorbmi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok2.kalkulatorbmi.data.UserGoal
import com.kelompok2.kalkulatorbmi.data.WeightDao
import com.kelompok2.kalkulatorbmi.data.WeightData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class WeightTrackerViewModel(private val weightDao: WeightDao) : ViewModel() {

    private val _userGoal = MutableStateFlow<UserGoal?>(null)
    val userGoal: StateFlow<UserGoal?> = _userGoal.asStateFlow()

    private val _weightData = MutableStateFlow<List<WeightData>>(emptyList())
    val weightData: StateFlow<List<WeightData>> = _weightData.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _notification = MutableStateFlow<String?>(null)
    val notification: StateFlow<String?> = _notification.asStateFlow()

    init {
        loadUserGoal()
        loadWeightData()
    }

    private fun loadUserGoal() {
        viewModelScope.launch {
            _userGoal.value = weightDao.getUserGoal()
            calculateProgress()
        }
    }

    private fun loadWeightData() {
        viewModelScope.launch {
            weightDao.getAllWeights().collectLatest { weights ->
                _weightData.value = weights
                calculateProgress()
            }
        }
    }

    fun setUserGoal(initialWeight: Float, targetWeight: Float) {
        viewModelScope.launch {
            val userGoal = UserGoal(initialWeight = initialWeight, targetWeight = targetWeight)
            weightDao.insertUserGoal(userGoal)
            _userGoal.value = userGoal
            calculateProgress()
        }
    }

    fun addWeightData(weight: Float) {
        viewModelScope.launch {
            val newWeightData = WeightData(date = Date(), weightValue = weight)
            weightDao.insertWeight(newWeightData)
            _weightData.update { it + newWeightData }
            calculateProgress()
        }
    }

    fun deleteWeightData(weight: WeightData) {
        viewModelScope.launch {
            weightDao.deleteWeightById(weight.id)
            _weightData.update { it.filterNot { it.id == weight.id } }
            calculateProgress()
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            // Hapus semua data berat
            weightDao.clearWeights()
            _weightData.value = emptyList()

            // Hapus entri UserGoal dari database
            weightDao.deleteUserGoal()
            _userGoal.value = null

            // Reset progress dan notifikasi
            _progress.value = 0f
            _notification.value = null
        }
    }




    private fun calculateProgress() {
        val goal = _userGoal.value ?: return
        val data = _weightData.value
        if (data.isNotEmpty()) {
            val initialWeight = goal.initialWeight
            val targetWeight = goal.targetWeight
            val latestWeight = data.last().weightValue

            val calculatedProgress = ((initialWeight - latestWeight) / (initialWeight - targetWeight)).coerceIn(0f, 1f)
            _progress.value = calculatedProgress

            if (calculatedProgress >= 1f) {
                _notification.value = "Selamat! Anda telah mencapai berat tujuan Anda."
            } else {
                _notification.value = null
            }
        } else {
            _progress.value = 0f
            _notification.value = null
        }
    }

    fun clearNotification() {
        _notification.value = null
    }
}