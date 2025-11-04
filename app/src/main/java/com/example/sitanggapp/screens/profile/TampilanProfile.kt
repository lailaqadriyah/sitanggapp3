package com.example.sitanggapp.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sitanggapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilanProfile(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profil") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Nama Pengguna", fontWeight = FontWeight.Bold)
            Text("email@gmail.com")
            Text("08123456789")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { navController.navigate("edit_profile") }) {
                Text("Edit Profil")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Riwayat Laporan", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(3) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("${index + 1}. Jalan Rusak")
                            Text("Status: ${if (index == 0) "Selesai" else "Proses"}")
                        }
                    }
                }
            }
        }
    }
}
