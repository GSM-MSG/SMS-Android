package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.FileIcon
import com.msg.sms.design.theme.SMSTheme

@Composable
fun FileItem(
    fileName: String,
    onDownLoadClick: () -> Unit,
) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(colors.N10)
                .border(
                    width = 1.dp,
                    color = colors.N20,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colors.WHITE)
                    .padding(8.dp)
            ) {
                FileIcon(modifier = Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = fileName,
                style = typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.5.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, colors.BLACK),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                onClick = onDownLoadClick
            ) {
                Text(text = "다운로드", style = typography.body1)
            }
        }
    }
}

@Preview
@Composable
fun FileItemPre() {
    FileItem(fileName = "GSM 독후감 파일.hwp") {}
}