package com.example.sitanggapp.screens.pengaduan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sitanggapp.ui.theme.SitanggappTheme

@Composable
fun ListPengaduanScreen(navController: NavController? = null) {
    val pengaduanList = remember {
        mutableStateListOf(
            PengaduanData("1", "Jalan Berlubang", "Terdapat jalan berlubang di depan kantor kelurahan."),
            PengaduanData("2", "Lampu Jalan Mati", "Beberapa lampu jalan di area pasar tidak menyala."),
            PengaduanData("3", "Sampah Menumpuk", "TPS penuh dan belum diangkut sejak 3 hari lalu.")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ListPengaduan(list = pengaduanList, navController = navController) { pengaduan ->
            pengaduanList.remove(pengaduan) // hapus data setelah konfirmasi
        }
    }
}

data class PengaduanData(
    val id: String,
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengaduanCard(
    pengaduan: PengaduanData,
    navController: NavController? = null,
    onDeleteConfirmed: (PengaduanData) -> Unit
) {
    var showAlert by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = pengaduan.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = pengaduan.description, maxLines = 3, overflow = TextOverflow.Ellipsis)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ButtonTransparent(
                    Icons.Default.Edit,
                    onLick = {
                        // 🟢 Navigasi ke halaman edit dengan ID laporan
                        navController?.navigate("editPengaduan/${pengaduan.id}")
                    },
                    contentDescription = "Edit"
                )
                ButtonTransparent(
                    Icons.Default.Delete,
                    onLick = { showAlert = true },
                    contentDescription = "Delete"
                )
            }
        }
    }

    // 🟢 Alert konfirmasi hapus
    if (showAlert) {
        AlertBottomContentPreview(
            onCancel = { showAlert = false },
            onConfirm = {
                showAlert = false
                onDeleteConfirmed(pengaduan)
            }
        )
    }
}

@Composable
fun ButtonTransparent(imageVector: ImageVector, onLick: () -> Unit, contentDescription: String) {
    Button(
        onClick = onLick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun ListPengaduan(
    list: List<PengaduanData>,
    navController: NavController? = null,
    onDeleteConfirmed: (PengaduanData) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (pengaduan in list) {
            PengaduanCard(
                pengaduan = pengaduan,
                navController = navController,
                onDeleteConfirmed = onDeleteConfirmed
            )
        }
    }
}

@Composable
fun AlertBottomContentPreview(
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000))
            .padding(top = 400.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ikon Peringatan
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFFFFB74D),
                        radius = size.minDimension / 2
                    )
                }
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFFFFA000),
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Kamu yakin ingin menghapus laporan ini?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onCancel,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA000),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Tidak", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onConfirm,
                border = BorderStroke(1.dp, Color(0xFFFFA000)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Ya", fontSize = 16.sp, color = Color(0xFFFFA000))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListPengaduan() {
    SitanggappTheme {
        val navController = rememberNavController()
        ListPengaduanScreen(navController)
    }
}
