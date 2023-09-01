package com.sms.presentation.main.ui.fill_out_information.component.foreignlanguage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.SmsDialog
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.fill_out_information.data.ForeignLanguageInfo
import com.sms.presentation.main.viewmodel.FillOutViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ForeignLanguageComponent(
    navController: NavController,
    viewModel: FillOutViewModel,
    lifecycleScope: CoroutineScope,
) {
    val data = viewModel.getEnteredForeignLanguagesInformation()
    SMSTheme { colors, typography ->
        val foreignLanguageList = remember {
            mutableStateListOf(*data.foreignLanguages.toTypedArray())
        }
        val dialogState = remember {
            mutableStateOf(false)
        }
        val errorTitle = remember {
            mutableStateOf("")
        }
        val errorMsg = remember {
            mutableStateOf("")
        }
        val onClick = remember {
            mutableStateOf({})
        }

        if (dialogState.value) {
            SmsDialog(
                widthPercent = 1f,
                betweenTextAndButtonHeight = 37.dp,
                cancelButtonEnabled = false,
                title = errorTitle.value,
                msg = errorMsg.value,
                outLineButtonText = "취소",
                importantButtonText = "확인",
                outlineButtonOnClick = {
                    dialogState.value = false
                },
                importantButtonOnClick = {
                    onClick.value()
                    dialogState.value = false
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            SmsTitleText(text = "외국어", isRequired = false)
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "외국어", style = typography.body2, color = colors.N40)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(foreignLanguageList.size) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SmsTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            setText = foreignLanguageList[it].languageCertificateName,
                            onClickButton = {
                                foreignLanguageList[it] =
                                    foreignLanguageList[it].copy(languageCertificateName = "")
                            },
                            maxLines = 1,
                            placeHolder = "예) 토익",
                            onValueChange = { str ->
                                foreignLanguageList[it] =
                                    foreignLanguageList[it].copy(languageCertificateName = str.trim())
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SmsTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.7f),
                            setText = foreignLanguageList[it].score,
                            onClickButton = {
                                foreignLanguageList[it] = foreignLanguageList[it].copy(score = "")
                            },
                            maxLines = 1,
                            placeHolder = "990",
                            onValueChange = { str ->
                                foreignLanguageList[it] =
                                    foreignLanguageList[it].copy(score = str.trim())
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = {
                            foreignLanguageList.removeAt(it)
                        }) {
                            TrashCanIcon(modifier = Modifier.size(24.dp))
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .padding(start = 8.dp, top = 5.5.dp, bottom = 5.5.dp, end = 5.5.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                foreignLanguageList.add(
                                    ForeignLanguageInfo(
                                        languageCertificateName = "",
                                        score = ""
                                    )
                                )
                            }) {
                        Text(text = "+", style = typography.body1, color = colors.N30)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "추가", style = typography.body1, color = colors.N30)
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SmsRoundedButton(
                        modifier = Modifier
                            .weight(2f),
                        text = "이전",
                        state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(
                        modifier = Modifier
                            .weight(4f),
                        text = "다음",
                        state = ButtonState.Normal
                    ) {
                        viewModel.setEnteredForeignLanguagesInformation(
                            foreignLanguageList.filter {
                                it != ForeignLanguageInfo("", "")
                            }
                        )
                        navController.navigate("Projects")
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}