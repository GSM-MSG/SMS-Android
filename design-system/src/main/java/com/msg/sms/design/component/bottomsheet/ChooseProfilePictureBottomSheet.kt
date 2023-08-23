package com.msg.sms.design.component.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.icon.CameraIcon
import com.msg.sms.design.icon.GalleryIcon
import com.msg.sms.design.theme.SMSTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChooseProfilePictureBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    onGalleryLaunchButtonClick: () -> Unit,
    onCameraLaunchButtonClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    SMSTheme { colors, typography ->
        Column {
            Spacer(modifier = Modifier.size(24.dp))
            BaseBottomSheetComponent(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState,
                leftIcon = {
                    GalleryIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                },
                text = "앨범에서 가져오기",
                textStyle = typography.body1,
                textColor = colors.N50,
                fontWeight = FontWeight.Normal,
                onClick = onGalleryLaunchButtonClick
            )
            Spacer(modifier = Modifier.size(8.dp))
            BaseBottomSheetComponent(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState,
                leftIcon = {
                    CameraIcon(
                        modifier = Modifier.padding(12.dp)
                    )
                },
                text = "카메라에서 촬영",
                textStyle = typography.body1,
                textColor = colors.N50,
                fontWeight = FontWeight.Normal,
                onClick = onCameraLaunchButtonClick
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}