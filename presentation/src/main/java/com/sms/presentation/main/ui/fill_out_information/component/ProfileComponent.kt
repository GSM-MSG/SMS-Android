package com.sms.presentation.main.ui.fill_out_information.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.icon.ProfileIcon
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileComponent(
    bottomSheetScaffoldState: ModalBottomSheetState,
    selectedMajor: String
) {
    val imageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri.value = uri
        }

    SMSTheme { _, typography ->
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                SmsTitleText(text = "프로필", isRequired = true)
                PagerIndicator(
                    modifier = Modifier.align(
                        Alignment.CenterEnd
                    ),
                    indexOfPointingNumber = 0,
                    size = 6
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "사진", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            if (imageUri.value == null)
                ProfileIcon(modifier = Modifier.clickable {
                    galleryLauncher.launch("image/*")
                })
            else
                Image(
                    painter = rememberAsyncImagePainter(imageUri.value),
                    contentDescription = "User Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(107.dp)
                        .height(106.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "자기소개", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(placeHolder = "1줄 자기소개 입력", modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "이메일", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(placeHolder = "공개용 이메일 입력", modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "분야", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsCustomTextField(
                placeHolder = "FrondEnd", modifier = Modifier.fillMaxWidth(),
                endIcon = { OpenButtonIcon() },
                readOnly = true,
                clickAction = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.show()
                    }
                },
                setChangeText = selectedMajor
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "포트폴리오 URL", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(placeHolder = "https://", modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "세부스택", style = typography.body2)
            SmsTextField(placeHolder = "예시) HTML, CSS, C#", modifier = Modifier.fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ProfileComponentPre() {
    ProfileComponent(
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
        "FrontEnd"
    )
}