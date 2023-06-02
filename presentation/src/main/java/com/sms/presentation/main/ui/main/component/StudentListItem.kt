package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun StudentListItem(
    profileImageUrl: String,
    name: String,
    major: String,
    teckStackList: List<String>,
    onClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        Box(modifier = Modifier.smsClickable {
            onClick()
        }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(20.dp))
                Image(
                    painter = rememberAsyncImagePainter(profileImageUrl),
                    contentDescription = "Student Profile Image",
                    modifier = Modifier
                        .width(101.dp)
                        .height(101.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.size(12.dp))
                Column {
                    Text(
                        text = name,
                        style = typography.title2,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = major,
                        style = typography.body2,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Row {
                        teckStackList.forEach { teckStack ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(colors.N10)
                            ) {
                                Text(
                                    text = teckStack,
                                    style = typography.caption1,
                                    color = colors.N40,
                                    modifier = Modifier.padding(
                                        vertical = 6.5.dp,
                                        horizontal = 12.dp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.size(4.dp))
                        }
                    }
                }
            }
        }
    }
}