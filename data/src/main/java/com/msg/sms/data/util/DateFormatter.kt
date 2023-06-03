package com.msg.sms.data.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    return SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").parse(this)!!
}

@SuppressLint("SimpleDateFormat")
fun Long.toSmsTimeDate(): Date {
    return SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").format(this).toDate()
}