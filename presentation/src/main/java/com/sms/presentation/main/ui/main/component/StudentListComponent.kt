package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.response.StudentModel

@Composable
fun StudentListComponent(
    listState: LazyListState,
    progressState: Boolean,
    studentList: List<StudentModel>,
    onItemClick: () -> Unit
) {
    SMSTheme { colors, _ ->
        LazyColumn(state = listState) {
            items(studentList.size) {
                StudentListItem(
                    profileImageUrl = studentList[it].profileImg,
                    major = studentList[it].major,
                    name = studentList[it].name,
                    teckStackList = studentList[it].techStack
                ) {
                    onItemClick()
                }
                Divider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = colors.N10,
                    thickness = 1.dp
                )
            }
            if (progressState) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}