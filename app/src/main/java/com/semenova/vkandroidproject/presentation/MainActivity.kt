package com.semenova.vkandroidproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.semenova.vkandroidproject.presentation.navigation.NavGraph
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkAndroidProjectTheme {
                NavGraph()
            }
        }
    }
}