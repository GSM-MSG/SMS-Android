package com.sms.presentation.main.ui.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

fun getPathFromUri(context: Context, uri: Uri): String {
    var filePath = ""
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)
    cursor?.let {
        if (it.moveToFirst()) {
            val columnIndex: Int = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            filePath = it.getString(columnIndex)
        }
        cursor.close()
    }
    return filePath
}