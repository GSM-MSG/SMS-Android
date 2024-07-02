package com.sms.presentation.main.ui.util

import android.content.Context
import android.net.Uri

fun getFileName(context: Context, input: String): String? {
    return if (input.startsWith("http://") || input.startsWith("https://")) {
        getFileNameFromUrl(input)
    } else {
        getFileNameFromUri(context, Uri.parse(input))
    }
}