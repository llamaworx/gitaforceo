package com.gitaforceo.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gitaforceo.app.model.Chapter
import com.gitaforceo.app.ui.navigation.Screen
import com.gitaforceo.app.ui.theme.*
import com.gitaforceo.app.viewmodel.GitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChaptersScreen(viewModel: GitaViewModel, navController: NavController) {
    var expandedChapter by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Chapters") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Each chapter reveals a different path of yoga — from action to devotion to knowledge",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(viewModel.chapters) { chapter ->
                ChapterCard(
                    chapter = chapter,
                    isExpanded = expandedChapter == chapter.id,
                    onToggle = {
                        expandedChapter = if (expandedChapter == chapter.id) null else chapter.id
                    },
                    onVerseClick = { verseId ->
                        navController.navigate(Screen.VerseDetail.createRoute(verseId))
                    }
                )
            }
        }
    }
}

@Composable
private fun ChapterCard(
    chapter: Chapter,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onVerseClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggle)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = Saffron.copy(alpha = 0.15f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            "${chapter.id}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Saffron
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(chapter.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        chapter.sanskritName,
                        style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Expanded content
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp).padding(bottom = 16.dp)) {
                    Divider()
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        chapter.summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // CEO Insight
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Saffron.copy(alpha = 0.06f)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = Saffron, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("CEO Insight", style = MaterialTheme.typography.labelSmall, color = Saffron)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                chapter.ceoInsight,
                                style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "${chapter.verses.size} Key Verses",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    chapter.verses.forEachIndexed { index, verse ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onVerseClick(verse.id) }
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                verse.displayReference,
                                style = MaterialTheme.typography.labelSmall,
                                color = Saffron,
                                modifier = Modifier.width(60.dp)
                            )
                            Text(
                                verse.translation,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                Icons.Filled.ChevronRight,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (index < chapter.verses.lastIndex) {
                            Divider(modifier = Modifier.padding(vertical = 2.dp))
                        }
                    }
                }
            }
        }
    }
}
