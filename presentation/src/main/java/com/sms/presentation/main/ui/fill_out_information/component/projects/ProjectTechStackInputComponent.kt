package com.sms.presentation.main.ui.fill_out_information.component.projects

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.DetailTechStackItem
import com.msg.sms.design.theme.SMSTheme
import com.msg.sms.design.util.AddGrayBody1Title
import com.sms.presentation.main.ui.mypage.component.profile.DisplaySearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectTechStackInputComponent(
    techStack: List<String>,
    isTechStackEmpty: Boolean,
    onClick: () -> Unit,
    onProjectTechStackValueChanged: (value: List<String>) -> Unit,
) {
    onProjectTechStackValueChanged(techStack)

    AddGrayBody1Title(titleText = "사용기술 (최대 20개)") {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DisplaySearchBar(
                modifier = Modifier
                    .onFocusChanged {
                        if (it.isFocused) {
                            onClick()
                        }
                    }
            )
            if (isTechStackEmpty) {
                SMSTheme { colors, typography ->
                    Text(
                        text = "사용 기술을 추가해 주세요.",
                        style = typography.caption1,
                        color = colors.ERROR
                    )
                }
            }
            LazyHorizontalStaggeredGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(
                        max = if (techStack.size <= 4) 50.dp
                        else if (techStack.size in 5..8) 100.dp
                        else if (techStack.size in 9..12) 150.dp
                        else if (techStack.size in 13..16) 200.dp
                        else 250.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalItemSpacing = 8.dp,
                rows = StaggeredGridCells.Fixed(
                    if (techStack.size <= 4) 1
                    else if (techStack.size in 5..8) 2
                    else if (techStack.size in 9..12) 3
                    else if (techStack.size in 13..16) 4
                    else 5
                )
            ) {
                itemsIndexed(techStack) { _: Int, item: String ->
                    DetailTechStackItem(
                        stack = item,
                        onClick = {
                            onProjectTechStackValueChanged(techStack.minus(item))
                        }
                    )
                }
            }
        }
    }
}