package com.sms.presentation.main.ui.filter.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.divider.SmsDivider
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterSelectorComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String
) {
    val selectedItemList = remember {
        mutableStateListOf<String>()
    }

    SMSTheme { colors, typography ->
        Column(
            Modifier
                .fillMaxSize()
                .background(colors.WHITE)
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
                rightIcon = { DeleteButtonIcon() }
            )
            SmsDivider()
            Spacer(modifier = Modifier.height(20.dp))
            FilterSelectorComponent(
                title = "분야",
                itemList = listOf(
                    "FrontEnd",
                    "BackEnd",
                    "Android",
                    "iOS",
                    "Game",
                    "Cyber Security",
                    "Design",
                    "AI",
                    "IoT",
                    "기타"
                ),
                selectedItemList = selectedItemList
            ) {
                Log.d("selected", it.toString())
            }
        }
    }
}