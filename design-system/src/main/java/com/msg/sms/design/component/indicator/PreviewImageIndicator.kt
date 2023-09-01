package com.msg.sms.design.component.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.GalleryIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme

@Composable
fun PreviewImageIndicator(
    currentSize: Int, max: Int = 4,
    onOpenGallery: () -> Unit,
) {
    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .size(132.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = colors.N10)
                .smsClickable(onClick = onOpenGallery)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 47.dp)
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GalleryIcon()
                Text(
                    text = "$currentSize / $max",
                    style = typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = colors.BLACK
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewImageIndicatorPre() {
    PreviewImageIndicator(currentSize = 2, max = 4) {}
}