package com.sms.presentation.main.ui.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build

fun isBrowserInstalled(context: Context): Boolean {
    val packageManager: PackageManager = context.packageManager
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://gauth.co.kr/"))

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val list = packageManager.queryIntentActivities(
            intent,
            PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
        )
        list.isNotEmpty()
    } else {
        val list = packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        list.isNotEmpty()
    }
}