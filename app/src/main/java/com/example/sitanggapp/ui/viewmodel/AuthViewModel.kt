package com.example.sitanggapp.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sitanggapp.MainActivity
import com.example.sitanggapp.data.pref.UserModel
import com.example.sitanggapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val loginState: StateFlow<UiState<String>> = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val registerState: StateFlow<UiState<String>> = _registerState.asStateFlow()

    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                val response = repository.login(email, password)
                if (!response.error) {
                    val intent = Intent(context, MainActivity::class.java)

                    val user = UserModel(
                        email = email,
                        token = response.loginResult.token,
                        isLogin = true
                    )

                    repository.saveSession(user)
                    _loginState.value = UiState.Success("Login successful")
                    context.startActivity(intent)
                } else {
                    _loginState.value = UiState.Error(response.message)
                }
            } catch (e: Exception) {
                _loginState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = UiState.Loading
            try {
                val response = repository.register(name, email, password)
                if (!response.error) {
                    _registerState.value = UiState.Success("Registration successful")
                } else {
                    _registerState.value = UiState.Error(response.message)
                }
            } catch (e: Exception) {
                _registerState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Idle : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
}