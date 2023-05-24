package com.sms.presentation.main.ui.util

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun Uri.toMultipartBody(context: Context): MultipartBody.Part {
    val file = File(getPathFromUri(context, this))
    val requestFile: RequestBody =
        RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
    return MultipartBody.Part.createFormData("file", file.name, requestFile)
}