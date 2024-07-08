package com.sms.presentation.main.ui.util

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun getFileNameFromUrl(url: String): String {
    val decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())
    return decodedUrl.substring(decodedUrl.lastIndexOf('/') + 1)
}