package com.sms.presentation.main.ui.util

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString


fun copyText(text: String, clipboardManager: ClipboardManager) {
    clipboardManager.setText(AnnotatedString(text))
}