package com.example.sitanggapp.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nohp by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Edit Profil") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = nama, onValueChange = { nama = it }, label = { Text("Nama") })
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            OutlinedTextField(value = nohp, onValueChange = { nohp = it }, label = { Text("No HP") })

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("success_save") }, modifier = Modifier.fillMaxWidth()) {
                Text("Simpan Perubahan")
            }

            TextButton(onClick = { navController.navigate("delete_account") }) {
                Text("Hapus Akun?", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
