package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.user.response.ActivityDuration

@Composable
fun ProjectInfoTopBar(
    projectIconUrl: String,
    projectName: String,
    projectDate: ActivityDuration
) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = projectIconUrl),
                contentDescription = "$projectName 아이콘",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(8))
                    .size(40.dp)
            )
            Column {
                Text(
                    text = projectName,
                    style = typography.body1,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "${projectDate.start} ~ ${projectDate.end ?: "진행 중"}",
                    style = typography.caption2,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}