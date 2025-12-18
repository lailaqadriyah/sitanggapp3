package com.example.sitanggapp.ui.viewmodel

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T? = null, val message: String? = null) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}