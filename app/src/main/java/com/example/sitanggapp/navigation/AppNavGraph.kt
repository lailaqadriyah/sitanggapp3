package com.example.sitanggapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sitanggapp.screens.component.HomeScreen
import com.example.sitanggapp.screens.saran.CreateSaranScreen
import com.example.sitanggapp.screens.pengaduan.CreatePengaduanScreen
import com.example.sitanggapp.screens.profile.TampilanProfile
import com.example.sitanggapp.screens.saran.EditSaranScreen
import com.example.sitanggapp.screens.saran.SaranScreen
import com.example.sitanggapp.ui.viewmodel.ViewModelFactory

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen() }
        composable("pengaduan") { 
            CreatePengaduanScreen()
        }
        composable("saran") { 
            SaranScreen(navController = navController)
        }
        composable("tambahsaran") { 
            CreateSaranScreen(
                modifier = modifier,
                navController = navController,
                saranId = 0,
                viewModelFactory = viewModelFactory
            )
        }
        composable("profile") { 
            TampilanProfile(navController) 
        }
        composable("editsaran/{saranId}") { backStackEntry ->
            EditSaranScreen(
                modifier = modifier,
                navController = navController,
                saranId = backStackEntry.arguments?.getString("saranId") ?: "0",
                existingData = emptyMap(),
                viewModelFactory = viewModelFactory
            )
        }

    }
}
