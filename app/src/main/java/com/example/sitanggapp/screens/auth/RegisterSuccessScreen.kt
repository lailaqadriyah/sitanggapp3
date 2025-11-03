package com.example.sitanggapp.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun RegisterSuccessScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "success", modifier = Modifier.size(120.dp))
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Akun Berhasil Dibuat", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Silakan masuk menggunakan username & password Anda")

        Spacer(modifier = Modifier.height(28.dp))

        Button(onClick = { navController.navigate("login") }, modifier = Modifier.fillMaxWidth().height(48.dp)) {
            Text("Lanjut ke Login")
        }
    }
}
