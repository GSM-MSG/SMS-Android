package com.sms.presentation.main.ui.fill_out_information.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.icon.GalleryIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddBody1Title

@Composable
fun ProjectIconInputComponent(iconImageUri: Uri, onClick: () -> Unit) {
    SMSTheme { colors, _ ->
        if (iconImageUri == Uri.EMPTY) {
            AddBody1Title(titleText = "아이콘") {
                Box(
                    modifier = Modifier
                        .size(108.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.N10)
                        .smsClickable(onClick = onClick)
                ) {
                    GalleryIcon(modifier = Modifier.align(Alignment.Center))
                }
            }
        } else {
            Image(
                painter = rememberAsyncImagePainter(iconImageUri),
                contentDescription = "프로젝트 아이콘",
                modifier = Modifier
                    .size(108.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.N10),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}