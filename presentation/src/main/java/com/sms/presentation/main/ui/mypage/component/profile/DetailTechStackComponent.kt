package com.sms.presentation.main.ui.mypage.component.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.DetailTechStackItem
import com.msg.sms.design.util.AddGrayBody1Title

@Composable
fun DetailTechStackComponent(addedList: List<String>) {
    val addedListedValue = remember {
        mutableStateListOf(*addedList.toTypedArray())
    }
    Column {
        AddGrayBody1Title(titleText = "세부스택 (5개)") {
            DisplaySearchBar()
        }
        LazyColumn(
            modifier = Modifier.heightIn(max = 300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(addedListedValue) { stack: String ->
                DetailTechStackItem(stack = stack, onClick = {
                    addedListedValue.remove(stack)
                })
            }
        }
    }
}

@Preview
@Composable
fun DetailTechStackComponentPre() {
    DetailTechStackComponent(listOf("Android Stdio", "Kotlin", "Flutter"))
}