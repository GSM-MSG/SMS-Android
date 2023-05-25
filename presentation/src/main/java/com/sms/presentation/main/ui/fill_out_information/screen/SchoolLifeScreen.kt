package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
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
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.SchoolLifeComponent
import com.sms.presentation.main.ui.util.getFileNameFromUri

@Composable
fun SchoolLifeScreen(
    navController: NavController
) {
    val dreamBookFileUri = remember {
        mutableStateOf(Uri.EMPTY)
    }

    val fileName = remember {
        mutableStateOf("+ hwp 파일 추가")
    }

    val localStorageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            dreamBookFileUri.value = uri
        }

    if (dreamBookFileUri.value != Uri.EMPTY && dreamBookFileUri.value != null)
        fileName.value = getFileNameFromUri(LocalContext.current, dreamBookFileUri.value)!!

    SMSTheme { colors, _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.WHITE)
        ) {
            TopBarComponent(text = "정보입력", leftIcon = { BackButtonIcon() }, rightIcon = null) {

            }
            SmsSpacer()
            SchoolLifeComponent(fileName = fileName.value) {
                localStorageLauncher.launch("application/*")
            }
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
                        state = ButtonState.Normal
                    ) {
                        navController.navigate("WorkCondition")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}
