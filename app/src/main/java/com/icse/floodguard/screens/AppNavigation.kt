package com.icse.floodguard.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController=navController,
        startDestination = "login"
        ){
        composable("login") {
            Login(navController)
        }
        composable("signup") {
            Signup(navController)
        }

        composable("home") {
            Home(navController)
        }
    }
}