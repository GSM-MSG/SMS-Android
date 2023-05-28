package com.sms.presentation.main.ui.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException

fun Uri.toMultipartBody(context: Context): MultipartBody.Part? {
    val file: File? = getFileFromUri(context, this)
    file?.let {
        val requestFile: RequestBody =
            RequestBody.create("application/octet-stream".toMediaTypeOrNull(), it)
        val part: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", it.name, requestFile)
        return part
    }
    return null
}

fun Bitmap?.toUri(context: Context): Uri? {
    if (this == null)
        return Uri.EMPTY

    var imageUri: Uri? = null
    try {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val resolver = context.contentResolver
        imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (imageUri != null) {
            val outputStream = resolver.openOutputStream(imageUri)
            outputStream?.use {
                this.compress(Bitmap.CompressFormat.JPEG, 90, it)
                it.flush()
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return imageUri
}