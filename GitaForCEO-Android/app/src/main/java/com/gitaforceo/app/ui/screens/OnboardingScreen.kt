package com.gitaforceo.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gitaforceo.app.ui.theme.*
import kotlinx.coroutines.launch

data class OnboardingPage(
    val icon: ImageVector,
    val iconColor: Color,
    val title: String,
    val subtitle: String,
    val background: Color
)

private val pages = listOf(
    OnboardingPage(
        icon = Icons.Filled.MenuBook,
        iconColor = Saffron,
        title = "Ancient Wisdom,\nModern Leadership",
        subtitle = "The Bhagavad Gita has guided leaders for over 5,000 years. Now, its timeless wisdom is curated specifically for CEOs, board members, and corporate leaders.",
        background = DeepBlue
    ),
    OnboardingPage(
        icon = Icons.Filled.Business,
        iconColor = SacredGold,
        title = "For the Boardroom\n& the Home",
        subtitle = "Every verse comes with two perspectives: corporate wisdom for your professional life, and family wisdom for your personal journey. Because true leadership spans both worlds.",
        background = Color(0xFF2E1E5B)
    ),
    OnboardingPage(
        icon = Icons.Filled.GraphicEq,
        iconColor = LotusRose,
        title = "Listen & Reflect",
        subtitle = "Soothing voice narration brings each verse to life. Listen during your morning routine, commute, or quiet evening moments. Let the wisdom sink deep.",
        background = Color(0xFF263861)
    ),
    OnboardingPage(
        icon = Icons.Filled.WbSunny,
        iconColor = Saffron,
        title = "Daily Wisdom\nAwaits You",
        subtitle = "Start each day with a fresh verse and its leadership insight. Bookmark your favourites, add personal reflections, and track your journey through the Gita.",
        background = Color(0xFF1F3369)
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onComplete: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pages[currentPage].background)
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            val p = pages[page]
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(p.iconColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(p.icon, contentDescription = null, tint = p.iconColor, modifier = Modifier.size(48.dp))
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    p.title,
                    style = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily.Serif, fontSize = 28.sp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    p.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Bottom controls
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page indicators
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pages.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(if (index == currentPage) 24.dp else 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (index == currentPage) Saffron else Color.White.copy(alpha = 0.3f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (currentPage < pages.lastIndex) {
                Button(
                    onClick = { scope.launch { pagerState.animateScrollToPage(currentPage + 1) } },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Saffron),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text("Continue", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onComplete) {
                    Text("Skip", color = Color.White.copy(alpha = 0.6f))
                }
            } else {
                Button(
                    onClick = onComplete,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Saffron),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text("Begin Your Journey", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
