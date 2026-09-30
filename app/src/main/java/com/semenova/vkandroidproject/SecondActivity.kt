package com.semenova.vkandroidproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semenova.vkandroidproject.ui.theme.VkAndroidProjectTheme

class SecondActivity : ComponentActivity() {
    
    companion object {
        
        const val EXTRA_TEXT = "userText"
        
        fun newIntent(context: Context, text: String): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(EXTRA_TEXT, text)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val text = intent.getStringExtra(EXTRA_TEXT) ?: ""
        
        setContent {
            VkAndroidProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondScreen(
                        text = text,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "Введенный текст: $text",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}