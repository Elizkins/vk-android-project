package com.semenova.vkandroidproject.presentation.navigation

import android.os.Bundle

sealed class Screen(val route: String) {
    data object AppListScreen : Screen("main_screen")
    data object AppScreen : Screen("app_screen/{app_id}") {
        fun createRoute(appId: Int): String {
            return "app_screen/$appId"
        }
        
        fun getAppId(argument: Bundle?): Int {
            return argument?.getString("app_id")?.toInt() ?: 0
        }
    }
}