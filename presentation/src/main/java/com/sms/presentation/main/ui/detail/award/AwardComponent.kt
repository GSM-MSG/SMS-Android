package com.sms.presentation.main.ui.detail.award

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.detail.data.AwardData

@Composable
fun AwardComponent(awardList: List<AwardData>) {
    SMSTheme { colors, typography ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 10000.dp),
            contentPadding = PaddingValues(bottom = 40.dp),
            userScrollEnabled = false
        ) {
            item {
                Text(
                    text = "수상",
                    style = typography.title2,
                    color = colors.BLACK,
                    fontWeight = FontWeight.Bold
                )
            }
            items(awardList.size) {
                val award = awardList[it]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.N10)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .align(Alignment.Center)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = award.title,
                                style = typography.body1,
                                color = colors.BLACK,
                                fontWeight = FontWeight.Normal,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = award.date,
                                style = typography.caption2,
                                fontWeight = FontWeight.Normal,
                                color = colors.BLACK
                            )
                        }
                        Text(
                            text = award.organization,
                            style = typography.caption2,
                            fontWeight = FontWeight.Normal,
                            color = colors.N40
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AwardComponentPre() {
    AwardComponent(awardList = listOf(AwardData("기업상", "해피문데이", "2023. 03. 02")))
}