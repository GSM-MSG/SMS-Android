package com.msg.sms.data.util

import android.annotation.SuppressLint
import com.msg.sms.domain.exception.NeedLoginException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    kotlin.runCatching {
        SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").parse(this)!!
    }.onSuccess {
        return it
    }
    throw NeedLoginException()
}

@SuppressLint("SimpleDateFormat")
fun Long.toSmsTimeDate(): Date {
    return SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss").format(this).toDate()
}