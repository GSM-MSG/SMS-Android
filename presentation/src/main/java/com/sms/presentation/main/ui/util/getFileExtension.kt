package com.sms.presentation.main.ui.util

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

fun getFileExtension(context: Context, uri: Uri): String? {
    val mimeType = context.contentResolver.getType(uri)
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
}