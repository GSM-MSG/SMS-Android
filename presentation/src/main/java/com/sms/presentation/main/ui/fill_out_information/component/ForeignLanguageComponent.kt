package com.sms.presentation.main.ui.fill_out_information.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.student.request.CertificateInformationModel
import com.sms.presentation.main.viewmodel.FillOutViewModel

@Composable
fun ForeignLanguageComponent(
    navController: NavController,
    viewModel: FillOutViewModel
) {
    SMSTheme { colors, typography ->
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val foreignLanguageList = remember {
            mutableStateListOf("")
        }
        val foreignLanguageScoreList = remember {
            mutableStateListOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SmsTitleText(text = "외국어", isRequired = false)
                PagerIndicator(indexOfPointingNumber = 5, size = 6)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "외국어", style = typography.body2, color = colors.N40)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(foreignLanguageList.size) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SmsCustomTextField(
                            modifier = Modifier
                                .weight(41f),
                            clickAction = {},
                            placeHolder = "예) 토익",
                            endIcon = null,
                            onValueChange = { str ->
                                foreignLanguageList[it] = str
                            },
                            setChangeText = foreignLanguageList[it]
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SmsCustomTextField(
                            modifier = Modifier
                                .weight(23f),
                            clickAction = {},
                            placeHolder = "990",
                            endIcon = null,
                            onValueChange = { str -> foreignLanguageScoreList[it] = str },
                            setChangeText = foreignLanguageScoreList[it]
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = {
                            foreignLanguageList.removeAt(it)
                            foreignLanguageScoreList.removeAt(it)
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
                                foreignLanguageScoreList.add("")
                                foreignLanguageList.add("")
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
                            .weight(2f)
                            .height(48.dp),
                        text = "이전",
                        state = ButtonState.OutLine
                    ) {
                        navController.popBackStack()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    SmsRoundedButton(
                        modifier = Modifier
                            .weight(4f)
                            .height(48.dp),
                        text = "다음",
                        state = ButtonState.Normal
                    ) {
                        val foreignLanguage =
                            foreignLanguageList.mapIndexed { index: Int, name: String ->
                                CertificateInformationModel(
                                    languageCertificateName = name,
                                    score = foreignLanguageScoreList[index]
                                )
                            }
                        /*TODO(KH) foreignLanguage 넣어서 버튼 클릭 시 api 요청하도록 보내기 */
                        Log.d("TAG", "ForeignLanguageScreen: $foreignLanguage")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}