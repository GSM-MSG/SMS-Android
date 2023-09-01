package com.msg.sms.design.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme

@Composable
fun Body1Text(titleText: String) {
    SMSTheme { colors, typography ->
        Text(
            text = titleText,
            style = typography.body1,
            fontWeight = FontWeight.Normal,
            color = colors.BLACK
        )
    }
}

@Composable
fun GrayBody1Text(titleText: String) {
    SMSTheme { colors, typography ->
        Text(
            text = titleText,
            style = typography.body1,
            fontWeight = FontWeight.Normal,
            color = colors.N40
        )
    }
}

@Composable
fun AddBody1Title(
    modifier: Modifier = Modifier,
    titleText: String,
    spaceSize: Int = 16,
    component: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Body1Text(titleText = titleText)
        Spacer(modifier = Modifier.height(spaceSize.dp))
        component()
    }
}

@Composable
fun AddGrayBody1Title(
    modifier: Modifier = Modifier,
    titleText: String,
    spaceSize: Int = 8,
    component: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        GrayBody1Text(titleText = titleText)
        Spacer(modifier = Modifier.height(spaceSize.dp))
        component()
    }
}

@Preview
@Composable
fun AddTitlePre() {
    AddBody1Title(titleText = "text") {
        Text(text = "lsdjkf")
    }
}