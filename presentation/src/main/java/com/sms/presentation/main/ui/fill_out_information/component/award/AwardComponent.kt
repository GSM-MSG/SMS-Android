package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.toggle.ToggleComponent
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.ui.fill_out_information.data.AwardRequiredDataInfo
import com.sms.presentation.main.ui.util.hideKeyboard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AwardComponent(
    data: AwardData,
    isFirstValidationInCorrectItem: Boolean,
    awardValidation: AwardRequiredDataInfo,
    onCancelButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: () -> Unit,
    onAwardValueChanged: (award: AwardData) -> Unit,
) {
    val context = LocalContext.current as FillOutInformationActivity
    val (nameFocusRequester, typeFocusRequester, dateFocusRequester) = remember {
        FocusRequester.createRefs()
    }

    LaunchedEffect(awardValidation) {
        if (isFirstValidationInCorrectItem) {
            if (awardValidation.isNameEmpty) {
                nameFocusRequester.requestFocus()
            } else if (awardValidation.isTypeEmpty) {
                typeFocusRequester.requestFocus()
            } else if (awardValidation.isDateEmpty) {
                dateFocusRequester.requestFocus()
            }
        }
    }

    ToggleComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        name = data.name.ifEmpty { "수상" },
        contentVisible = data.isToggleOpen,
        onOpenButtonClick = { onAwardValueChanged(data.copy(isToggleOpen = !data.isToggleOpen)) },
        onCancelButtonClick = onCancelButtonClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AwardNameInputComponent(
                title = "이름",
                placeHolder = "수상 내역 이름 입력",
                isNameEmpty = awardValidation.isNameEmpty,
                text = data.name,
                focusRequester = nameFocusRequester,
                onButtonClick = { onAwardValueChanged(data.copy(name = "")) },
                onValueChange = { name ->
                    onAwardValueChanged(data.copy(name = name))
                }
            )
            AwardTypeInputComponent(
                title = "종류",
                placeHolder = "수상 종류입력",
                isTypeEmpty = awardValidation.isTypeEmpty,
                text = data.type,
                focusRequester = typeFocusRequester,
                onButtonClick = { onAwardValueChanged(data.copy(type = "")) },
                onValueChange = { type ->
                    onAwardValueChanged(data.copy(type = type))
                }
            )
            AwardDateBarComponent(
                date = data.date,
                isDateEmpty = awardValidation.isDateEmpty,
                focusRequester = dateFocusRequester,
                onClick = {
                    context.hideKeyboard()
                    onDateBottomSheetOpenButtonClick()
                }
            )
        }
    }
}