package com.example.sitanggapp.screens.saran

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sitanggapp.AddSaranActivity
import com.example.sitanggapp.data.remote.response.SaranResponse
import com.example.sitanggapp.ui.theme.SitanggappTheme
import com.example.sitanggapp.ui.viewmodel.SaranViewModel
import com.example.sitanggapp.ui.viewmodel.ViewModelFactory

@Composable
fun SaranScreen(
    viewModel: SaranViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val context = LocalContext.current

    // Gunakan LaunchedEffect untuk memanggil data sekali saja saat UI pertama kali dibuat
    LaunchedEffect(Unit) {
        viewModel.getSaran()
    }

    val listSaran by viewModel.listSaran.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    // State untuk menyimpan ID yang sedang ingin dihapus
    var idToDelete by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navigasi ke halaman Tambah Saran
                    val intent = Intent(context, AddSaranActivity::class.java)
                    context.startActivity(intent)
                },
                containerColor = Color(0xFFFF9800), // Warna Oranye
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Saran")
            }
        }
    ) { paddingValues ->

        // Container Utama
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Penting agar tidak tertutup status bar/bottom bar
                .padding(horizontal = 20.dp)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFFF9800))
                }
            } else {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Daftar Saran",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (listSaran.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = "Belum ada saran", color = Color.Gray)
                        }
                    } else {
                        // ListSaranContent menggunakan LazyColumn agar bisa discroll
                        ListSaranContent(
                            list = listSaran,
                            onDeleteRequest = { id -> idToDelete = id }
                        )
                    }
                }
            }
        }
    }

    // Tampilkan Alert Hapus jika idToDelete tidak null
    if (idToDelete != null) {
        AlertHapus(
            onCancel = { idToDelete = null },
            onConfirm = {
                idToDelete?.let { id ->
                    // --- PERBAIKANNYA DI SINI ---
                    // Menggunakan argumen bernama untuk semua parameter yang dibutuhkan
                    viewModel.deleteSaran(
                        id = id,
                        onSuccess = {
                            // Setelah sukses delete dari API, refresh list
                            viewModel.getSaran()
                        },
                        onError = { errorMessage ->
                            // TODO: Tampilkan pesan error ke pengguna, misalnya dengan Toast atau Snackbar
                            println("Error deleting saran: $errorMessage")
                        }
                    )
                    // --------------------------
                }
                idToDelete = null
            }
        )
    }
}

// === FUNGSI YANG BARU DITAMBAHKAN ===
@Composable
fun ListSaranContent(
    list: List<SaranResponse>,
    onDeleteRequest: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 80.dp) // Beri ruang untuk FAB
    ) {
        items(list, key = { it.idSaran ?: -1 }) { saran ->
            SaranItem(
                saran = saran,
                onDeleteClick = {
                    saran.idSaran?.let { id ->
                        onDeleteRequest(id)
                    }
                }
            )
        }
    }
}

// === FUNGSI YANG BARU DITAMBAHKAN ===
@Composable
fun SaranItem(
    saran: SaranResponse,
    onDeleteClick: () -> Unit
) {
    val darkBlue = Color(0xFF003366)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = saran.judul ?: "Tanpa Judul",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = darkBlue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = saran.deskripsi ?: "Tidak ada deskripsi",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row {
                IconButton(onClick = { /* TODO: Implementasi edit */ }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Saran",
                        tint = Color.Gray
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus Saran",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}


// Ubah nama AlertBottomContentPreview menjadi AlertHapus agar lebih jelas
@Composable
fun AlertHapus(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    // Overlay semi-transparan
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFFFFF3E0),
                        radius = size.minDimension / 2
                    )
                }
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = "Kamu yakin ingin menghapus?",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = "Data yang dihapus tidak dapat dikembalikan.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = onConfirm, // Langsung panggil onConfirm
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Ya, Hapus", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
                OutlinedButton(
                    onClick = onCancel, // Langsung panggil onCancel
                    border = BorderStroke(1.dp, Color(0xFFFF9800)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Batal", fontSize = 16.sp, color = Color(0xFFFF9800))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSaranScreen() {
    SitanggappTheme {
        SaranScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSaranItem() {
    SitanggappTheme {
        SaranItem(
            saran = SaranResponse(1, "Lampu Merah Padam di Perempatan", "Lampu merah di perempatan ABC sudah padam selama 2 hari dan menyebabkan kemacetan parah."),
            onDeleteClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewAlertHapus() {
    SitanggappTheme {
        AlertHapus(onCancel = {}, onConfirm = {})
    }
}