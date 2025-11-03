package com.example.sitanggapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sitanggapp.screens.auth.*
import com.example.sitanggapp.screens.component.HomeScreen
import com.example.sitanggapp.screens.pengaduan.CreatePengaduanScreen
import com.example.sitanggapp.screens.saran.CreateSaranScreen
import com.example.sitanggapp.screens.saran.SaranScreen
import com.example.sitanggapp.screens.auth.SplashScreen
import com.example.sitanggapp.screens.profile.*

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "splash", // mulai dari Splash
        modifier = modifier
    ) {
        // ====== FLOW AUTH ======
        composable("splash") { SplashScreen(navController) }
        composable("register") { RegisterOptionScreen(navController) }
        composable("register_success") { RegisterSuccessScreen(navController) }
        composable("login") { LoginScreen(navController) }

        // ====== HALAMAN UTAMA ======
        composable("home") { HomeScreen() }
        composable("pengaduan") { CreatePengaduanScreen() }
        composable("saran") { CreateSaranScreen(modifier, navController) }
        composable("listsaran") { SaranScreen() }

        // ====== HALAMAN PROFIL ======
        composable("profile") { TampilanProfile(navController) }
        composable("edit_profile") { EditProfileScreen(navController) }
        composable("delete_account") { DeleteAccountDialog(navController) }
        composable("upload_foto") { UploadFotoScreen(navController) }
        composable("success_save") { SuccessSaveScreen(navController) }
    }
}
