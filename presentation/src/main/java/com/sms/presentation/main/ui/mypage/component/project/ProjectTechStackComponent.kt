package com.sms.presentation.main.ui.mypage.component.project

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.DetailTechStackItem
import com.sms.presentation.main.ui.mypage.component.profile.DisplaySearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectTechStackComponent(
    techStack: List<String>,
    onRemoveButton: (value: String) -> Unit,
    onClickSearchBar: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DisplaySearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            onSearchBarClick = onClickSearchBar
        )
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
            items(techStack) { item: String ->
                DetailTechStackItem(
                    stack = item,
                    onClick = { onRemoveButton(item) })
            }
        }
    }
}

@Preview
@Composable
private fun ProjectTechStackComponentPre() {
    ProjectTechStackComponent(
        listOf(
            "Kotlin",
            "Android Studio",
            "git",
            "Github",
            "Jetpack Compose"
        ), onRemoveButton = {}
    ) {}
}