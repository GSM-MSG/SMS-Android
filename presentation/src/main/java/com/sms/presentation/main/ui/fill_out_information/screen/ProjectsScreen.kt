package com.sms.presentation.main.ui.fill_out_information.screen

import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.toggle.ToggleComponent
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.component.ProjectIconInputComponent
import com.sms.presentation.main.ui.fill_out_information.component.ProjectNameInputComponent
import com.sms.presentation.main.ui.fill_out_information.component.ProjectPreviewInputComponent

@Preview
@Composable
fun ProjectsScreen() {
    val projectName = remember {
        mutableStateOf("")
    }

    SMSTheme { colors, typography ->
        ToggleComponent(name = "프로젝트", onCancelButtonClick = {}) {
            Spacer(modifier = Modifier.height(32.dp))
            ProjectNameInputComponent(
                text = projectName.value,
                onButtonClick = { projectName.value = "" }
            ) {
                projectName.value = it
            }
            Spacer(modifier = Modifier.height(24.dp))
            ProjectIconInputComponent(iconImageUri = Uri.EMPTY) {

            }
            Spacer(modifier = Modifier.height(24.dp))
            ProjectPreviewInputComponent(previewListSize = 0) {

            }
        }
    }
}