package com.semenova.vkandroidproject.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semenova.vkandroidproject.R
import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme


@Composable
fun AppListItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    description: String,
    category: AppCategory
) {
    val categoryColor = when (category) {
        AppCategory.COMM -> {
            Color.Blue
        }
        
        AppCategory.FUN -> {
            Color.Cyan
        }
        
        AppCategory.TRAVEL -> {
            Color.Green
        }
        
        AppCategory.WORK -> {
            Color.Gray
        }
        
        AppCategory.PHOTO -> {
            Color.Yellow
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            modifier = Modifier
                .padding(top = 4.dp),
            imageVector = icon,
            contentDescription = stringResource(R.string.main_screen_app_list),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(categoryColor)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = category.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        
    }
}

@Preview
@Composable
fun AppListItemPreview() {
    VkAndroidProjectTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppListItem(
                modifier = Modifier.padding(innerPadding),
                title = "Мессенджер",
                description = "Быстрые сообщения и звонки",
                category = AppCategory.COMM,
                icon = Icons.AutoMirrored.Filled.Message
            )
        }
    }
}
