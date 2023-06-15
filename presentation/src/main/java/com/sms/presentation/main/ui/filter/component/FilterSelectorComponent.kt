package com.sms.presentation.main.ui.filter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.theme.SMSTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@Composable
fun FilterSelectorComponent(
    title: String,
    item: List<String>,
    selectedItem: (List<String>) -> Unit
) {
    val selectedList: Flow<ArrayList<String>> = flow {
        arrayListOf<String>()
    }

    LaunchedEffect("SelectedList") {
        selectedList.collect {
            selectedItem(it)
        }
    }



    SMSTheme { colors, typography ->
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = title,
                style = typography.body1,
                color = colors.BLACK,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp)
            ) {
                items(item.size) { index ->
                    Column {
                        FilterItem(text = item[index]) { checked, text ->
                            if (checked)
                                selectedList.map { it.add(text) }
                            else
                                selectedList.map { it.minus(text) }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterSelectorComponentPre() {
    FilterSelectorComponent(
        title = "분야",
        item = listOf(
            "FrontEnd",
            "BackEnd",
            "Android",
            "iOS",
            "Game",
            "Cyber Security",
            "Design",
            "AI",
            "IoT",
            "기타"
        )
    ) {

    }
}