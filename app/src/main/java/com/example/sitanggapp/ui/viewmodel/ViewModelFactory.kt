package com.example.sitanggapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sitanggapp.data.repository.SaranRepository // Pastikan import ini
import com.example.sitanggapp.data.repository.UserRepository
import com.example.sitanggapp.di.Injection

// Update constructor untuk menerima SaranRepository juga
class ViewModelFactory(
    private val userRepository: UserRepository,
    private val saranRepository: SaranRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(userRepository) as T
            }
            // Tambahkan case untuk SaranViewModel
            modelClass.isAssignableFrom(SaranViewModel::class.java) -> {
                SaranViewModel(saranRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                        // Pastikan Injection menyediakan kedua repository ini
                        Injection.provideRepository(context),
                        Injection.provideSaranRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}