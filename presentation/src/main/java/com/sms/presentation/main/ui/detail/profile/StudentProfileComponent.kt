package com.sms.presentation.main.ui.detail.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.item.TechStackItem
import com.msg.sms.design.theme.SMSTheme

@Composable
fun StudentProfileComponent(
    major: String,
    name: String,
    isNotGuest: Boolean,
    techStack: List<String>,
    grade: String,
    classNumber: String,
    schoolNumber: String,
    departments: String,
    introduce: String
) {
    SMSTheme { colors, typography ->
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = major,
                style = typography.body1,
                color = colors.S2,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                style = typography.headline3,
                color = colors.BLACK,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            if (isNotGuest) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${grade}학년 ${classNumber}반 ${schoolNumber}번 • $departments",
                    style = typography.body2,
                    color = colors.N40,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(techStack) {
                    TechStackItem(techStack = it)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.N10)
            ) {
                Text(
                    text = "자기소개",
                    style = typography.caption2,
                    color = colors.N40,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = introduce,
                    style = typography.body2,
                    color = colors.BLACK,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}