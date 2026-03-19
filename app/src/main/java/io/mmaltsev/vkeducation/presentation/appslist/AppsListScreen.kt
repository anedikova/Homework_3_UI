package io.mmaltsev.vkeducation.presentation.appslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppsListRoute(
    onAppClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppsListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is AppsListEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
    ) { innerPadding ->
        when (val currentState = uiState) {
            AppsListUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is AppsListUiState.Content -> {
                AppsListScreen(
                    apps = currentState.apps,
                    onAppClick = onAppClick,
                    onLogoClick = viewModel::onLogoClick,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

@Composable
fun AppsListScreen(
    apps: List<StoreAppUi>,
    onAppClick: (String) -> Unit,
    onLogoClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .safeDrawingPadding(),
    ) {
        RuStoreTopBar()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = apps,
                key = { it.id },
            ) { app ->
                AppListItem(
                    app = app,
                    onItemClick = { onAppClick(app.id) },
                    onLogoClick = { onLogoClick(app.title) },
                )
            }
        }
    }
}

@Composable
private fun RuStoreTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2E66FF))
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(Color.White, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "R",
                color = Color(0xFF2E66FF),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = "RuStore",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun AppListItem(
    app: StoreAppUi,
    onItemClick: () -> Unit,
    onLogoClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .background(app.iconColor, RoundedCornerShape(16.dp))
                        .clickable(onClick = onLogoClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = app.iconText,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.size(14.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = onItemClick),
                ) {
                    Text(
                        text = app.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF1E1E1E),
                        fontWeight = FontWeight.SemiBold,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = app.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4A4A4A),
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = app.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF9A9A9A),
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFFEAEAEA))
        }
    }
}