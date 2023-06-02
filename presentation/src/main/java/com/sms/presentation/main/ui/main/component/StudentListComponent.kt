package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun StudentListComponent(
    studentList: List<String>
) {
    LazyColumn {
        items(studentList.size) {
            StudentListItem(
                profileImageUrl = "",
                major = "iOS Dev",
                name = studentList[it],
                teckStackList = listOf("figma", "figma")
            )
            ListItemSpacer()
        }
    }
}