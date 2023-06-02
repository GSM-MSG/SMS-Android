package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.sms.presentation.main.ui.main.data.StudentData

@Composable
fun StudentListComponent(
    studentList: List<StudentData>,
    onItemClick: () -> Unit
) {
    LazyColumn {
        items(studentList.size) {
            with(studentList[it]) {
                StudentListItem(
                    profileImageUrl = profileImageUrl,
                    major = major,
                    name = name,
                    teckStackList = teckStackList
                ) {
                    onItemClick()
                }
            }
            ListItemSpacer()
        }
    }
}