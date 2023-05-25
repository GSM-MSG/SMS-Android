package com.sms.presentation.main.ui.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

fun getFileFromUri(context: Context, uri: Uri): File? {
    val inputStream = context.contentResolver.openInputStream(uri)
    inputStream?.let {
        val fileExtension = getFileExtension(context, uri)
        val fileName = getFileNameFromUri(context, uri)
        val file = File(context.cacheDir, fileName + fileExtension)
        FileOutputStream(file).use { outputStream ->
            val buffer = ByteArray(4 * 1024) // 4KB buffer size
            var bytesRead: Int
            while (it.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.flush()
        }
        inputStream.close()
        return file
    }
    return null
}