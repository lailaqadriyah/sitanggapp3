package com.example.sitanggapp.data.repository

import com.example.sitanggapp.data.remote.response.SaranResponse
import com.example.sitanggapp.data.remote.retrofit.ApiService
import okhttp3.MultipartBody
import retrofit2.Response

class SaranRepository(private val apiService: ApiService) {

    // Mendapatkan semua data saran
    suspend fun getAllSaran(): List<SaranResponse> {
        val response: Response<List<SaranResponse>> = apiService.getAllSaran()

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception("Failed to load saran: ${response.message()}")
        }
    }

    // Menambahkan data saran
    suspend fun addSaran(
        judul: String,
        deskripsi: String,
        latitude: String? = null,
        longitude: String? = null,
        foto: MultipartBody.Part?
    ): SaranResponse {
        val response: Response<SaranResponse> =
            apiService.addSaran(judul, deskripsi, latitude, longitude, foto)

        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Failed to add saran: empty body")
        } else {
            throw Exception("Failed to add saran: ${response.message()}")
        }
    }

    // Menghapus data saran
    suspend fun deleteSaran(id: Int): SaranResponse {
        val response: Response<SaranResponse> = apiService.deleteSaran(id)

        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Failed to delete saran: empty body")
        } else {
            throw Exception("Failed to delete saran: ${response.message()}")
        }
    }
    suspend fun updateSaran(
        id: Int,
        judul: String,
        deskripsi: String,
        latitude: String?,
        longitude: String?,
        foto: MultipartBody.Part?
    ): SaranResponse {
        // 1. TAMBAHKAN 'val response ='
        // Pastikan juga kamu mengirimkan 'id' ke apiService jika endpoint membutuhkannya
        val response: Response<SaranResponse> = apiService.updateSaran(id, judul, deskripsi, latitude, longitude, foto)

        return if (response.isSuccessful) {
            // 2. Perbaiki pesan error (sebelumnya "add saran", ubah jadi "update saran")
            response.body() ?: throw Exception("Failed to update saran: empty body")
        } else {
            throw Exception("Failed to update saran: ${response.message()}")
        }
    }

    companion object {
        @Volatile
        private var instance: SaranRepository? = null

        fun getInstance(apiService: ApiService): SaranRepository =
            instance ?: synchronized(this) {
                instance ?: SaranRepository(apiService).also { instance = it }
            }
    }
}
