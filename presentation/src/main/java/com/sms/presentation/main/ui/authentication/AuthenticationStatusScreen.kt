package com.sms.presentation.main.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.authentication.MarkingBoardType
import com.msg.sms.domain.model.authentication.response.VerifyAuthenticationModel

@Composable
fun AuthenticationStatusComponent(
    verifyAuthenticationModel: VerifyAuthenticationModel?,
) {
    SMSTheme { colors, typography ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.N10)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.WHITE)
                    .padding(vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (val markingBoardType = verifyAuthenticationModel?.markingBoardType ?: "") {
                    MarkingBoardType.NOT_SUBMITTED -> {}

                    MarkingBoardType.PENDING_REVIEW, MarkingBoardType.UNDER_REVIEW -> {
                        Text(
                            text = "${verifyAuthenticationModel?.name ?: ""}님의 인증제는 현재 채점 ${
                                if (markingBoardType == MarkingBoardType.PENDING_REVIEW) "중" else "전"
                            }입니다.",
                            style = typography.title2,
                            fontWeight = FontWeight.Bold,
                            color = colors.N40,
                            textAlign = TextAlign.Center
                        )
                    }

                    MarkingBoardType.COMPLETED -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${verifyAuthenticationModel?.name ?: ""}님의 인증제 점수는",
                                style = typography.title2,
                                fontWeight = FontWeight.Bold,
                                color = colors.BLACK
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${verifyAuthenticationModel?.score?.toInt() ?: 0}점 입니다.",
                                style = typography.headline3,
                                fontWeight = FontWeight.Bold,
                                color = colors.P2
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "채점자 : ${verifyAuthenticationModel?.grader ?: ""} 선생님",
                                style = typography.title2,
                                fontWeight = FontWeight.Normal,
                                color = colors.N40
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAuthenticationStatusComponent() {
    AuthenticationStatusComponent(
        verifyAuthenticationModel = VerifyAuthenticationModel(
            name = "김현승",
            score = 100.0,
            grader = "변찬우",
            markingBoardType = MarkingBoardType.UNDER_REVIEW
        )
    )
}