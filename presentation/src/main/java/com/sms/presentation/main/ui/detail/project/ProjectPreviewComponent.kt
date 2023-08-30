package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sms.presentation.main.ui.detail.data.ProjectData

@Composable
fun ProjectPreviewComponent(
    projectName: String,
    projectPreviewUrlList: List<String>
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            userScrollEnabled = false,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxWidth + 100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            itemsIndexed(projectPreviewUrlList) { _, item ->
                Image(
                    painter = rememberAsyncImagePainter(model = item),
                    contentDescription = "$projectName 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .aspectRatio(1f)
                )
            }
        }
    }
}