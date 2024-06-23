package com.sms.presentation.main.ui.util

import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString

private val clipBoardManager = LocalClipboardManager.current
fun copyText(text: String) {
    clipBoardManager.setText(AnnotatedString(text))
}