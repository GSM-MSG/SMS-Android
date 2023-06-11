package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                    profileImageUrl = studentList[it].profileImgUrl,
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .align(Alignment.Center),
                            color = colors.N20,
                        )
                    }
                }
            }
        }
    }
}