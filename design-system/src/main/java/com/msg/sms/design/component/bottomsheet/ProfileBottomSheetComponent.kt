package com.msg.sms.design.component.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileBottomSheetComponent(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    icon: @Composable () -> Unit,
    text: String,
    onClick: () -> Unit
) {
    SMSTheme { colors, typography ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.fillMaxWidth(0.05f))
            icon()
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = text,
                color = colors.N50,
                style = typography.body1,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 13.5.dp)
            )
        }
    }
}