package com.gitaforceo.app.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gitaforceo.app.service.VoiceService
import com.gitaforceo.app.ui.theme.*
import com.gitaforceo.app.viewmodel.GitaViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceConversationScreen(viewModel: GitaViewModel, navController: NavController) {
    val voiceService = viewModel.voiceService
    val conversationService = viewModel.conversationService
    val scope = rememberCoroutineScope()

    val voiceState by voiceService.voiceState.collectAsState()
    val spokenText by voiceService.spokenText.collectAsState()
    val responseText by voiceService.responseText.collectAsState()
    val errorMessage by voiceService.errorMessage.collectAsState()
    val amplitude by voiceService.amplitude.collectAsState()
    val isLoading by conversationService.isLoading.collectAsState()

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            voiceService.startRecording()
        }
    }

    // Continuous conversation mode
    var continuousMode by remember { mutableStateOf(true) }

    // Auto-listen after response finishes speaking
    LaunchedEffect(voiceState) {
        if (voiceState == VoiceService.VoiceState.IDLE && continuousMode && responseText.isNotBlank()) {
            // Small pause before listening again
            delay(800)
            if (voiceService.hasMicPermission()) {
                voiceService.startRecording()
            }
        }
    }

    fun startListening() {
        if (voiceService.hasMicPermission()) {
            voiceService.startRecording()
        } else {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    fun stopAndProcess() {
        voiceService.stopRecording()
        scope.launch {
            val transcript = voiceService.transcribeAudio()
            if (transcript != null) {
                // Start a conversation if none exists
                if (conversationService.currentConversation.value == null) {
                    conversationService.startNewConversation()
                }
                // Send to advisor
                conversationService.sendMessage(transcript)

                // Wait for response
                // Poll until loading is done
                while (conversationService.isLoading.value) {
                    delay(200)
                }

                // Get latest advisor message
                val conv = conversationService.currentConversation.value
                val lastAdvisor = conv?.messages?.lastOrNull {
                    it.role == com.gitaforceo.app.model.ChatMessage.Role.ADVISOR && !it.isStreaming
                }
                if (lastAdvisor != null) {
                    voiceService.speakText(lastAdvisor.content)
                }
            }
        }
    }

    // Background gradient
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        DeepBlue,
                        Color(0xFF1A1040),
                        Color(0xFF0D0D2B)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    voiceService.stopAll()
                    navController.popBackStack()
                }) {
                    Icon(Icons.Filled.Close, contentDescription = "Close", tint = Color.White)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "Voice Advisor",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))

                // Continuous mode toggle
                IconButton(onClick = { continuousMode = !continuousMode }) {
                    Icon(
                        if (continuousMode) Icons.Filled.Loop else Icons.Filled.TouchApp,
                        contentDescription = if (continuousMode) "Continuous mode" else "Manual mode",
                        tint = if (continuousMode) Saffron else Color.White.copy(alpha = 0.5f)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.3f))

            // Status text
            Text(
                when (voiceState) {
                    VoiceService.VoiceState.IDLE -> if (responseText.isBlank()) "Tap the mic to begin" else "Tap to ask another question"
                    VoiceService.VoiceState.LISTENING -> "Listening..."
                    VoiceService.VoiceState.PROCESSING -> "Understanding your words..."
                    VoiceService.VoiceState.SPEAKING -> "Gita Advisor is speaking..."
                },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Spoken text display
            if (spokenText.isNotBlank()) {
                Card(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Saffron.copy(alpha = 0.15f))
                ) {
                    Text(
                        "\"$spokenText\"",
                        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                        color = Saffron,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Central mic button with ripple animation
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                // Animated ripple rings when listening
                if (voiceState == VoiceService.VoiceState.LISTENING) {
                    repeat(3) { index ->
                        PulsingRing(
                            delay = index * 400,
                            amplitude = amplitude,
                            color = Saffron
                        )
                    }
                }

                // Pulsing ring when speaking
                if (voiceState == VoiceService.VoiceState.SPEAKING) {
                    repeat(2) { index ->
                        PulsingRing(
                            delay = index * 600,
                            amplitude = 0.3f,
                            color = SacredGold
                        )
                    }
                }

                // Processing spinner
                if (voiceState == VoiceService.VoiceState.PROCESSING || isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(160.dp),
                        color = Saffron.copy(alpha = 0.3f),
                        strokeWidth = 2.dp
                    )
                }

                // Main mic button
                val buttonColor = when (voiceState) {
                    VoiceService.VoiceState.IDLE -> Saffron
                    VoiceService.VoiceState.LISTENING -> LotusRose
                    VoiceService.VoiceState.PROCESSING -> SacredGold
                    VoiceService.VoiceState.SPEAKING -> ForestGreen
                }

                val buttonScale = if (voiceState == VoiceService.VoiceState.LISTENING) {
                    1f + amplitude * 0.15f
                } else {
                    1f
                }

                Surface(
                    modifier = Modifier
                        .size(100.dp)
                        .scale(buttonScale)
                        .clickable {
                            when (voiceState) {
                                VoiceService.VoiceState.IDLE -> startListening()
                                VoiceService.VoiceState.LISTENING -> stopAndProcess()
                                VoiceService.VoiceState.PROCESSING -> { /* Wait */ }
                                VoiceService.VoiceState.SPEAKING -> voiceService.stopPlayback()
                            }
                        },
                    shape = CircleShape,
                    color = buttonColor,
                    shadowElevation = 8.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            when (voiceState) {
                                VoiceService.VoiceState.IDLE -> Icons.Filled.Mic
                                VoiceService.VoiceState.LISTENING -> Icons.Filled.Stop
                                VoiceService.VoiceState.PROCESSING -> Icons.Filled.HourglassTop
                                VoiceService.VoiceState.SPEAKING -> Icons.Filled.VolumeUp
                            },
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action hint
            Text(
                when (voiceState) {
                    VoiceService.VoiceState.IDLE -> "Tap mic to speak"
                    VoiceService.VoiceState.LISTENING -> "Tap to stop & send"
                    VoiceService.VoiceState.PROCESSING -> "Please wait..."
                    VoiceService.VoiceState.SPEAKING -> "Tap to interrupt"
                },
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Response text (scrollable)
            if (responseText.isNotBlank() && voiceState == VoiceService.VoiceState.SPEAKING) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .heightIn(max = 200.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.08f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        Text(
                            "Gita Advisor",
                            style = MaterialTheme.typography.labelSmall,
                            color = SacredGold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            responseText,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Error message
            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = LotusRose.copy(alpha = 0.15f))
                ) {
                    Text(
                        errorMessage ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = LotusRose,
                        modifier = Modifier.padding(12.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Voice selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                VoiceService.VoiceOption.entries.forEach { voice ->
                    val isSelected = voiceService.selectedVoice == voice
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { voiceService.setVoice(voice) },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) Saffron.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.05f),
                        border = if (isSelected) {
                            androidx.compose.foundation.BorderStroke(1.dp, Saffron)
                        } else null
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                if (voice.gender == "MALE") Icons.Filled.Person else Icons.Filled.Person,
                                contentDescription = null,
                                tint = if (isSelected) Saffron else Color.White.copy(alpha = 0.4f),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                voice.displayName.substringBefore(" ("),
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected) Saffron else Color.White.copy(alpha = 0.5f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            // Bottom info
            Row(
                modifier = Modifier.padding(bottom = 24.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    if (continuousMode) Icons.Filled.Loop else Icons.Filled.TouchApp,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.3f),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    if (continuousMode) "Continuous conversation" else "Manual mode",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
private fun PulsingRing(delay: Int, amplitude: Float, color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.5f + amplitude * 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, delayMillis = delay, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringScale"
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, delayMillis = delay, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringAlpha"
    )

    Box(
        modifier = Modifier
            .size(120.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(color.copy(alpha = alpha))
    )
}
