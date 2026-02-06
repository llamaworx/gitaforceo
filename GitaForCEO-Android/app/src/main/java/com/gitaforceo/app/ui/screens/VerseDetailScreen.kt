package com.gitaforceo.app.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gitaforceo.app.data.GitaData
import com.gitaforceo.app.model.Verse
import com.gitaforceo.app.ui.theme.*
import com.gitaforceo.app.viewmodel.GitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerseDetailScreen(verseId: String, viewModel: GitaViewModel, navController: NavController) {
    val verse = GitaData.allVerses.find { it.id == verseId } ?: return
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Translation", "Corporate", "Family")

    LaunchedEffect(verseId) {
        viewModel.bookmarkService.markAsRead(verseId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(verse.displayReference) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.bookmarkService.toggleBookmark(verse.id) }) {
                        Icon(
                            if (viewModel.bookmarkService.isBookmarked(verse.id)) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = Saffron
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Sanskrit section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Saffron.copy(alpha = 0.08f), SacredGold.copy(alpha = 0.04f))
                        )
                    )
                    .border(1.dp, Saffron.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        verse.sanskrit,
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Serif),
                        textAlign = TextAlign.Center,
                        lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.3
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(modifier = Modifier.width(40.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        verse.transliteration,
                        style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                contentColor = Saffron,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                title,
                                color = if (selectedTab == index) Saffron else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Content
            when (selectedTab) {
                0 -> {
                    WisdomCard("Translation", verse.translation, Icons.Filled.MenuBook, DeepBlue)
                    WisdomCard("Commentary", verse.commentary, Icons.Filled.FormatQuote, ForestGreen)
                }
                1 -> WisdomCard("For the Boardroom", verse.corporateWisdom, Icons.Filled.Business, Saffron)
                2 -> WisdomCard("For the Home", verse.familyWisdom, Icons.Filled.Home, LotusRose)
            }

            // Themes
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Themes", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        verse.themes.forEach { theme ->
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = theme.color.copy(alpha = 0.1f)
                            ) {
                                Text(
                                    theme.displayName,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = theme.color,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Listen button
            Button(
                onClick = {
                    val text = "${verse.displayReference}. ${verse.translation}. Commentary: ${verse.commentary}. Corporate Wisdom: ${verse.corporateWisdom}. Family Wisdom: ${verse.familyWisdom}"
                    viewModel.audioService.speakVerse(text)
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Saffron),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.PlayCircleFilled, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Listen to Full Verse")
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun WisdomCard(
    title: String, content: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(title, style = MaterialTheme.typography.labelLarge, color = color)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.2
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRow(
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
        content = { content() }
    )
}
