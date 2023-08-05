package com.sms.presentation.main.ui.fill_out_information.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.icon.GalleryIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddBody1TitleText

@Composable
fun ProjectPreviewInputComponent(
    previewUriList: List<Uri>,
    deletedIndex: (Int) -> Unit,
    onClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        AddBody1TitleText(titleText = "미리보기 사진") {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                item {
                    Box(
                        modifier = Modifier
                            .size(132.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.N10)
                            .smsClickable(onClick = onClick)
                    ) {
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            GalleryIcon()
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${previewUriList.size}/4",
                                style = typography.body2,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                itemsIndexed(previewUriList) { idx, uri ->
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(132.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.N10)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "프로젝트 아이콘",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(colors.N10),
                            contentScale = ContentScale.FillBounds
                        )
                        DeleteButtonIcon(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(6.dp)
                                .smsClickable { deletedIndex(idx) }
                        )
                    }
                }
            }
        }
    }
}