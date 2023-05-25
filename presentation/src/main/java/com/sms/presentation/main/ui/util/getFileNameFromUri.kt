package com.sms.presentation.main.ui.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns

fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
    cursor?.let {
        if (it.moveToFirst()) {
            val columIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val displayName = it.getString(columIndex)
            fileName = displayName ?: ""
        }
        cursor.close()
    }
    return fileName
}