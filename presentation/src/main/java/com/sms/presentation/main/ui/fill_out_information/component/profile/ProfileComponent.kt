package com.sms.presentation.main.ui.fill_out_information.component.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    detailStacks: List<String>,
    profileImageUri: Uri,
    isReadOnly: Boolean,
    changeView: () -> Unit,
    onMajorBottomSheetOpenButtonClick: () -> Unit,
    onPhotoPickBottomSheetOpenButtonClick: () -> Unit,
    isRequired: (Boolean) -> Unit,
    onProfileValueChanged: (data: ProfileData) -> Unit,
    onTechStackItemRemoved: (item: String) -> Unit
) {
    SMSTheme { _, typography ->
        val context = LocalContext.current as FillOutInformationActivity
        isRequired(
            textFieldChecker(
                data.introduce,
                profileImageUri.toString(),
                data.contactEmail
            ) && profileImageUri != Uri.EMPTY && detailStacks.isNotEmpty()
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
                setText = data.introduce,
                onValueChange = { introduce ->
                    onProfileValueChanged(data.copy(introduce = introduce))
                },
                onClickButton = { onProfileValueChanged(data.copy(introduce = "")) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "이메일", style = typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            SmsTextField(
                placeHolder = "공개용 이메일 입력",
                modifier = Modifier.fillMaxWidth(),
                setText = data.contactEmail,
                onValueChange = { email ->
                    onProfileValueChanged(data.copy(contactEmail = email))
                },
                onClickButton = { onProfileValueChanged(data.copy(contactEmail = "")) }
            )
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
            ) { enteredMajor ->
                onProfileValueChanged(data.copy(enteredMajor = enteredMajor))
            }
            Spacer(modifier = Modifier.height(24.dp))
            ProfileTechStackInputComponent(
                techStack = detailStacks,
                onClick = changeView,
                onTechStackRemoved = { removedItem ->
                    onTechStackItemRemoved(removedItem)
                    onProfileValueChanged(data.copy(techStack = detailStacks.minus(removedItem)))
                }
            )
        }
    }
}

@Preview
@Composable
fun ProfileComponentPre() {
    ProfileComponent(
        selectedMajor = "FrontEnd",
        data = ProfileData(Uri.EMPTY, "", "", "", "", emptyList()),
        isRequired = {},
        detailStacks = listOf("a", "b", "c"),
        profileImageUri = Uri.EMPTY,
        isReadOnly = true,
        changeView = {},
        enteredMajor = "",
        onMajorBottomSheetOpenButtonClick = {},
        onPhotoPickBottomSheetOpenButtonClick = {},
        onProfileValueChanged = {},
        onTechStackItemRemoved = {}
    )
}