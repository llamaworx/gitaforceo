package com.gitaforceo.app.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gitaforceo.app.data.GitaData
import com.gitaforceo.app.model.*
import com.gitaforceo.app.ui.navigation.Screen
import com.gitaforceo.app.ui.theme.*
import com.gitaforceo.app.viewmodel.GitaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvisorScreen(viewModel: GitaViewModel, navController: NavController) {
    val conversationService = viewModel.conversationService
    var inputText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gita Advisor") },
                actions = {
                    IconButton(onClick = { conversationService.startNewConversation() }) {
                        Icon(Icons.Filled.Add, contentDescription = "New conversation", tint = Saffron)
                    }
                }
            )
        }
    ) { padding ->
        val conversation = conversationService.currentConversation

        if (conversation == null) {
            // Welcome view
            WelcomeView(
                modifier = Modifier.padding(padding),
                onStartConversation = { conversationService.startNewConversation() },
                onSelectScenario = { scenario ->
                    conversationService.startNewConversation(scenario)
                    scope.launch {
                        conversationService.sendMessage(scenario.prompt)
                    }
                }
            )
        } else {
            // Chat view
            Column(modifier = Modifier.padding(padding).fillMaxSize()) {
                val messages = conversation.messages.filter { it.role != ChatMessage.Role.SYSTEM }
                val listState = rememberLazyListState()

                LaunchedEffect(messages.size) {
                    if (messages.isNotEmpty()) {
                        listState.animateScrollToItem(messages.lastIndex)
                    }
                }

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(messages) { message ->
                        MessageBubble(message = message, navController = navController)
                    }
                    if (conversationService.isLoading) {
                        item { TypingIndicator() }
                    }
                }

                Divider()

                // Input bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Ask the Gita...") },
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Saffron, cursorColor = Saffron),
                        maxLines = 3
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (inputText.isNotBlank()) {
                        IconButton(
                            onClick = {
                                val text = inputText.trim()
                                inputText = ""
                                scope.launch { conversationService.sendMessage(text) }
                            }
                        ) {
                            Icon(Icons.Filled.Send, contentDescription = "Send", tint = Saffron)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WelcomeView(
    modifier: Modifier = Modifier,
    onStartConversation: () -> Unit,
    onSelectScenario: (ScenarioTemplate) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Avatar
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(listOf(DeepBlue, Color(0xFF2E1E5B)))
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("\uD83D\uDE4F", fontSize = 44.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Namaste", style = MaterialTheme.typography.displayLarge.copy(fontFamily = FontFamily.Serif, fontSize = 32.sp))
        Text("Your Gita Advisor", style = MaterialTheme.typography.titleMedium, color = Saffron)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Ask me anything about leadership, ethics,\ndecision-making, family, or inner peace.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onStartConversation,
            colors = ButtonDefaults.buttonColors(containerColor = Saffron),
            shape = RoundedCornerShape(24.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 14.dp)
        ) {
            Icon(Icons.Filled.Forum, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Start a Conversation")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Scenarios by category
        ScenarioTemplate.Category.entries.forEach { category ->
            val scenarios = GitaData.scenarios.filter { it.category == category }
            if (scenarios.isNotEmpty()) {
                Text(
                    category.displayName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Saffron,
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(scenarios) { scenario ->
                        ScenarioChip(scenario = scenario, onClick = { onSelectScenario(scenario) })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun ScenarioChip(scenario: ScenarioTemplate, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = onClick).width(220.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.AutoAwesome, contentDescription = null, tint = Saffron, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(scenario.title, style = MaterialTheme.typography.labelLarge)
                Text(scenario.description, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage, navController: NavController) {
    val isUser = message.role == ChatMessage.Role.USER

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        if (!isUser) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(DeepBlue, Color(0xFF2E1E5B)))),
                contentAlignment = Alignment.Center
            ) {
                Text("\uD83D\uDE4F", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Card(
            modifier = Modifier.widthIn(max = 300.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isUser) Saffron else MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                if (!isUser) {
                    Text("Gita Advisor", style = MaterialTheme.typography.labelSmall, color = Saffron)
                    Spacer(modifier = Modifier.height(4.dp))
                }

                if (message.isStreaming) {
                    TypingIndicator()
                } else {
                    Text(
                        message.content,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface
                    )
                }

                // Verse chips
                if (message.referencedVerses.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        message.referencedVerses.take(3).forEach { verseId ->
                            val verse = GitaData.allVerses.find { it.id == verseId }
                            if (verse != null) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Saffron.copy(alpha = 0.1f),
                                    modifier = Modifier.clickable {
                                        navController.navigate(Screen.VerseDetail.createRoute(verseId))
                                    }
                                ) {
                                    Text(
                                        verse.displayReference,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Saffron,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TypingIndicator() {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.padding(4.dp)) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Saffron.copy(alpha = 0.6f))
            )
        }
    }
}
