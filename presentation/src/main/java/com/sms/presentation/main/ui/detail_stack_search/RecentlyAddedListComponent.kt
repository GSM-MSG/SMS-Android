package com.sms.presentation.main.ui.detail_stack_search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.item.RecentlyAddedItem
import com.msg.sms.design.theme.SMSTheme

@Composable
fun RecentlyAddedListComponent(
    modifier: Modifier,
    list: List<String>,
    isSearching: Boolean,
    selectedList: List<String>,
    onClickButton: (stack: String, checked: Boolean) -> Unit,
    onClickRemoveAll: () -> Unit
) {
    SMSTheme { colors, typography ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 24.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "최근 추가", style = typography.title2, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = MutableInteractionSource(),
                            onClick = onClickRemoveAll
                        )
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "전체삭제",
                            style = typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = colors.N30,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Divider(
                    color = colors.N10,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            val currentList = if(isSearching) list else selectedList
            items(currentList.size) {
                RecentlyAddedItem(
                    stack = currentList[it],
                    selectedStack = selectedList,
                    onClick = { stack, checked -> onClickButton(stack, checked) }
                )
            }
        }
    }
}