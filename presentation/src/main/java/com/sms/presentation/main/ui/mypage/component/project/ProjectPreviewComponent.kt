package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.component.indicator.PreviewImageIndicator
import com.msg.sms.design.icon.DeleteButtonIcon

@Composable
fun ProjectPreviewComponent(list: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            PreviewImageIndicator(currentSize = list.size)
        }
        items(list) {
            Box(
                Modifier
                    .size(132.dp)
                    .clip(RoundedCornerShape(8.dp))) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    painter = rememberAsyncImagePainter(model = it),
                    contentScale = ContentScale.Crop,
                    contentDescription = "프로젝트 미리보기 이미지"
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopEnd)) {
                    DeleteButtonIcon()
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProjectPreviewComponentPre() {
    ProjectPreviewComponent(
        listOf(
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
        )
    )
}