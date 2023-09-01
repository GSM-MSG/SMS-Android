package com.sms.presentation.main.ui.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun createCurrentTime(timeFormat: String): String {
    val currentTime = System.currentTimeMillis()
    return SimpleDateFormat(timeFormat).format(currentTime)
}