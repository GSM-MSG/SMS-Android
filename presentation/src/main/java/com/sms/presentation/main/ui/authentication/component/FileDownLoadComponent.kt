package com.sms.presentation.main.ui.authentication.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun FileDownLoadComponent(
    modifier: Modifier = Modifier,
    file: List<String>,
    onItemClick: (index: Int) -> Unit,
) {
    SMSTheme { colors, typography ->
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "파일 다운로드",
                    color = colors.BLACK,
                    style = typography.title1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            itemsIndexed(file) { index, item ->
                FileItem(fileName = item) {
                    onItemClick(index)
                }
            }
        }
    }
}

@Preview
@Composable
fun FileDownLoadComponentPre() {
    FileDownLoadComponent(
        file = listOf(
            "GSM 독후감 파일.hwp",
            "GSM 독후감 파일.hwp",
            "GSM 독후감 파일.hwp"
        )
    ) {

    }
}