package com.sms.presentation.main.ui.util

import android.graphics.Bitmap
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink

fun Bitmap.createFileFromBitmap(name: String, fileName: String): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        name = name,
        filename = fileName,
        body = BitmapRequestBody(this)
    )
}

class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
    override fun contentType() = "image/jpeg".toMediaType()
    override fun writeTo(sink: BufferedSink) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
    }
}