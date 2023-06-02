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
        StudentListComponent(studentList = listOf(
            StudentData(name = "이현빈", major = "Android Dev", profileImageUrl = "", teckStackList = listOf("Kotlin","Retrofit")),
            StudentData(name = "김현승", major = "Android Dev", profileImageUrl = "", teckStackList = listOf("Compose","MVVM")),
            StudentData(name = "최형우", major = "iOS Dev", profileImageUrl = "", teckStackList = listOf("Swift","Xcode")),
            StudentData(name = "김성훈", major = "iOS Dev", profileImageUrl = "", teckStackList = listOf("RxSwift","MVI")),
            StudentData(name = "김시훈", major = "BackEnd Dev", profileImageUrl = "", teckStackList = listOf("Java","Spring")),
            StudentData(name = "전승원", major = "BackEnd Dev", profileImageUrl = "", teckStackList = listOf("JS","Node.js")),
            StudentData(name = "변찬우", major = "FrontEnd Dev", profileImageUrl = "", teckStackList = listOf("HTML","CSS","JS")),
            StudentData(name = "김준", major = "Design", profileImageUrl = "", teckStackList = listOf("Figma","Adobe"))
        )) {
            //TODO (Kimhyunseung) : 디테일 페이지로 이동
        }
    }
}