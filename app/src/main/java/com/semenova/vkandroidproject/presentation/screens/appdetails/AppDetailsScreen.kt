package com.semenova.vkandroidproject.presentation.screens.appdetails

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.semenova.vkandroidproject.R
import com.semenova.vkandroidproject.data.AppData
import com.semenova.vkandroidproject.domain.AppCategory
import com.semenova.vkandroidproject.domain.AppItem
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme

@Composable
fun AppDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AppScreenViewModel = hiltViewModel(),
    appId: Int,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (Int) -> Unit,
) {
    val app = remember { viewModel.getAppById(appId) }
    
    val context = LocalContext.current
    val underDevelopmentText = stringResource(R.string.under_developement)
    
    var descriptionCollapsed by remember { mutableStateOf(false) }
    
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp),
        ) {
            Toolbar(
                onBackClick = onBackClick,
                onShareClick = {
                    Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                },
            )
            Spacer(Modifier.height(8.dp))
            AppDetailsHeader(
                app = app,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(Modifier.height(16.dp))
            InstallButton(
                onClick = {
                    Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(12.dp))
            ScreenshotsList(
                screenshotUrlList = app.screenshotUrlList,
                contentPadding = PaddingValues(horizontal = 16.dp),
            )
            Spacer(Modifier.height(12.dp))
            AppDescription(
                description = app.description,
                collapsed = descriptionCollapsed,
                onReadMoreClick = {
                    descriptionCollapsed = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Spacer(Modifier.height(12.dp))
            Showcase(
                appList = viewModel.apps,
                contentPadding = PaddingValues(horizontal = 16.dp),
                onAppClick = onShowcaseAppClick
            )
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outlineVariant,
            )
            Spacer(Modifier.height(12.dp))
            Developer(
                name = app.developer,
                onClick = {
                    Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkAndroidProjectTheme {
        AppDetailsScreen(
            modifier = Modifier.fillMaxSize(),
            appId = 0,
            onBackClick = {},
            onShowcaseAppClick = {}
        )
    }
}