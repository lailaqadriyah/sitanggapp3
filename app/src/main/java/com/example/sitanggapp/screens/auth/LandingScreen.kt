package com.example.sitanggapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sitanggapp.R

@Composable
fun LandingScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // logo
            Image(
                painter = painterResource(id = R.drawable.sitanggap),
                contentDescription = "Logo SITanggap",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "SITanggap", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(40.dp))

            // tombol lanjut ke register option
            Button(onClick = { navController.navigate("register_option") }, modifier = Modifier
                .width(180.dp)
                .height(44.dp)) {
                Text(text = "Buat Akun / Kirim Laporan")
            }
        }
    }
}
