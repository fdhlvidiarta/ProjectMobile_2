package com.kelompok2.kalkulatorbmi.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class UserGoal(
    @PrimaryKey val id: Int = 0, // Fixed ID for a single user goal
    val initialWeight: Float,
    val targetWeight: Float
)

@Entity
data class WeightData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date = Date(), // Tanggal default adalah sekarang
    val weightValue: Float
)