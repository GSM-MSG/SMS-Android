package com.sms.presentation.main.ui.detail.link

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.detail.data.RelatedLinksData

@Composable
fun RelatedLinksComponent(modifier: Modifier = Modifier, links: List<RelatedLinksData>) {
    val itemHeight = remember {
        mutableStateOf(0.dp)
    }
    LazyColumn(
        modifier = modifier.heightIn(max = 200.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(links.size) {
            LinkComponent(
                linksData = links[it],
                getHeight = { height -> itemHeight.value = height })
        }
    }
}

@Preview
@Composable
fun RelatedLinksComponentPre() {
    RelatedLinksComponent(
        links = listOf(
            RelatedLinksData("Youtube", "https://dolmc.com"),
            RelatedLinksData("GitHub", "https://youyu.com"),
            RelatedLinksData(
                "X",
                "https://asdgasasdfljkasjhdlfalsdhfklhalksdhfklhaskldjfhlkasdhfklahsdfkjgw.com"
            )
        )
    )
}