package com.example.sitanggapp.screens.saran

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sitanggapp.ui.theme.SitanggappTheme
import kotlinx.coroutines.launch

@Composable
fun Layout(modifier: Modifier, children: @Composable () -> Unit) {
    Column(
        modifier = modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        children()
    }
}

@Composable
fun SaranScreen() {
    Layout(modifier = Modifier) {
        ListSaran(
            list = listOf(
                Pair(
                    "Tambah Fitur Dark Mode",
                    "Agar pengguna dapat menggunakan aplikasi dengan nyaman di malam hari."
                ),
                Pair(
                    "Integrasi dengan Media Sosial",
                    "Memudahkan berbagi laporan ke platform sosial."
                ),
                Pair(
                    "Notifikasi Real-time",
                    "Memberikan update langsung tentang status laporan pengguna."
                ),
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaranCard(title: String, description: String) {
    var showAlert by remember { mutableStateOf(false) } // ← Tambah state

    Box(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = description, maxLines = 3, overflow = TextOverflow.Ellipsis)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                ButtonTransparent(Icons.Default.Edit, onLick = {}, contentDescription = "Edit")
                ButtonTransparent(
                    Icons.Default.Delete,
                    onLick = { showAlert = true }, // ← saat delete diklik, tampilkan alert
                    contentDescription = "Delete"
                )
            }
        }
    }

    // Jika showAlert true, tampilkan AlertBottomContentPreview
    if (showAlert) {
        AlertBottomContentPreview(
            onCancel = { showAlert = false }, // Tutup saat "Tidak"
            onConfirm = {
                showAlert = false
                // Di sini bisa tambahkan logika hapus data nanti
            }
        )
    }
}


@Composable
fun ButtonTransparent(imageVector: ImageVector, onLick: () -> Unit, contentDescription: String) {
    Button(
        onClick = onLick, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun ListSaran(list: List<Pair<String, String>>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (saran in list) {
            SaranCard(title = saran.first, description = saran.second)
        }
    }
}

@Composable
fun AlertBottomContentPreview(
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    // Ini cuma wrapper untuk simulasi posisi dari bawah
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)) // abu transparan (background blur)
            .padding(top = 400.dp) // seolah muncul dari bawah
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Ikon Peringatan ---
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

            // --- Teks Konfirmasi ---
            Text(
                text = "Kamu yakin ingin menghapus?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(28.dp))

            // --- Tombol Tidak ---
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

            // --- Tombol Ya ---
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
fun PreviewAlertBottom() {
    AlertBottomContentPreview()
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSaranScreen() {
    SaranScreenPreview()
}

@Preview(showBackground = true)
@Composable
fun SaranScreenPreview() {
    SitanggappTheme {
        SaranScreen()
    }
}


