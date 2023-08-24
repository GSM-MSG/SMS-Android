package com.sms.presentation.main.ui.fill_out_information.component.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.icon.ProfileIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.fill_out_information.data.ProfileData
import com.sms.presentation.main.ui.util.hideKeyboard
import com.sms.presentation.main.ui.util.textFieldChecker

@Composable
fun ProfileComponent(
    data: ProfileData,
    enteredMajor: String,
    selectedMajor: String,
    detailStack: String,
    profileImageUri: Uri,
    isReadOnly: Boolean,
    changeView: () -> Unit,
    isRequired: (Boolean) -> Unit,
    enteringMajor: (String) -> Unit,
    onMajorBottomSheetOpenButtonClick: () -> Unit,
    onPhotoPickBottomSheetOpenButtonClick: () -> Unit,
    savedData: (introduce: String, portfolio: String, contactEmail: String, profileImageUri: Uri) -> Unit
) {
    SMSTheme { _, typography ->
        val context = LocalContext.current as FillOutInformationActivity

        val introduce = remember {
            mutableStateOf(if (data.introduce != "") data.introduce else "")
        }
        val portfolioUrl = remember {
            mutableStateOf(if (data.portfolioUrl != "") data.portfolioUrl else "")
        }
        val contactEmail = remember {
            mutableStateOf(if (data.contactEmail != "") data.contactEmail else "")
        }

        savedData(
            if (introduce.value == "") data.introduce else introduce.value,
            if (portfolioUrl.value == "") data.portfolioUrl else portfolioUrl.value,
            if (contactEmail.value == "") data.contactEmail else contactEmail.value,
            if (profileImageUri == Uri.EMPTY) data.profileImageUri else profileImageUri
        )

        isRequired(
            textFieldChecker(
                detailStack,
                introduce.value,
                profileImageUri.toString(),
                contactEmail.value
            ) && profileImageUri != Uri.EMPTY
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            SmsTitleText(text = "프로필", isRequired = true)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "사진", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            if (profileImageUri == Uri.EMPTY) {
                ProfileIcon(modifier = Modifier.clickable {
                    context.hideKeyboard()
                    onPhotoPickBottomSheetOpenButtonClick()
                })
            } else {
                Image(
                    painter = rememberAsyncImagePainter(profileImageUri),
                    contentDescription = "User Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(107.dp)
                        .height(106.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .clickable {
                            context.hideKeyboard()
                            onPhotoPickBottomSheetOpenButtonClick()
                        }
                )
            }
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
                readOnly = isReadOnly,
                clickAction = {
                    onMajorBottomSheetOpenButtonClick()
                    context.hideKeyboard()
                },
                setChangeText = if (selectedMajor == "직접입력") enteredMajor else selectedMajor
            ) {
                enteringMajor(it)
            }
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
            SmsCustomTextField(
                placeHolder = "예시) HTML, CSS, C#",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(FocusRequester())
                    .onFocusChanged {
                        if (it.isFocused) {
                            changeView()
                        }
                    },
                setChangeText = detailStack,
                readOnly = true,
                endIcon = null,
                onValueChange = {},
                clickAction = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ProfileComponentPre() {
    ProfileComponent(
        selectedMajor = "FrontEnd",
        savedData = { _: String, _: String, _: String, _: Uri -> },
        data = ProfileData(Uri.EMPTY, "", "", "", "", "", ""),
        isRequired = {},
        detailStack = "",
        profileImageUri = Uri.EMPTY,
        isReadOnly = true,
        changeView = {},
        enteringMajor = {},
        enteredMajor = "",
        onMajorBottomSheetOpenButtonClick = {},
        onPhotoPickBottomSheetOpenButtonClick = {}
    )
}