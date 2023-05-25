package com.sms.presentation.main.ui.util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

fun checkAndRequestPermissions(
    context: Context,
    permissions: String,
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    areadyGranted: () -> Unit
) {
    if (
        ContextCompat.checkSelfPermission(
            context,
            permissions
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        areadyGranted()
    } else {
        launcher.launch(permissions)
        Log.d("함수", "권한을 요청하였습니다.")
    }
}