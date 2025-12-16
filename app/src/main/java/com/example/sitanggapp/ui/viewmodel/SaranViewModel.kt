package com.example.sitanggapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import okhttp3.MultipartBody
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.sitanggapp.data.remote.response.SaranResponse
import com.example.sitanggapp.data.repository.SaranRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sitanggapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class SaranViewModel(private val repository: SaranRepository) : ViewModel() {

    private val _listSaran = MutableLiveData<List<SaranResponse>>()
    val listSaran: LiveData<List<SaranResponse>> = _listSaran

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _uploadResult = MutableLiveData<Result<SaranResponse>>()
    val uploadResult: LiveData<Result<SaranResponse>> = _uploadResult

    // Fungsi mengambil data saran
    fun getSaran() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllSaran()
                _listSaran.value = response
            } catch (e: Exception) {
                // Handle error, misalnya tampilkan toast
             e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Fungsi kirim saran
    fun uploadSaran(
        judul: String,
        deskripsi: String,
        latitude: String?= null,
        longitude: String?= null,
        foto: MultipartBody.Part?,
        navController: NavController? = null,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.addSaran(judul, deskripsi, latitude, longitude, foto)
                _uploadResult.value = Result.success(response)
                getSaran() // Refresh list setelah upload
                navController?.navigate("listsaran") {
                    popUpTo("listsaran") { inclusive = true }
                }
            } catch (e: Exception) {
                _uploadResult.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Fungsi untuk menghapus saran
    fun deleteSaran(id: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteSaran(id)
                onSuccess()
                // Di sini sebaiknya panggil ulang fungsi fetchSaran() agar list diperbarui otomatis
            } catch (e: Exception) {
                onError(e.message ?: "Gagal menghapus saran")
            }
        }
    }

    // Fungsi untuk mengedit saran
    fun updateSaran(
        id: Int,
        judul: String,
        deskripsi: String,
        latitude: String?,
        longitude: String?,
        foto: MultipartBody.Part?
    ) {
        viewModelScope.launch {
            try {
                // Pastikan repository.updateSaran sudah diperbaiki sesuai langkah sebelumnya
                repository.updateSaran(id, judul, deskripsi, latitude, longitude, foto)

                // Opsional: Beritahu UI bahwa update berhasil (misalnya lewat LiveData/StateFlow)
                // _updateResult.value = Result.Success(...)
            } catch (e: Exception) {
                // Handle error
                // _updateResult.value = Result.Error(e.message)
            }
        }
    }
}