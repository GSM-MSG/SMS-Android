package com.sms.presentation.main.ui.fill_out_authentication.state

import android.graphics.Bitmap

data class AuthenticationData(
    val title: String,
    val content: String,
    val activityImages: String,
    val activityImagesBitmap: Bitmap? = null
)
