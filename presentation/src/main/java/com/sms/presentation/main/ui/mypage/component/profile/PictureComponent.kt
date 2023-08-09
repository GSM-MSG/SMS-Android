package com.sms.presentation.main.ui.mypage.component.profile

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.modifier.smsClickable
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun PicturePickerComponent(imageUrl: String) {
    val imageUri = remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            if(uri != null) {
                imageUri.value = uri
            }
        }

    AddGrayBody1Title(titleText = "사진") {
        if (imageUri.value == Uri.EMPTY) {
            Image(
                modifier = Modifier
                    .size(168.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .smsClickable {
                        launcher.launch("image/*")
                    },
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentScale = ContentScale.Crop,
                contentDescription = "프로필 이미지"
            )
        } else {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value =
                    MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri.value)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, imageUri.value)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            Image(
                modifier = Modifier
                    .size(168.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .smsClickable {
                        launcher.launch("image/*")
                    },
                bitmap = bitmap.value!!.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = "프로필 이미지"
            )
        }
    }
}

@Preview
@Composable
private fun PicturePickerComponentPre() {
    PicturePickerComponent(imageUrl = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4")
}