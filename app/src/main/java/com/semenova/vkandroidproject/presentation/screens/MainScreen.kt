package com.semenova.vkandroidproject.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        Toolbar()
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(viewModel.apps) {
                AppListItem(
                    title = it.name,
                    icon = it.icon,
                    description = it.description,
                    category = it.category
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    VkAndroidProjectTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}