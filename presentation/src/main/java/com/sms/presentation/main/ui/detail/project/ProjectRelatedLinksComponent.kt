package com.sms.presentation.main.ui.detail.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.domain.model.common.LinkModel

@Composable
fun ProjectRelatedLinksComponent(modifier: Modifier = Modifier, links: List<LinkModel>) {
    val itemHeight = remember {
        mutableStateOf(0.dp)
    }
    LazyColumn(
        modifier = modifier.heightIn(max = 200.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(links.size) {
            ProjectLinkComponent(
                linksData = links[it],
                getHeight = { height -> itemHeight.value = height })
        }
    }
}

@Preview
@Composable
fun RelatedLinksComponentPre() {
    ProjectRelatedLinksComponent(
        links = listOf(
            LinkModel("Youtube", "https://dolmc.com"),
            LinkModel("GitHub", "https://youyu.com"),
            LinkModel(
                "X",
                "https://asdgasasdfljkasjhdlfalsdhfklhalksdhfklhaskldjfhlkasdhfklahsdfkjgw.com"
            )
        )
    )
}