package com.sms.presentation.main.ui.util

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

suspend fun downloadFile(context: Context, urlString: String): File? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(urlString)
            val fileName = url.path.substringAfterLast("/")
            val file = File(context.cacheDir, fileName)
            url.openStream().use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}