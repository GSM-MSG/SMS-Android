package com.sms.presentation.main.ui.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
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
        Log.d("Multipart", file.name)
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

fun String.isfileExtensionCorrect(): Boolean {
    val fileExtension = listOf("hwp", "hwpx")
    return fileExtension.contains(this)
}

fun String.isImageExtensionCorrect(): Boolean {
    val imageExtension = listOf("jpg", "jpeg", "png", "heic")
    return imageExtension.contains(this.substringAfter("."))
}

fun String.employmentEnumToSting(): String {
    return when (this) {
        "FULL_TIME" -> "정규직"
        "TEMPORARY" -> "비정규직"
        "CONSTRACT" -> "계약직"
        "INTERN" -> "인턴"
        else -> ""
    }
}

fun String.militaryServiceEnumToString(): String {
    return when (this) {
        "HOPE" -> "병특 희망"
        "NOT_HOPE" -> "희망하지 않음"
        "NO_MATTER" -> "상관없음"
        "NONE" -> "해당 사항 없음"
        else -> ""
    }
}

fun String.departmentEnumToString(): String {
    return when (this) {
        "SW_DEVELOPMENT" -> "SW 개발과"
        "SMART_IOT_DEVELOPMENT" -> "스마트 IOT과"
        "AI_DEVELOPMENT" -> "인공지능 개발과"
        else -> ""
    }
}

fun String.isEmailRegularExpression(): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return matches(emailRegex)
}

fun String.isUrlRegularExpression(): Boolean {
    val urlRegex = Regex("^(http(s)?://)?[\\w.-]+\\.[a-zA-Z]{2,3}(/\\S*)?\$")
    return matches(urlRegex)
}

fun String.returnNumberOnly(number: (Int) -> Unit) {
    runCatching {
        this.toInt()
    }.onSuccess {
        number(it)
    }
}