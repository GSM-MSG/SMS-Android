package com.msg.sms.design.component.selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.design_system.R

@Composable
fun SmsCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onClick: () -> Unit,
) {
    Image(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = MutableInteractionSource()
        ) {
          onClick()
        },
        painter = painterResource(
            id =
            if (checked) R.drawable.ic_checked_box else R.drawable.ic_unchecked_box
        ),
        contentDescription = "check box"
    )
}

@Preview
@Composable
fun SmsCheckBoxPre() {
    Column {
        SmsCheckBox(checked = true, onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
        SmsCheckBox(checked = false, onClick = {})
    }
}