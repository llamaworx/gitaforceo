package com.gitaforceo.app.ui.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gitaforceo.app.ui.screens.*
import com.gitaforceo.app.ui.theme.Saffron
import com.gitaforceo.app.viewmodel.GitaViewModel

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Advisor : Screen("advisor")
    data object Chapters : Screen("chapters")
    data object Themes : Screen("themes")
    data object Search : Screen("search")
    data object Settings : Screen("settings")
    data object VerseDetail : Screen("verse/{verseId}") {
        fun createRoute(verseId: String) = "verse/$verseId"
    }
    data object ThemeDetail : Screen("theme/{themeName}") {
        fun createRoute(themeName: String) = "theme/$themeName"
    }
    data object DailyWisdom : Screen("daily_wisdom")
    data object VoiceConversation : Screen("voice_conversation")
}

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, "Home", Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem(Screen.Advisor, "Advisor", Icons.Filled.Forum, Icons.Outlined.Forum),
    BottomNavItem(Screen.Chapters, "Chapters", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
    BottomNavItem(Screen.Themes, "Themes", Icons.Filled.GridView, Icons.Outlined.GridView),
    BottomNavItem(Screen.Search, "Search", Icons.Filled.Search, Icons.Outlined.Search),
)

@Composable
fun GitaNavHost(viewModel: GitaViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in bottomNavItems.map { it.screen.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentRoute == item.screen.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (currentRoute != item.screen.route) {
                                    navController.navigate(item.screen.route) {
                                        popUpTo(Screen.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label, style = MaterialTheme.typography.labelSmall) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Saffron,
                                selectedTextColor = Saffron,
                                indicatorColor = Saffron.copy(alpha = 0.12f)
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Advisor.route) {
                AdvisorScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Chapters.route) {
                ChaptersScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Themes.route) {
                ThemesScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Search.route) {
                SearchScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Settings.route) {
                SettingsScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.DailyWisdom.route) {
                DailyWisdomScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.VoiceConversation.route) {
                VoiceConversationScreen(viewModel = viewModel, navController = navController)
            }
            composable(
                route = Screen.VerseDetail.route,
                arguments = listOf(navArgument("verseId") { type = NavType.StringType })
            ) { backStackEntry ->
                val verseId = backStackEntry.arguments?.getString("verseId") ?: return@composable
                VerseDetailScreen(verseId = verseId, viewModel = viewModel, navController = navController)
            }
            composable(
                route = Screen.ThemeDetail.route,
                arguments = listOf(navArgument("themeName") { type = NavType.StringType })
            ) { backStackEntry ->
                val themeName = backStackEntry.arguments?.getString("themeName") ?: return@composable
                ThemeDetailScreen(themeName = themeName, viewModel = viewModel, navController = navController)
            }
        }
    }
}
