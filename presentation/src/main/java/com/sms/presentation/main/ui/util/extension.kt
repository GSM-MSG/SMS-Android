package com.sms.presentation.main.ui.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.compose.ui.unit.Dp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

fun Uri.toMultipartBody(context: Context): MultipartBody.Part? {
    val file: File? = getFileFromUri(context, this)
    file?.let {
        val requestFile: RequestBody =
            it.asRequestBody("application/octet-stream".toMediaTypeOrNull())
        val part: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", it.name, requestFile)
        return part
    }
    return null
}

fun Bitmap?.toUri(context: Context): Uri? {
    if (this == null) return Uri.EMPTY

    var imageUri: Uri? = null
    try {
        if (Build.VERSION.SDK_INT >= 29) {
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
        } else {
            val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val imageFileName = "${System.currentTimeMillis()}.jpg"
            val imageFile = File(imagesDir, imageFileName)

            val outputStream: OutputStream = FileOutputStream(imageFile)
            this.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            outputStream.flush()
            outputStream.close()

            imageUri = Uri.fromFile(imageFile)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return imageUri
}

fun List<String>.toCorrectList(screenWidth: Dp): List<String> {
    val rowWidth = (screenWidth.toString().split(".")[0].toInt() * 0.6).toInt()
    var itemListSize = 0
    val list = arrayListOf<String>()
    this.forEach {
        itemListSize += (screenWidth.toString()
            .split(".")[0].toInt() * 0.02 * it.length).toInt() + 17
        if (rowWidth > itemListSize) {
            list.add(it)
        } else {
            list[list.size - 1] = "•••"
        }
    }
    return list
}