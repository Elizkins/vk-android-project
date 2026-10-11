package com.semenova.vkandroidproject.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF5E8574),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE3EEE8),
    onPrimaryContainer = Color(0xFF2A4338),
    secondary = Color(0xFF7E9A8C),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8F0EB),
    onSecondaryContainer = Color(0xFF34493F),
    tertiary = Color(0xFFA39A6E),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF0EDDC),
    onTertiaryContainer = Color(0xFF4A4529),
    background = Color(0xFFF9FBFA),
    onBackground = Color(0xFF2B312E),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2B312E),
    surfaceVariant = Color(0xFFEDF2EF),
    onSurfaceVariant = Color(0xFF5A645F),
    outline = Color(0xFFA8B3AD),
    error = Color(0xFFC98085),
    onError = Color.White
)

@Composable
fun VkAndroidProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}