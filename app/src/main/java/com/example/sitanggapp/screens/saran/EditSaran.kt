package com.example.sitanggapp.screens.saran

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sitanggapp.ui.viewmodel.SaranViewModel
import com.example.sitanggapp.ui.viewmodel.ViewModelFactory
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.sitanggapp.ui.theme.SitanggappTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSaranScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    saranId: String, // ID untuk mengambil data yang akan diedit
    existingData: Map<String, String>, // Data yang ada untuk diedit
    viewModelFactory: ViewModelFactory
) {
    var jenisMasalah by remember { mutableStateOf(existingData["jenisMasalah"] ?: "") }
    var tanggal by remember { mutableStateOf(existingData["tanggal"] ?: "") }
    var deskripsi by remember { mutableStateOf(existingData["deskripsi"] ?: "") }

    val viewModel: SaranViewModel = viewModel(factory = viewModelFactory)

    val scrollState = rememberScrollState()
    val darkBlue = Color(0xFF003366)
    val orange = Color(0xFFFF9800)
    val borderColor = Color(0xFFE0E0E0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Edit Saran",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = 16.dp
                )
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Jenis Masalah
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Jenis Masalah",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = jenisMasalah,
                    onValueChange = { jenisMasalah = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = borderColor,
                        focusedBorderColor = darkBlue
                    )
                )
            }

            // Hari/Tanggal
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Hari/Tanggal",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = tanggal,
                    onValueChange = { tanggal = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = borderColor,
                        focusedBorderColor = darkBlue
                    )
                )
            }

            // Deskripsi
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Deskripsi",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = deskripsi,
                    onValueChange = { deskripsi = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = borderColor,
                        focusedBorderColor = darkBlue
                    )
                )
            }

            // Upload Foto
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Upload Foto",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { /* TODO: Ambil foto */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, borderColor)
                    ) {
                        Text("Ambil Foto", fontSize = 14.sp)
                    }

                    OutlinedButton(
                        onClick = { /* TODO: Pilih dari galeri */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, borderColor)
                    ) {
                        Text("Pilih dari Galeri", fontSize = 14.sp)
                    }
                }
            }

            // Lokasi
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Lokasi",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8F4F8))
                        .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Peta Placeholder",
                            tint = Color(0xFF0288D1),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Peta Lokasi",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* TODO: Gunakan lokasi saya */ },
                    colors = ButtonDefaults.buttonColors(containerColor = darkBlue),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        "Gunakan lokasi saya",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tombol Kirim
            Button(
                onClick = {
                    // Kirim data yang telah diperbarui ke API untuk disimpan
                    viewModel.updateSaran(
                        id = saranId.toInt(),
                        judul = jenisMasalah,
                        deskripsi = deskripsi,
                        latitude = null,
                        longitude = null,
                        foto = null
                    )
                    navController?.popBackStack() // Kembali setelah data diperbarui
                },
                colors = ButtonDefaults.buttonColors(containerColor = orange),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    "Simpan Perubahan",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EditSaranScreenPreview() {
    SitanggappTheme {
        // Data dummy untuk simulasi tampilan edit
        val dummyData = mapOf(
            "jenisMasalah" to "Lampu Jalan Rusak",
            "tanggal" to "12 Oktober 2025",
            "deskripsi" to "Lampu di perempatan jalan mawar mati total, sangat gelap saat malam hari."
        )

        // Menggunakan LocalContext untuk mendapatkan instance factory
        // Catatan: Pada preview mode, fungsi ViewModel mungkin tidak berjalan sempurna
        // tanpa mocking repository, tapi ini cukup untuk melihat tampilan UI.
        val context = LocalContext.current

        EditSaranScreen(
            saranId = "1", // ID Dummy
            existingData = dummyData,
            viewModelFactory = ViewModelFactory.getInstance(context)
        )
    }
}
