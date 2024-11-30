package com.example.finalprojectfirstterm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalprojectfirstterm.viewmodels.AViewModel
import com.example.finalprojectfirstterm.views.AnyadirCView
import com.example.finalprojectfirstterm.views.EditarCView
import com.example.finalprojectfirstterm.views.InicioView
import com.example.finalprojectfirstterm.views.SplashScreen

@Composable
fun NavManager(
    viewModel: AViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ){
        composable("splash_screen"){
            SplashScreen(navController)
        }

        composable("inicio") {
            InicioView(navController, viewModel)
        }

        composable("anyadir") {
            AnyadirCView(navController, viewModel)
        }

        composable("editar/{idCita}/{nombre}/{telefono}/{asunto}/{dia}/{hora}", arguments = listOf(
            navArgument("idCita") { type = NavType.StringType },
            navArgument("nombre") { type = NavType.StringType },
            navArgument("telefono") { type = NavType.StringType },
            navArgument("asunto") { type = NavType.StringType },
            navArgument("dia") { type = NavType.StringType },
            navArgument("hora") { type = NavType.StringType }
        )) {
            EditarCView(
                navController,
                viewModel,
                it.arguments?.getString("idCita")!!,
                it.arguments?.getString("nombre")!!,
                it.arguments?.getString("telefono")!!,
                it.arguments?.getString("asunto")!!,
                it.arguments?.getString("dia")!!,
                it.arguments?.getString("hora")!!


            )
        }
    }
}