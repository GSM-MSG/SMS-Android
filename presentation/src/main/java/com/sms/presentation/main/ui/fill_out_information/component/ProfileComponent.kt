package com.sms.presentation.main.ui.fill_out_information.component

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.sms.presentation.main.ui.fill_out_information.data.ProfileData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileComponent(
    bottomSheetScaffoldState: ModalBottomSheetState,
    selectedMajor: String,
    enteredData: (techStack: String, introduce: String, portfolio: String, contactEmail: String, profileImageUri: Uri) -> Unit,
    data: ProfileData,
    isRequired: (Boolean) -> Unit,
) {
    val profileImageUri = remember {
        mutableStateOf(Uri.EMPTY)
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            profileImageUri.value = uri ?: Uri.EMPTY
        }

    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        }
    }


    SMSTheme { _, typography ->
        val coroutineScope = rememberCoroutineScope()

        val techStack = remember {
            mutableStateOf(if (data.techStack != "") data.techStack else "")
        }
        val introduce = remember {
            mutableStateOf(if (data.introduce != "") data.introduce else "")
        }
        val portfolioUrl = remember {
            mutableStateOf(if (data.portfolioUrl != "") data.portfolioUrl else "")
        }
        val contactEmail = remember {
            mutableStateOf(if (data.contactEmail != "") data.contactEmail else "")
        }

        enteredData(
            if (techStack.value == "") data.techStack else techStack.value,
            if (introduce.value == "") data.introduce else introduce.value,
            if (portfolioUrl.value == "") data.portfolioUrl else portfolioUrl.value,
            if (contactEmail.value == "") data.contactEmail else contactEmail.value,
            profileImageUri.value ?: Uri.EMPTY
        )

        isRequired(
            techStack.value != "" && introduce.value != "" && portfolioUrl.value != "" && contactEmail.value != "" && profileImageUri.value != Uri.EMPTY
        )

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
                    ), indexOfPointingNumber = 0, size = 6
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "사진", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            if (profileImageUri.value == Uri.EMPTY || profileImageUri.value == null) {
                profileImageUri.value = data.profileImageUri
                ProfileIcon(modifier = Modifier.clickable {
                    launcherMultiplePermissions.launch(permission)
                })
            } else Image(
                painter = rememberAsyncImagePainter(profileImageUri.value),
                contentDescription = "User Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(107.dp)
                    .height(106.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "자기소개", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(
                placeHolder = "1줄 자기소개 입력",
                modifier = Modifier.fillMaxWidth(),
                setText = introduce.value,
                onValueChange = { introduce.value = it }
            ) {
                introduce.value = ""
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "이메일", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(
                placeHolder = "공개용 이메일 입력",
                modifier = Modifier.fillMaxWidth(),
                setText = contactEmail.value,
                onValueChange = { contactEmail.value = it }
            ) {
                contactEmail.value = ""
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "분야", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsCustomTextField(
                placeHolder = "FrondEnd",
                modifier = Modifier.fillMaxWidth(),
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
            SmsTextField(
                placeHolder = "https://",
                modifier = Modifier.fillMaxWidth(),
                setText = portfolioUrl.value,
                onValueChange = { portfolioUrl.value = it }
            ) {
                portfolioUrl.value = ""
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "세부스택", style = typography.body2)
            SmsTextField(
                placeHolder = "예시) HTML, CSS, C#",
                modifier = Modifier.fillMaxWidth(),
                setText = techStack.value,
                onValueChange = { techStack.value = it }
            ) {
                techStack.value = ""
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ProfileComponentPre() {
    ProfileComponent(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
        selectedMajor = "FrontEnd",
        enteredData = { _: String, _: String, _: String, _: String, _: Uri -> Unit },
        data = ProfileData(Uri.EMPTY, "", "", "", "", ""),
        isRequired = {})
}