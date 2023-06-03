package com.sms.presentation.main.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sms.presentation.main.ui.main.component.MainScreenTopBar
import com.sms.presentation.main.ui.main.component.StudentListComponent
import com.sms.presentation.main.ui.main.data.StudentData

@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MainScreenTopBar(
            profileImageUrl = "",
            filterButtonOnClick = { /*TODO (KimHyunseung) : 필터 Screen으로 이동*/ },
            profileButtonOnClick = { /*TODO (KimHyunseung) : 마이페이지로 이동*/ }
        )
        Spacer(modifier = Modifier.size(16.dp))
        StudentListComponent(
            studentList = listOf(
                StudentData(
                    name = "이현빈",
                    major = "Android Dev",
                    profileImageUrl = "",
                    teckStackList = listOf(
                        "Spring Boot",
                        "Kot",
                        "Kot",
                        "Android",
                        "Android",
                        "Android",
                        "Android"
                    )
                )
            )
        ) {
            //TODO (Kimhyunseung) : 디테일 페이지로 이동
        }
    }
}