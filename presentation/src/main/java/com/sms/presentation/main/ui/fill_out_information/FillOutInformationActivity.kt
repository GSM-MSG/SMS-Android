package com.sms.presentation.main.ui.fill_out_information

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class FillOutInformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Profile") {
                composable("Profile") { ProfileScreen(navController = navController) }
                composable("SchoolLife") { SchoolLifeScreen(navController = navController) }
                composable("WorkCondition") { WorkConditionScreen(navController = navController) }
                composable("MilitaryService") { MilitaryServiceScreen(navController = navController) }
                composable("Certification") { CertificationScreen(navController = navController) }
                composable("ForeignLanguage") { ForeignLanguageScreen(navController = navController) }
            }
        }
    }
}