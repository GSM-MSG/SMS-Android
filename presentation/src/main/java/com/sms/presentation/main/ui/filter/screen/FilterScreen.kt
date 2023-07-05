package com.sms.presentation.main.ui.filter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterSelectorComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FilterScreen(
    navController: NavController,
    studentListViewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String
) {
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
                rightIcon = { DeleteButtonIcon() },
                onClickLeftButton = {

                },
                onClickRightButton = {
                    navController.popBackStack()
                }
            )
            Divider(thickness = 16.dp, color = colors.N10)
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    FilterSelectorComponent(
                        title = "학년",
                        itemList = studentListViewModel.selectedGradeList
                    ) {
                        studentListViewModel.selectedGradeList = it.toMutableStateList()
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    FilterSelectorComponent(
                        title = "반",
                        itemList = studentListViewModel.selectedClassList
                    ) {
                        studentListViewModel.selectedClassList = it.toMutableStateList()
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    FilterSelectorComponent(
                        title = "학과",
                        itemList = studentListViewModel.selectedDepartmentList
                    ) {
                        studentListViewModel.selectedDepartmentList = it.toMutableStateList()
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    FilterSelectorComponent(
                        title = "분야",
                        itemList = studentListViewModel.selectedMajorList.map { Pair(it, false) }
                    ) {
//                        studentListViewModel.selectedMajorList = it.toMutableStateList()
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    FilterSelectorComponent(
                        title = "희망 고용 형태",
                        itemList = studentListViewModel.selectedTypeOfEmploymentList
                    ) {
                        studentListViewModel.selectedTypeOfEmploymentList = it.toMutableStateList()
                    }
                }
            }
        }
    }
}