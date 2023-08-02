package com.msg.sms.design.component.text

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.item.TechStackItem
import com.msg.sms.design.util.AddBody1TitleText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechStackRow(modifier: Modifier, techStack: List<String>) {
    LazyHorizontalStaggeredGrid(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = if (techStack.size <= 5) 30.dp else if (techStack.size in 5..12) 64.dp else 98.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalItemSpacing = 4.dp,
        rows = StaggeredGridCells.Fixed(if (techStack.size <= 5) 1 else if (techStack.size in 5..12) 2 else 3)
    ) {
        itemsIndexed(techStack) { _: Int, item: String ->
            TechStackItem(techStack = item)
        }
    }
}

@Preview
@Composable
fun TechStackRowPre() {
    AddBody1TitleText(titleText = "asddf", spaceSize = 8) {
        TechStackRow(
            modifier = Modifier,
            techStack = listOf(
                "Kotlin",
                "MVVM",
                "Compose UI",
                "Jetpack Compose",
                "Hilt",
                "Android Studio"
            )
        )
    }
}