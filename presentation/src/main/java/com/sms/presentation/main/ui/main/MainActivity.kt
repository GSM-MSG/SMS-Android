package com.sms.presentation.main.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sms.presentation.main.ui.main.screen.MainScreen
import com.sms.presentation.main.viewmodel.StudentListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val studentListViewModel by viewModels<StudentListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentListViewModel.getStudentList(1, 20)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Main"
            ) {
                composable("Main") {
                    MainScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as MainActivity)
                    )
                }
            }
        }
    }
}