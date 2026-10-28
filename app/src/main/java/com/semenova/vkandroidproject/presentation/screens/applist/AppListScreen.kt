package com.semenova.vkandroidproject.presentation.screens.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.widget.ContentLoadingProgressBar
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semenova.vkandroidproject.R
import com.semenova.vkandroidproject.presentation.ui.theme.VkAndroidProjectTheme
import com.semenova.vkandroidproject.presentation.utils.CustomIcons
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Composable
fun AppListScreen(
    modifier: Modifier = Modifier,
    viewModel: AppListScreenViewModel = hiltViewModel(),
    onAppClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val events = viewModel.events
    
    val snackbarHostState = remember { SnackbarHostState() }
    
    ObserveEvents(
        events = events,
        snackbarHostState = snackbarHostState,
    )
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Toolbar(onClick = {
                viewModel.sendMessage()
            })
            when (state) {
                is AppListScreenState.Content -> {
                    val apps = (state as AppListScreenState.Content).apps
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        itemsIndexed(apps) { index, app ->
                            AppListItem(
                                modifier = Modifier.combinedClickable(onClick = { onAppClick(app.id) }),
                                title = app.name,
                                icon = app.iconUrl,
                                description = app.description,
                                category = app.category
                            )
                            if (index < apps.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant
                                )
                            }
                        }
                    }
                }
                
                AppListScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                
                is AppListScreenState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.app_list_something_went_wrong),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
            
        }
    }
}

@Composable
private fun ObserveEvents(
    events: Flow<AppListScreenEvent>,
    snackbarHostState: SnackbarHostState,
) {
    val snackbarMessage = stringResource(R.string.app_list_something_important)
    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppListScreenEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(snackbarMessage)
                }
            }
        }
    }
}

@Preview
@Composable
fun AppListScreenPreview() {
    VkAndroidProjectTheme {
        AppListScreen(
            onAppClick = {}
        )
    }
}