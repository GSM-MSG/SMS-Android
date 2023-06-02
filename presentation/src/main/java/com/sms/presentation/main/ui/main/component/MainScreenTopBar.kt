package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.icon.FilterButtonIcon
import com.msg.sms.design.icon.SmsLogoIcon
import com.msg.sms.design.modifier.smsClickable

@Composable
fun MainScreenTopBar(
    profileImageUrl: String,
    filterButtonOnClick: () -> Unit,
    profileButtonOnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, top = 3.5.dp, bottom = 3.5.dp)
    ) {
        SmsLogoIcon(
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .align(Alignment.CenterStart)
        )
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            FilterButtonIcon(
                modifier = Modifier
                    .smsClickable {
                        filterButtonOnClick()
                    }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                painter = rememberAsyncImagePainter(profileImageUrl),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .smsClickable {
                        profileButtonOnClick()
                    }
            )
        }
    }
}