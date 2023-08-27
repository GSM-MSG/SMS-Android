package com.sms.presentation.main.ui.fill_out_information.component.projects

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.icon.GalleryIcon
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectPreviewInputComponent(
    previewUriList: List<Uri>,
    onSnackBarVisibleChanged: (text: String) -> Unit,
    onValueChanged: (value: List<Uri>) -> Unit
) {
    val previews = remember {
        mutableStateListOf(*previewUriList.toTypedArray())
    }

    val multipleSelectGalleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            if (uris.isNotEmpty()) {
                if ((previews.size + uris.size) <= 4) {
                    previews.addAll(uris)
                } else onSnackBarVisibleChanged("이미지는 최대 4장까지만 추가 할 수 있어요.")
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            if (previews.size == 4) {
                onSnackBarVisibleChanged("이미지는 최대 4장까지만 추가 할 수 있어요.")
            } else {
                multipleSelectGalleryLauncher.launch("image/*")
            }
        }
    }

    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    if (previews.isNotEmpty()) {
        onValueChanged(previews)
    }

    SMSTheme { colors, typography ->
        AddGrayBody1Title(titleText = "미리보기 사진") {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                item {
                    Box(modifier = Modifier
                        .size(132.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.N10)
                        .smsClickable { permissionLauncher.launch(permission) }) {
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            GalleryIcon()
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${previews.size}/4",
                                style = typography.body2,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                itemsIndexed(previews) { idx, uri ->
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(132.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.N10)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "프로젝트 아이콘",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(colors.N10),
                            contentScale = ContentScale.FillBounds
                        )
                        DeleteButtonIcon(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(6.dp)
                                .smsClickable(
                                    bounded = false
                                ) {
                                    previews.removeAt(idx)
                                }
                        )
                    }
                }
            }
        }
    }
}