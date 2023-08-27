package com.sms.presentation.main.ui.fill_out_information.component.projects

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.icon.GalleryIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectIconInputComponent(
    iconImageUri: Uri,
    onValueChanged: (value: Uri) -> Unit
) {
    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    val singleSelectGalleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                onValueChanged(uri)
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            singleSelectGalleryLauncher.launch("image/*")
        }
    }

    SMSTheme { colors, typography ->
        AddGrayBody1Title(titleText = "아이콘") {
            if (iconImageUri == Uri.EMPTY) {
                Column {
                    Box(
                        modifier = Modifier
                            .size(108.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.N10)
                            .smsClickable { permissionLauncher.launch(permission) }
                    ) {
                        GalleryIcon(modifier = Modifier.align(Alignment.Center))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "프로젝트 아이콘을 선택해 주세요.",
                        style = typography.caption1,
                        color = colors.ERROR
                    )
                }
            } else {
                Image(
                    painter = rememberAsyncImagePainter(iconImageUri),
                    contentDescription = "프로젝트 아이콘",
                    modifier = Modifier
                        .size(108.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.N10)
                        .smsClickable { permissionLauncher.launch(permission) },
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}