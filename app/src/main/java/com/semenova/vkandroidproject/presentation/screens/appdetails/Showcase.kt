package com.semenova.vkandroidproject.presentation.screens.appdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.semenova.vkandroidproject.R
import com.semenova.vkandroidproject.data.AppData
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.presentation.screens.applist.AppListItem
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme

@Composable
fun Showcase(
    appList: List<AppItem>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onAppClick: (Int) -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.app_details_showcase),
            modifier = Modifier.padding(contentPadding),
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(Modifier.height(8.dp))
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = contentPadding,
            pageSpacing = 8.dp,
            state = rememberPagerState { if (appList.size / 3 < 3) appList.size / 3 else 3 },
        ) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..<3).forEach {
                    val app = appList[index * 3 + it]
                    AppListItem(
                        modifier = Modifier.combinedClickable(onClick = { onAppClick(app.id) }),
                        title = app.name,
                        icon = app.iconUrl,
                        description = app.description,
                        category = app.category
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkAndroidProjectTheme() {
        Showcase(
            appList = AppData.apps,
            contentPadding = PaddingValues(horizontal = 16.dp),
            onAppClick = {}
        )
    }
}