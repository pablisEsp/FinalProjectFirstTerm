package com.example.finalprojectfirstterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.finalprojectfirstterm.navigation.NavManager
import com.example.finalprojectfirstterm.room.ADataBase
import com.example.finalprojectfirstterm.ui.theme.FinalProjectFirstTermTheme
import com.example.finalprojectfirstterm.viewmodels.AViewModel
import com.example.finalprojectfirstterm.views.InicioView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalProjectFirstTermTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val database = Room.databaseBuilder(this, ADataBase::class.java, "db_a").build()
                    val dao = database.citasDAO()

                    val viewModel = AViewModel(dao)
                    NavManager(viewModel = viewModel)
                }
            }


        }
    }
}
/*
@Composable
fun MyApp(){
    val navController = rememberNavController() // Initialize the NavController

    NavHost(navController = navController, startDestination = "login") {  // Definir la pantalla de inicio
        composable("login") { LoginScreen(navController) }
        //composable("home") { HomeScreen() }
    }
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}