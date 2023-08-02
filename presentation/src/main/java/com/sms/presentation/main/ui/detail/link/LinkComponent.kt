package com.sms.presentation.main.ui.detail.link

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.TopEndArrowIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.detail.data.RelatedLinksData

@Composable
fun LinkComponent(linksData: RelatedLinksData, getHeight: (height: Dp) -> Unit) {
    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = colors.N10)
                .onGloballyPositioned {
                    getHeight(it.size.height.dp)
                }
        ) {
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = linksData.name,
                        style = typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = colors.BLACK,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = linksData.link,
                        maxLines = 1,
                        style = typography.caption2,
                        fontWeight = FontWeight.Normal,
                        overflow = TextOverflow.Ellipsis,
                        color = colors.N40,
                    )
                }
                TopEndArrowIcon()
            }

        }
    }
}

@Preview
@Composable
private fun LinkComponentPre() {
    LinkComponent(
        linksData = RelatedLinksData(name = "Youtube", link = "https://youtube.com"),
        getHeight = {})
}

@Preview
@Composable
private fun LongLinkComponentPre() {
    LinkComponent(
        linksData = RelatedLinksData(
            name = "Youtube",
            link = "https://youtube.com/lkajsdlfjal;sdlfnaosdjfkasodfjkao;rigjasdg;ljaworgji"
        ), getHeight = {})
}