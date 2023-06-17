package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.SchoolLifeComponent
import com.sms.presentation.main.ui.util.getFileNameFromUri
import com.sms.presentation.main.ui.util.isfileExtensionCorrect
import com.sms.presentation.main.ui.util.textFieldChecker
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun SchoolLifeScreen(
    navController: NavController,
    viewModel: FillOutViewModel
) {
    val data = viewModel.getEnteredSchoolLifeInformation()

    val context = LocalContext.current

    val dreamBookFileUri = remember {
        mutableStateOf(data.dreamBookFileUri)
    }

    val gsmAuthenticationScore = remember {
        mutableStateOf(data.gsmAuthenticationScore)
    }

    val fileName = remember {
        mutableStateOf("")
    }

    val isFileExtensionInCorrect = remember {
        mutableStateOf(false)
    }

    val localStorageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                Log.d("filename", getFileNameFromUri(context, uri).toString())
                if (getFileNameFromUri(context, uri)!!.isfileExtensionCorrect()) {
                    isFileExtensionInCorrect.value = false
                    dreamBookFileUri.value = uri
                } else {
                    isFileExtensionInCorrect.value = true
                }
            }
        }

    if (dreamBookFileUri.value != Uri.EMPTY) {
        fileName.value = getFileNameFromUri(LocalContext.current, dreamBookFileUri.value)!!
    }

    if (isFileExtensionInCorrect.value) {
        SmsDialog(
            widthPercent = 1f,
            title = "에러",
            msg = "파일의 확장자가 hwp, hwpx가 아닙니다.",
            outLineButtonText = "취소",
            normalButtonText = "확인",
            outlineButtonOnClick = { isFileExtensionInCorrect.value = false },
            normalButtonOnClick = { isFileExtensionInCorrect.value = false }
        )
    }


    SMSTheme { colors, _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            TopBarComponent(text = "정보입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {

            }
            SmsSpacer()
            SchoolLifeComponent(
                fileName = fileName.value,
                enteredGsmAuthenticationScore = gsmAuthenticationScore.value,
                gsmAuthenticationScore = {
                    gsmAuthenticationScore.value = it
                },
                addDreamBook = {
                    localStorageLauncher.launch("application/*")
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    SmsRoundedButton(
                        text = "이전",
                        modifier = Modifier
                            .weight(2f)
                            .height(48.dp),
                        state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(
                        text = "다음",
                        modifier = Modifier
                            .weight(4f)
                            .height(48.dp),
                        enabled = dreamBookFileUri.value != Uri.EMPTY && textFieldChecker(gsmAuthenticationScore.value),
                        state = ButtonState.Normal
                    ) {
                        viewModel.setEnteredSchoolLifeInformation(
                            gsmAuthenticationScore = gsmAuthenticationScore.value,
                            dreamBookFileUri = dreamBookFileUri.value
                        )
                        navController.navigate("WorkCondition")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}
