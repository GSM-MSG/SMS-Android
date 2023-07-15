package com.sms.presentation.main.ui.filter.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.SmsBoxButton
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterDetailStackSearchComponent
import com.sms.presentation.main.ui.filter.component.FilterSelectionControlsGroup
import com.sms.presentation.main.ui.filter.component.FilterSelectorGroup
import com.sms.presentation.main.ui.filter.component.FilterSliderGroup
import com.sms.presentation.main.viewmodel.StudentListViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String
) {
    val scrollState = rememberScrollState()

    BackHandler {
        navController.navigate("Main") {
            popUpTo("Main") { inclusive = false }
        }
    }

    SMSTheme { colors, typography ->
        Scaffold(
            bottomBar = {
                SmsBoxButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "확인",
                    enabled = true
                ) {
                    navController.navigate("Main")
                }
            }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(colors.WHITE)
                    .verticalScroll(scrollState)
            ) {
                TopBarComponent(
                    text = "필터",
                    leftIcon = {
                        Text(
                            text = "초기화",
                            style = typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = colors.BLACK
                        )
                    },
                    rightIcon = { DeleteButtonIcon() },
                    onClickLeftButton = {
                        viewModel.resetFilter()
                    },
                    onClickRightButton = {
                        navController.navigate("Main") {
                            popUpTo("Main") { inclusive = false }
                        }
                    }
                )
                Divider(thickness = 16.dp, color = colors.N10)
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.fillMaxSize()) {
                    FilterSelectorGroup(role = role, viewModel = viewModel)
                    FilterSliderGroup(role = role, viewModel = viewModel)
                    FilterSelectionControlsGroup(role = role, viewModel = viewModel)
                    FilterDetailStackSearchComponent(detailStack = viewModel.detailStackList.value) {
                        navController.navigate("Search")
                    }
                    Spacer(modifier = Modifier.height(it.calculateBottomPadding() + 64.dp))
                }
            }
        }
    }
}