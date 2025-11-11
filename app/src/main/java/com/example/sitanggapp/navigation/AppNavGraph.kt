package com.example.sitanggapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sitanggapp.screens.component.HomeScreen
import com.example.sitanggapp.screens.saran.CreateSaranScreen
//import com.example.sitanggapp.screens.ProfileScreen
import com.example.sitanggapp.screens.pengaduan.CreatePengaduanScreen
import com.example.sitanggapp.screens.saran.SaranScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen() }
        composable("pengaduan") { CreatePengaduanScreen()}
        composable("saran") { CreateSaranScreen(modifier, navController) }
//        composable("profile") { ProfileScreen() }
        composable("listsaran") { SaranScreen() }
    }
}
