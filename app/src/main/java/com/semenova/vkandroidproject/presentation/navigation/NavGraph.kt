package com.semenova.vkandroidproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.semenova.vkandroidproject.presentation.screens.appdetails.AppDetailsScreen
import com.semenova.vkandroidproject.presentation.screens.applist.MainScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.AppListScreen.route
    ) {
        composable(Screen.AppListScreen.route) {
            MainScreen(
                onAppClick = {
                    navController.navigate(Screen.AppScreen.createRoute(it))
                }
            )
        }
        composable(Screen.AppScreen.route) {
            val appId = Screen.AppScreen.getAppId(it.arguments)
            AppDetailsScreen(
                appId = appId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}