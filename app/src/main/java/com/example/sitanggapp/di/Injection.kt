package com.example.sitanggapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.sitanggapp.data.pref.UserPreference
import com.example.sitanggapp.data.remote.retrofit.ApiConfig
import com.example.sitanggapp.data.repository.SaranRepository
import com.example.sitanggapp.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService, pref)
    }

    fun provideSaranRepository(context: Context): SaranRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        // Ambil token secara sinkronus (blocking) untuk inisialisasi awal
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return SaranRepository.getInstance(apiService)
    }
}