package com.example.sitanggapp.screens.pengaduan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sitanggapp.R
import com.example.sitanggapp.navigation.AppNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePengaduanScreen(navController: NavController? = null) {
    var jenisMasalah by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            // ✅ Gunakan bawaan Material3, tidak perlu bikin ulang fungsi SmallTopAppBar
            TopAppBar(
                title = { Text("Laporan Fasilitas Rusak", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Input Jenis Masalah
            OutlinedTextField(
                value = jenisMasalah,
                onValueChange = { jenisMasalah = it },
                label = { Text("Jenis Masalah") },
                modifier = Modifier.fillMaxWidth()
            )

            // Input Tanggal
            OutlinedTextField(
                value = tanggal,
                onValueChange = { tanggal = it },
                label = { Text("Hari/Tanggal") },
                modifier = Modifier.fillMaxWidth()
            )

            // Input Deskripsi
            OutlinedTextField(
                value = deskripsi,
                onValueChange = { deskripsi = it },
                label = { Text("Deskripsi") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            // Upload Foto
            Text("Upload Foto", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { /* TODO: Ambil foto */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDEDED))
                ) {
                    Text("Ambil Foto", color = Color.Black)
                }
                Button(
                    onClick = { /* TODO: Pilih dari galeri */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDEDED))
                ) {
                    Text("Pilih dari Galeri", color = Color.Black)
                }
            }

            // Lokasi
            Text("Lokasi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Text("Peta belum tersedia", color = Color.Gray)
            }

            Button(
                onClick = { /* TODO: Gunakan lokasi saya */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002B5B)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Gunakan Lokasi Saya", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Kirim
            Button(
                onClick = { navController.navigate("listsaran") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kirim Laporan", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreatePengaduanScreen() {
    CreatePengaduanScreen()
}
