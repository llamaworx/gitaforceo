package com.gitaforceo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitaforceo.app.ui.navigation.GitaNavHost
import com.gitaforceo.app.ui.screens.OnboardingScreen
import com.gitaforceo.app.ui.theme.GitaForCEOTheme
import com.gitaforceo.app.viewmodel.GitaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitaForCEOTheme {
                val viewModel: GitaViewModel = viewModel()
                val hasCompletedOnboarding by viewModel.hasCompletedOnboarding.collectAsState()

                if (hasCompletedOnboarding) {
                    GitaNavHost(viewModel = viewModel)
                } else {
                    OnboardingScreen(onComplete = { viewModel.completeOnboarding() })
                }
            }
        }
    }
}
