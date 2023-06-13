package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.response.StudentModel
import java.util.UUID

@Composable
fun StudentListComponent(
    listState: LazyListState,
    progressState: Boolean,
    studentList: List<StudentModel>,
    listTotalSize: Int,
    onItemClick: (uuid: UUID) -> Unit
) {
    SMSTheme { colors, typography ->
        LazyColumn(state = listState) {
            item {
                Row(
                    modifier = Modifier.padding(start = 20.dp, top = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = listTotalSize.toString(),
                        style = typography.title1,
                        fontWeight = FontWeight.Bold,
                        color = colors.BLACK
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "명",
                        style = typography.title2,
                        fontWeight = FontWeight.Bold,
                        color = colors.N30
                    )
                }
            }
            items(studentList.size) {
                StudentListItem(
                    profileImageUrl = studentList[it].profileImg,
                    major = studentList[it].major,
                    name = studentList[it].name,
                    teckStackList = studentList[it].techStack
                ) {
                    onItemClick(studentList[it].id)
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