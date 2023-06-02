package com.sms.presentation.main.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sms.presentation.main.ui.main.screen.MainScreen
import com.sms.presentation.main.ui.main.screen.StudentFilteringScreen
import com.sms.presentation.main.ui.main.screen.TectStackSearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Main"
            ) {
                composable("Main") {
                    MainScreen(
                        navController = navController
                    )
                }
                composable("StudentFiltering") {
                    StudentFilteringScreen(
                        navController = navController
                    )
                }
                composable("TectStackSearch") {
                    TectStackSearchScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}