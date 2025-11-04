package com.example.sitanggapp.screens.profile

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun DeleteAccountDialog(navController: NavController) {
    AlertDialog(
        onDismissRequest = { navController.popBackStack() },
        title = { Text("Hapus Akun?", fontWeight = FontWeight.Bold) },
        text = { Text("Apakah Anda yakin ingin menghapus akun? Tindakan ini tidak dapat dibatalkan.") },
        confirmButton = {
            TextButton(onClick = { /* delete logic */ navController.popBackStack() }) {
                Text("Ya", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Batal")
            }
        }
    )
}
