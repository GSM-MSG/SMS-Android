package com.sms.presentation.main.ui.mypage.component.project

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.state.ExpandableProjectData
import com.sms.presentation.main.ui.mypage.state.ProjectTechStack

@Composable
fun ProjectComponent(
    data: ExpandableProjectData,
    techStacks: ProjectTechStack,
    onNameValueChange: (value: String) -> Unit,
    onKeyTaskValueChange: (value: String) -> Unit,
    onLinkNameChanged: (index: Int, value: String) -> Unit,
    onLinkChanged: (index: Int, value: String) -> Unit,
    onAddLinkButton: () -> Unit,
    onClickSearchBar: () -> Unit,
    onRemoveProjectImageButton: (list: List<String>) -> Unit,
    onRemoveBitmapButton: (index: Int) -> Unit,
    onRemoveProjectDetailStack: (value: String) -> Unit,
    onRemoveRelatedLInk: (index: Int) -> Unit,
    enteredList: List<Bitmap>,
    onOpenGallery: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        ProjectNameComponent(
            name = data.name,
            onValueChange = onNameValueChange
        )
        ProjectIconComponent(projectIcon = data.icon)
        ProjectPreviewComponent(
            list = data.projectImage,
            enteredList = enteredList,
            onClickRemoveButton = onRemoveProjectImageButton,
            onClickRemoveBitmapButton = onRemoveBitmapButton,
            onOpenGallery = onOpenGallery
        )
        ProjectTechStackComponent(
            techStack = techStacks.techStacks,
            onRemoveButton = onRemoveProjectDetailStack,
            onClickSearchBar = onClickSearchBar
        )
        ProjectKeyTaskComponent(data.keyTask, onValueChange = onKeyTaskValueChange)
        ProjectScheduleComponent()
        ProjectRelatedLinksComponent(
            relatedLinks = data.relatedLinks,
            onClick = onRemoveRelatedLInk,
            onLinkNameChanged = onLinkNameChanged,
            onLinkChanged = onLinkChanged,
            onAddButton = onAddLinkButton
        )
        ProjectDivider()
    }
}

@Preview
@Composable
private fun ProjectComponentPre() {
    ProjectComponent(
        data = ExpandableProjectData(
            name = "SMS",
            activityDuration = "2023 ~",
            projectImage = listOf(
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
            ),
            icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
            keyTask = "모이자 ㅋㅋ",
            relatedLinks = listOf(
                RelatedLinksData("Youtube", "https://dolmc.com"),
                RelatedLinksData("GitHujb", "https://youyu.com"),
                RelatedLinksData("X", "https://asdgasgw.com")
            ),
            isExpand = true
        ),
        onNameValueChange = {},
        onKeyTaskValueChange = {},
        enteredList = listOf(),
        onRemoveProjectImageButton = {},
        onRemoveBitmapButton = {},
        onOpenGallery = {},
        onAddLinkButton = {},
        onRemoveProjectDetailStack = { },
        onRemoveRelatedLInk = {},
        onLinkChanged = { _, _ -> },
        onClickSearchBar = {},
        onLinkNameChanged = { _, _ -> },
        techStacks = ProjectTechStack(listOf("Github", "Git", "Kotlin", "Android Studio")),
    )
}