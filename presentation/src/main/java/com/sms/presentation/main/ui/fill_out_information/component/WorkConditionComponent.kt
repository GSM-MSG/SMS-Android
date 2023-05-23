package com.sms.presentation.main.ui.fill_out_information.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.button.ButtonState
import com.msg.sms.design.component.button.SmsRoundedButton
import com.msg.sms.design.component.indicator.PagerIndicator
import com.msg.sms.design.component.text.SmsTitleText
import com.msg.sms.design.component.textfield.SmsCustomTextField
import com.msg.sms.design.component.textfield.SmsTextField
import com.msg.sms.design.icon.OpenButtonIcon
import com.msg.sms.design.icon.TrashCanIcon
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkConditionComponent(
    wantWorkingCondition: String,
    bottomSheetState: ModalBottomSheetState,
    navController: NavController
) {
    SMSTheme { colors, typography ->
        val wantWorkingArea = remember {
            mutableStateListOf("")
        }
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(end = 20.dp, start = 20.dp, top = 20.dp)
                    .weight(1f)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        SmsTitleText(text = "근무 조건", isRequired = true)
                        PagerIndicator(
                            indexOfPointingNumber = 2, size = 6, modifier = Modifier.align(
                                Alignment.CenterEnd
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "희망 고용 형태", style = typography.body2)
                    Spacer(modifier = Modifier.height(8.dp))
                    SmsCustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        endIcon = { OpenButtonIcon() },
                        placeHolder = "정규직",
                        readOnly = true,
                        clickAction = {
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        },
                        setChangeText = wantWorkingCondition
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "희망 연봉", style = typography.body2)
                    Spacer(modifier = Modifier.height(8.dp))
                    SmsTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeHolder = "희망 연봉 (10,000원 단위)",
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "근무 지역", style = typography.body2)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(wantWorkingArea.size) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SmsCustomTextField(
                            modifier = Modifier
                                .weight(1.0f),
                            clickAction = {},
                            placeHolder = "근무 희망 지역 입력",
                            endIcon = null,
                            onValueChange = { str -> wantWorkingArea[it] = str },
                            setChangeText = wantWorkingArea[it]
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = { wantWorkingArea.removeAt(it) }) {
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
                                wantWorkingArea.add("")
                            }) {
                        Text(text = "+", style = typography.body1, color = colors.N30)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "추가", style = typography.body1, color = colors.N30)
                    }
                }
            }
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
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
                        navController.navigate("MilitaryService")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}