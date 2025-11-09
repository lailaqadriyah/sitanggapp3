package com.example.sitanggapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sitanggapp.screens.auth.LoginScreen
import com.example.sitanggapp.ui.theme.SitanggappTheme
import com.example.sitanggapp.ui.viewmodel.ViewModelFactory

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SitanggappTheme {
                PreviewLoginScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val viewModelFactory = ViewModelFactory.getInstance(navController.context)
    LoginScreen(navController = navController, viewModelFactory =viewModelFactory )
}