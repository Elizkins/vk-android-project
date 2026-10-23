package com.semenova.vkandroidproject.presentation.screens.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.presentation.ui.theme.CommBg
import com.semenova.vkandroidproject.presentation.ui.theme.FunBg
import com.semenova.vkandroidproject.presentation.ui.theme.PhotoBg
import com.semenova.vkandroidproject.presentation.ui.theme.TravelBg
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme
import com.semenova.vkandroidproject.presentation.ui.theme.WorkBg

@Composable
fun AppListItem(
    modifier: Modifier = Modifier,
    icon: String,
    title: String,
    description: String,
    category: AppCategory
) {
    val categoryColor = when (category) {
        AppCategory.COMM -> CommBg
        AppCategory.FUN -> FunBg
        AppCategory.TRAVEL -> TravelBg
        AppCategory.WORK -> WorkBg
        AppCategory.PHOTO -> PhotoBg
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
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
                title = "Telegram",
                description = "Быстрые сообщения, звонки и каналы",
                category = AppCategory.COMM,
                icon = "https://www.google.com/s2/favicons?domain=telegram.org&sz=128",
            )
        }
    }
}
