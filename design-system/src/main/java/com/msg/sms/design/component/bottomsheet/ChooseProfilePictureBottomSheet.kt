package com.msg.sms.design.component.bottomsheet

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.CameraIcon
import com.msg.sms.design.icon.GalleryIcon

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseProfilePictureBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    profileImageUri: (Uri) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            profileImageUri(uri ?: Uri.EMPTY)
        }

    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        }
    }

    Column {
        Spacer(modifier = Modifier.size(24.dp))
        ProfileBottomSheetComponent(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState,
            icon = { GalleryIcon() },
            text = "앨범에서 가져오기"
        ) {
            permissionLauncher.launch(permission)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ProfileBottomSheetComponent(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState,
            icon = { CameraIcon() },
            text = "카메라에서 촬영"
        ) {

        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}