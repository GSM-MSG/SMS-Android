package com.msg.sms.design.component.profile

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.icon.ProfileDefaultIcon

@Composable
fun ProfileImageComponent(
    profileImage: String,
    modifier: Modifier,
    imageView: @Composable (Modifier) -> Unit
) {
    val imageModifier = modifier
        .fillMaxWidth()
        .aspectRatio(1f)
    if (profileImage == "") {
        ProfileDefaultIcon(modifier = imageModifier)
    } else {
        imageView(imageModifier)
    }
}
