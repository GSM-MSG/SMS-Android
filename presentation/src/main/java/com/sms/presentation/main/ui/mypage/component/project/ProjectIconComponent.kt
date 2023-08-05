package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectIconComponent() {
    AddGrayBody1Title(titleText = "아이콘") {
        Image(
            modifier = Modifier
                .size(108.dp)
                .clip(RoundedCornerShape(8.dp))
                .smsClickable {

                },
            painter = rememberAsyncImagePainter(model = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"),
            contentScale = ContentScale.Crop,
            contentDescription = "프로젝트 아이콘"
        )
    }
}

@Preview
@Composable
private fun ProjectIconComponentPre() {
    ProjectIconComponent()
}