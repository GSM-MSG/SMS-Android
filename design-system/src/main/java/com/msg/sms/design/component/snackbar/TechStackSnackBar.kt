package com.msg.sms.design.component.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msg.sms.design.icon.CheckedIcon
import com.msg.sms.design.icon.UnCheckedIcon

@Composable
fun TechStackSnackBar(modifier: Modifier, visible: Boolean, added: Boolean, onClick: () -> Unit) {
    SmsSnackBar(
        modifier = modifier,
        text = "세부 스택이 ${if (added) "추가" else "해제 "}되었습니다.",
        visible = visible,
        leftIcon = { if (added) CheckedIcon() else UnCheckedIcon() },
        onClick = onClick
    )
}