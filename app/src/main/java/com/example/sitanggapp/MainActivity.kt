package com.example.sitanggapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.sitanggapp.navigation.AppNavGraph
import com.example.sitanggapp.ui.theme.DarkBlue
import com.example.sitanggapp.ui.theme.SitanggappTheme
import com.example.sitanggapp.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SitanggappTheme {
                Sitanggapp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sitanggapp() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "SITanggap") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notif", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "home",
                    onClick = { navController.navigate("home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = currentRoute == "pengaduan",
                    onClick = { navController.navigate("pengaduan") },
                    icon = { Icon(Icons.Default.Face, contentDescription = "Pengaduan") },
                    label = { Text("Pengaduan") }
                )
                NavigationBarItem(
                    selected = currentRoute == "saran",
                    onClick = { navController.navigate("saran") },
                    icon = { Icon(Icons.Default.MailOutline, contentDescription = "Saran") },
                    label = { Text("Saran") }
                )
                NavigationBarItem(
                    selected = currentRoute == "profile",
                    onClick = { navController.navigate("profile") },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModelFactory= ViewModelFactory.getInstance(navController.context)
        )
    }
}

@Preview (
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SitanggappPreview() {
    SitanggappTheme {
        Sitanggapp()
    }
}


