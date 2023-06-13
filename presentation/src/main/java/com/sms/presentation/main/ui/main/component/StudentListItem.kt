package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.design_system.R

@Composable
fun StudentListItem(
    profileImageUrl: String,
    name: String,
    major: String,
    techStackList: List<String>,
    onClick: () -> Unit
) {
    val list = remember {
        mutableStateOf(techStackList)
    }

    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier.smsClickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = profileImageUrl,
                    placeholder = painterResource(id = R.drawable.ic_profile_default),
                    error = painterResource(id = R.drawable.ic_profile_default),
                    contentDescription = "Student Profile Image",
                    modifier = Modifier
                        .width(101.dp)
                        .height(101.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = name,
                        style = typography.title2,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = major,
                        style = typography.body2,
                        color = colors.BLACK,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .fillMaxWidth()
                    ) {
                        run {
                            list.value.forEachIndexed { index, techStack ->
                                val isItemOver = remember {
                                    mutableStateOf(false)
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(colors.N10)
                                ) {
                                    if (isItemOver.value) {
                                        Text(
                                            text = "•••",
                                            style = typography.caption1,
                                            color = colors.N40,
                                            maxLines = 1,
                                            onTextLayout = { result ->
                                                if (result.hasVisualOverflow) {
                                                    list.value =
                                                        list.value.slice(0 until index - 1)
                                                            .plus("•••")
                                                }
                                            },
                                            modifier = Modifier
                                                .padding(
                                                    vertical = 6.5.dp,
                                                    horizontal = 12.dp
                                                )
                                        )
                                    } else {
                                        Text(
                                            text = techStack,
                                            style = typography.caption1,
                                            color = colors.N40,
                                            maxLines = 1,
                                            onTextLayout = { result ->
                                                isItemOver.value = result.hasVisualOverflow
                                            },
                                            modifier = Modifier
                                                .padding(
                                                    vertical = 6.5.dp,
                                                    horizontal = 12.dp
                                                )
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                if (isItemOver.value)
                                    return@run
                            }
                        }
                    }
                }
            }
        }
    }
}
