package com.semenova.vkandroidproject.presentation.navigation

import android.os.Bundle

sealed class Screen(val route: String) {
    data object AppListScreen : Screen("main_screen")
    data object AppScreen : Screen("app_screen/{app_id}") {
        fun createRoute(appId: String): String {
            return "app_screen/$appId"
        }
        
        fun getAppId(argument: Bundle?): String {
            return argument?.getString("app_id") ?: "0"
        }
    }
}