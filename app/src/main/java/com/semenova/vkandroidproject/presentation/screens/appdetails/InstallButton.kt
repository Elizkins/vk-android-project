package com.semenova.vkandroidproject.presentation.screens.appdetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semenova.vkandroidproject.R
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme
import kotlin.math.roundToInt

@Composable
fun InstallButton(
    onInstallClick: () -> Unit,
    onCancelInstallClick: () -> Unit,
    onOpenClick: () -> Unit,
    modifier: Modifier = Modifier,
    installState: InstallState
) {
    when (installState) {
        InstallState.Installed -> {
            Button(
                onClick = onOpenClick,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = modifier,
            ) {
                Text(text = stringResource(R.string.open))
            }
        }
        is InstallState.Installing -> {
            val progress by animateFloatAsState(
                targetValue = installState.progress.toFloat(),
                label = "installProgress",
            )
            OutlinedButton(
                onClick = onCancelInstallClick,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = modifier,
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Отменить ${progress.roundToInt()}%",
                )
            }
        }
        InstallState.NotInstalled -> {
            Button(
                onClick = onInstallClick,
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = modifier,
            ) {
                Text(text = stringResource(R.string.install))
                
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkAndroidProjectTheme() {
        InstallButton(
            onInstallClick = {},
            onOpenClick = {},
            onCancelInstallClick = {},
            modifier = Modifier.fillMaxWidth(),
            installState = InstallState.NotInstalled
        )
    }
}