package com.example.sitanggapp.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun RegisterOptionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Judul / Logo singkat
        Text(text = "SITanggap", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(36.dp))

        // Google sign-in (contoh UI saja)
        OutlinedButton(
            onClick = {
                // TODO: implement Google Sign-In flow
                // Setelah sukses sign-in -> langsung ke register success (flow kamu)
                navController.navigate("register_success")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign in with Google")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sign in with phone (UI saja)
        OutlinedButton(
            onClick = {
                // TODO: implement phone auth OR open phone input flow
                // Setelah sukses (simulasi) -> register success
                navController.navigate("register_success")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Phone, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign in with Handphone")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Kalau sudah punya akun -> ke login
        Button(onClick = { navController.navigate("login") }, modifier = Modifier.fillMaxWidth().height(48.dp)) {
            Text("Sudah punya akun? Masuk")
        }
    }
}
