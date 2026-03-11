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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class StoreAppUi(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val iconText: String,
    val iconColor: Color,
)

private val apps = listOf(
    StoreAppUi(
        id = "sber",
        title = "СберБанк Онлайн — с Салютом",
        subtitle = "Больше чем банк",
        category = "Финансы",
        iconText = "С",
        iconColor = Color(0xFF42C05E),
    ),
    StoreAppUi(
        id = "browser",
        title = "Яндекс.Браузер — с Алисой",
        subtitle = "Быстрый и безопасный браузер",
        category = "Инструменты",
        iconText = "Я",
        iconColor = Color(0xFFFF4B3A),
    ),
    StoreAppUi(
        id = "mail",
        title = "Почта Mail.ru",
        subtitle = "Почтовый клиент для любых ящиков",
        category = "Инструменты",
        iconText = "@",
        iconColor = Color(0xFF3366FF),
    ),
    StoreAppUi(
        id = "navigator",
        title = "Яндекс Навигатор",
        subtitle = "Парковки и заправки — по пути",
        category = "Транспорт",
        iconText = "N",
        iconColor = Color(0xFFFFD54F),
    ),
    StoreAppUi(
        id = "mts",
        title = "Мой МТС",
        subtitle = "Мой МТС — центр экосистемы МТС",
        category = "Инструменты",
        iconText = "М",
        iconColor = Color(0xFFFF4A4A),
    ),
    StoreAppUi(
        id = "yandex",
        title = "Яндекс — с Алисой",
        subtitle = "Яндекс — поиск всегда под рукой",
        category = "Инструменты",
        iconText = "Я",
        iconColor = Color(0xFFFF7A45),
    ),
)

@Composable
fun AppsListScreen(
    onAppClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .safeDrawingPadding()
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
                    onClick = { onAppClick(app.id) },
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
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .background(app.iconColor, RoundedCornerShape(16.dp)),
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
                    modifier = Modifier.weight(1f)
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