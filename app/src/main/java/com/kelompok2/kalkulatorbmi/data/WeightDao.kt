package com.kelompok2.kalkulatorbmi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {

    /**
     * Mendapatkan goal user dari database.
     * Hanya 1 entry yang disimpan sebagai data goal user.
     */
    @Query("SELECT * FROM UserGoal LIMIT 1")
    suspend fun getUserGoal(): UserGoal?

    /**
     * Memasukkan atau mengganti goal user di database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserGoal(userGoal: UserGoal)

    /**
     * Mendapatkan semua data berat badan secara reaktif.
     * Data akan dipesan berdasarkan tanggal secara ascending.
     */
    @Query("SELECT * FROM WeightData ORDER BY date ASC")
    fun getAllWeights(): Flow<List<WeightData>>

    /**
     * Menambahkan data berat badan baru ke database.
     * Jika terdapat konflik pada ID, data akan diganti.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(weightData: WeightData)

    /**
     * Menghapus data berat badan dari database berdasarkan ID.
     * ID digunakan untuk memastikan penghapusan data yang spesifik.
     */
    @Query("DELETE FROM WeightData WHERE id = :weightId")
    suspend fun deleteWeightById(weightId: Int)

    @Query("DELETE FROM WeightData")
    suspend fun clearWeights()

    @Query("DELETE FROM UserGoal")
    suspend fun deleteUserGoal()


}
