package com.msg.sms.design.component.bottomsheet

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
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
    isCamera: (Boolean) -> Unit,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    permission: String
) {
    val coroutineScope = rememberCoroutineScope()

    Column {
        Spacer(modifier = Modifier.size(24.dp))
        ProfileBottomSheetComponent(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState,
            icon = {
                GalleryIcon(
                    modifier = Modifier.padding(12.dp)
                )
            },
            text = "앨범에서 가져오기"
        ) {
            isCamera(false)
            permissionLauncher.launch(permission)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ProfileBottomSheetComponent(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState,
            icon = {
                CameraIcon(
                    modifier = Modifier.padding(12.dp)
                )
            },
            text = "카메라에서 촬영"
        ) {
            isCamera(true)
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}