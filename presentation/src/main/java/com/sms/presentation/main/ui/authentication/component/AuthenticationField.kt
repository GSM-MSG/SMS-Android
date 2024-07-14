package com.sms.presentation.main.ui.authentication.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.segmented_control.SegmentedControl
import com.msg.sms.design.component.textfield.SmsBasicTextField
import com.msg.sms.design.icon.ArrowDownIcon
import com.msg.sms.design.icon.FileIcon
import com.msg.sms.design.icon.XMarkIcon
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.BOOLEAN
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.FILE
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.NUMBER
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.SELECT
import com.msg.sms.domain.model.authentication.response.AuthenticationFieldType.TEXT
import com.msg.sms.domain.model.authentication.response.AuthenticationSectionFieldValuesModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationField(
    modifier: Modifier = Modifier,
    fieldType: AuthenticationFieldType,
    values: List<AuthenticationSectionFieldValuesModel>?,
    placeHolder: String?,
    scoreDescription: String?,
    getFileName: (uri: Uri) -> Pair<String, String>,
    showExtensionError: () -> Unit,
    onSelect: (values: List<AuthenticationSectionFieldValuesModel>) -> String,
    enteredValue: (enteredValue: String, selectedId: String, uri: Uri?) -> Unit,
) {

    SMSTheme { _, typography ->
        var value by remember {
            mutableStateOf("")
        }
        var selectedId by remember {
            mutableStateOf("")
        }
        var bottomSheetState by remember {
            mutableStateOf(false)
        }
        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    val fileInfo = getFileName(it)
                    if (listOf("hwp", "hwpx", "pdf").contains(fileInfo.second)) {
                        value = getFileName(it).first
                        enteredValue(value, selectedId, it)
                    } else {
                        showExtensionError()
                    }
                }
            }

        SMSTheme { colors, _ ->
            if (fieldType == SELECT && values != null && bottomSheetState) {
                ModalBottomSheet(
                    dragHandle = null,
                    onDismissRequest = { bottomSheetState = false }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .background(colors.WHITE)
                            .padding(vertical = 16.dp)
                            .navigationBarsPadding(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(values) { _, item ->
                            val interactionSource = remember { MutableInteractionSource() }
                            val isPressed by interactionSource.collectIsPressedAsState()
                            val buttonColor by animateColorAsState(
                                if (isPressed) colors.N10 else colors.WHITE,
                                label = "",
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .fillMaxWidth()
                                    .background(color = buttonColor)
                                    .clickable(
                                        onClick = {
                                            enteredValue(item.value, item.selectId, null)
                                            bottomSheetState = false
                                            value = item.value
                                        },
                                        interactionSource = interactionSource,
                                        indication = null
                                    )
                                    .padding(12.dp),
                            ) {
                                Text(
                                    text = item.value,
                                    style = typography.body1,
                                    fontWeight = FontWeight.Normal,
                                    color = colors.N50
                                )
                            }
                        }
                    }
                }
            }

            if (fieldType == BOOLEAN) {
                SegmentedControl(
                    modifier = modifier.fillMaxWidth(),
                    items = values?.map { it.value } ?: listOf(),
                    onItemSelection = { index ->
                        selectedId = values?.get(index)?.selectId ?: ""
                        enteredValue(value, selectedId, null)
                    }
                )
            } else {
                SmsBasicTextField(
                    modifier = modifier.fillMaxWidth(),
                    text = value,
                    readOnly = fieldType == SELECT || fieldType == FILE,
                    onValueChange = {
                        value = it
                        enteredValue(value, selectedId, null)
                    },
                    placeHolder = placeHolder ?: scoreDescription ?: "",
                    trailingIcon = {
                        IconButton(onClick = {
                            when (fieldType) {
                                TEXT, NUMBER -> {
                                    value = ""
                                }

                                FILE -> {
                                    launcher.launch("*/*")
                                }

                                SELECT -> {
                                    bottomSheetState = true
                                }

                                else -> {}
                            }
                        }) {
                            when (fieldType) {
                                TEXT -> XMarkIcon(modifier = Modifier.size(24.dp))

                                FILE -> FileIcon(modifier = Modifier.size(24.dp))

                                SELECT -> ArrowDownIcon(modifier = Modifier.size(24.dp))

                                else -> {}
                            }
                        }
                    },
                )
                if (!scoreDescription.isNullOrBlank()) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = scoreDescription,
                        style = typography.caption1,
                        color = Color(0xFFA0ACB1)
                    )
                }
            }
        }
    }
}