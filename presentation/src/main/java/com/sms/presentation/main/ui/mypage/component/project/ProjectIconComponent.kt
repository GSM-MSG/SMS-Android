package com.sms.presentation.main.ui.mypage.component.project

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun ProjectIconComponent(projectIcon: String, bitmap: Bitmap?, setBitmap: (value: Bitmap) -> Unit) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                if (Build.VERSION.SDK_INT < 28) {
                    setBitmap(MediaStore.Images.Media.getBitmap(context.contentResolver, uri))
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    setBitmap(ImageDecoder.decodeBitmap(source))
                }
            }
        }
    AddGrayBody1Title(titleText = "아이콘") {
        if (bitmap == null) {
            if (projectIcon == "") {
                Image(
                    modifier = Modifier
                        .size(108.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .smsClickable {
                            launcher.launch("image/*")
                        },
                    painter = painterResource(id = com.sms.design_system.R.drawable.ic_gallery_btn),
                    contentScale = ContentScale.Crop,
                    contentDescription = "프로젝트 아이콘"
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(108.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .smsClickable {
                            launcher.launch("image/*")
                        },
                    painter = rememberAsyncImagePainter(model = projectIcon),
                    contentScale = ContentScale.Crop,
                    contentDescription = "프로젝트 아이콘"
                )
            }
        } else {
            Image(
                modifier = Modifier
                    .size(108.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .smsClickable {
                        launcher.launch("image/*")
                    },
                bitmap = bitmap.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = "프로젝트 아이콘"
            )
        }
    }
}

@Preview
@Composable
private fun ProjectIconComponentPre() {
    ProjectIconComponent(
        projectIcon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
        bitmap = null,
        setBitmap = {})
}