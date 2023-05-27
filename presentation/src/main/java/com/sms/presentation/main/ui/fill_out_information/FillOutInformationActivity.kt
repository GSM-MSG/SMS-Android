package com.sms.presentation.main.ui.fill_out_information

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sms.presentation.main.ui.fill_out_information.screen.*
import com.sms.presentation.main.viewmodel.FillOutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FillOutInformationActivity : ComponentActivity() {

    private val viewModel by viewModels<FillOutViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMajorList()

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Profile"
            ) {
                composable("Profile") {
                    ProfileScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity),
                    )
                }
                composable("SchoolLife") {
                    SchoolLifeScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("WorkCondition") {
                    WorkConditionScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("MilitaryService") {
                    MilitaryServiceScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("Certification") {
                    CertificationScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
                composable("ForeignLanguage") {
                    ForeignLanguageScreen(
                        navController = navController,
                        viewModel = viewModel(LocalContext.current as FillOutInformationActivity)
                    )
                }
            }
        }

//        observeData()
    }

//    private fun observeData() {
//        lifecycleScope.launch {
//            viewModel.getMajorListEvent.observe(this@FillOutInformationActivity) { data ->
//                when (data) {
//                    is Event.Success -> {
//                        showSuccessPage(data.data!!.major)
//                    }
//                    is Event.Loading -> {
//                    }
//                    else -> {
//                    }
//                }
//            }
//        }
//    }

    private fun showSuccessPage(list: List<String>) {

    }
}