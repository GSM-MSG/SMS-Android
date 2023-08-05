package com.sms.presentation.main.ui.mypage.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.mypage.component.certification.CertificationsComponent

@Composable
fun CertificationsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 20.dp)
    ) {
        CertificationsComponent(certificationList = listOf("정보처리 산업기사"))
    }
}

@Preview
@Composable
private fun CertificationsSectionPre() {
    CertificationsSection()
}