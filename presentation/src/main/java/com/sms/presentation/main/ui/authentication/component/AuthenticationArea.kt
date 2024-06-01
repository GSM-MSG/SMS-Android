package com.sms.presentation.main.ui.authentication.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.authentication.enum.InputItemEnum

@Composable
fun AuthenticationArea(
    modifier: Modifier = Modifier,
    title: String, // (e.g. 전공 영역, 인문.인성 영역, 외국어 영역), area
    items: List<SectionItem>,
    file: List<String> = listOf(),// 추가 첨부파일 e.g. 독서 활동 템플릿, 외국어 영역 항목별 점수표
) {
    val isExpanded = rememberSaveable {
        mutableStateOf(false)
    }
    SMSTheme { colors, _ ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 4000.dp)
                .background(color = colors.WHITE)
                .animateContentSize()
        ) {
            item {
                TitleHeader(
                    modifier = Modifier,
                    titleText = title,
                    isExpand = isExpanded.value,
                    isExpandable = true,
                    onClickToggleButton = {
                        isExpanded.value = !isExpanded.value
                    }
                )
            }
            if (isExpanded.value) {
                itemsIndexed(items) { index, it ->
                    AuthenticationSection(
                        modifier = Modifier.padding(start = 20.dp, top = 12.dp, end = 20.dp),
                        section = it.section,
                        maxCount = it.maxCount,
                        fields = it.fields,
                        description = it.description,
                        onClickButton = {},
                        onValueChanged = { changedIndex, changedItem ->

                        }
                    )
                    if (index != items.size - 1) {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
                items(file) {

                }
            }
        }
    }
}

@Preview
@Composable
private fun AuthenticationAreaPreview() {
    AuthenticationArea(
        title = "전공 영역",
        items = listOf(
            SectionItem(
                section = "aa",
                fieldScore = 50,
                description = null,
                maxCount = 10,
                fields = listOf(
                    FieldItem(
                        name = "aa",
                        type = InputItemEnum.STRING,
                        values = null,
                        placeHolder = ""
                    )
                )
            )
        ),
    )
}