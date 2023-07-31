package com.sms.presentation.main.ui.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msg.sms.design.icon.FilterButtonIcon
import com.msg.sms.design.icon.SmsLogoIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.sms.design_system.R

@Composable
fun MainScreenTopBar(
    profileImageUrl: String,
    isScolled: Boolean,
    filterButtonOnClick: () -> Unit,
    profileButtonOnClick: () -> Unit
) {
    Column {
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
                        .smsClickable(onClick = filterButtonOnClick)
                )
                Spacer(modifier = Modifier.width(16.dp))
                AsyncImage(
                    model = profileImageUrl,
                    placeholder = painterResource(id = R.drawable.ic_profile_default),
                    error = painterResource(id = R.drawable.ic_profile_default),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .smsClickable(onClick = profileButtonOnClick),
                    contentScale = ContentScale.Crop
                )
            }
        }
        SMSTheme { colors, _ ->
            if (isScolled) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = colors.N10,
                    thickness = 1.dp
                )
            }
        }
    }
}