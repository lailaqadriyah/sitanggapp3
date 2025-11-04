package com.example.sitanggapp.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sitanggapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadFotoScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Upload Foto Profil") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Foto", modifier = Modifier.size(150.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { /* buka galeri */ }) { Text("Upload Foto") }
                Button(onClick = { /* buka kamera */ }) { Text("Buka Kamera") }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("success_save") }) {
                Text("Simpan Perubahan")
            }
        }
    }
}
