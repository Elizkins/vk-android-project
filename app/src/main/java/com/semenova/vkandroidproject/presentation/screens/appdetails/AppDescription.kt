package com.semenova.vkandroidproject.presentation.screens.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semenova.vkandroidproject.R
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme

@Composable
fun AppDescription(
    description: String,
    collapsed: Boolean,
    onReadMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = stringResource(R.string.app_details_description),
            style = MaterialTheme.typography.labelLarge,
            )
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.labelSmall,
            maxLines = if (collapsed) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (!collapsed) {
            TextButton(
                onClick = onReadMoreClick,
                contentPadding = PaddingValues(horizontal = 0.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_details_read_more),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewShort() {
    VkAndroidProjectTheme {
        AppDescription(
            description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!",
            onReadMoreClick = {},
            collapsed = false,
        )
    }
}

@Preview
@Composable
private fun PreviewCollapsed() {
    VkAndroidProjectTheme {
        AppDescription(
            description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!",
            onReadMoreClick = {},
            collapsed = true,
        )
    }
}