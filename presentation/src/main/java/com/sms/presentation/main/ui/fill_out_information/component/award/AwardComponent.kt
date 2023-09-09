package com.sms.presentation.main.ui.fill_out_information.component.award

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.toggle.ToggleComponent
import com.sms.presentation.main.ui.fill_out_information.FillOutInformationActivity
import com.sms.presentation.main.ui.fill_out_information.data.AwardData
import com.sms.presentation.main.ui.fill_out_information.data.AwardRequiredDataInfo
import com.sms.presentation.main.ui.util.hideKeyboard
import kotlinx.coroutines.cancel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AwardComponent(
    data: AwardData,
    awardValidation: AwardRequiredDataInfo,
    onCancelButtonClick: () -> Unit,
    onDateBottomSheetOpenButtonClick: () -> Unit,
    onAwardValueChanged: (award: AwardData) -> Unit
) {
    val context = LocalContext.current as FillOutInformationActivity
    val onNameFocusRequested = remember {
        mutableStateOf(false)
    }
    val onTypeFocusRequested = remember {
        mutableStateOf(false)
    }
    val onDateFocusRequested = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(awardValidation) {
        if (awardValidation.isNameEmpty) {
            onNameFocusRequested.value = true
            this.cancel()
        } else if (awardValidation.isTypeEmpty) {
            onTypeFocusRequested.value = true
            this.cancel()
        } else {
            onDateFocusRequested.value = true
            this.cancel()
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
                .focusGroup()
                .padding(top = 32.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AwardNameInputComponent(
                title = "이름",
                placeHolder = "수상 내역 이름 입력",
                isNameEmpty = awardValidation.isNameEmpty,
                text = data.name,
                onFocusRequested = onNameFocusRequested.value,
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
                onFocusRequested = onTypeFocusRequested.value,
                onButtonClick = { onAwardValueChanged(data.copy(type = "")) },
                onValueChange = { type ->
                    onAwardValueChanged(data.copy(type = type))
                }
            )
            AwardDateBarComponent(
                date = data.date,
                isDateEmpty = awardValidation.isDataEmpty,
                onFocusRequested = onDateFocusRequested.value,
                onClick = {
                    context.hideKeyboard()
                    onDateBottomSheetOpenButtonClick()
                }
            )
        }
    }
}