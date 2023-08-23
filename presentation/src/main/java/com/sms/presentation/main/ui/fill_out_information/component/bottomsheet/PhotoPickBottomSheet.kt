package com.sms.presentation.main.ui.fill_out_information.component.bottomsheet

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.msg.sms.design.component.bottomsheet.ChooseProfilePictureBottomSheet
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isImageExtensionCorrect
import com.sms.presentation.main.ui.util.toUri

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoPickBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    onProfileImageUriChanged: (uri: Uri, extention: Boolean) -> Unit
) {
    val context = LocalContext.current
    val isCamera = remember {
        mutableStateOf(false)
    }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                onProfileImageUriChanged(
                    uri,
                    getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()
                )
            }
        }
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            val uri = bitmap.toUri(context)
            if (uri != null) {
                onProfileImageUriChanged(
                    uri,
                    getFileNameFromUri(context, uri)!!.isImageExtensionCorrect()
                )
            }
        }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        when {
            isGranted && isCamera.value -> {
                cameraLauncher.launch(null)
            }
            isGranted && !isCamera.value -> {
                galleryLauncher.launch("image/*")
            }
        }
    }
    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    ChooseProfilePictureBottomSheet(
        bottomSheetState = bottomSheetState,
        onGalleryLaunchButtonClick = {
            isCamera.value = false
            permissionLauncher.launch(permission)
        },
        onCameraLaunchButtonClick = {
            isCamera.value = true
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    )
}