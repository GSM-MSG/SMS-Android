package com.sms.presentation.main.ui.mypage.section

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.component.project.ProjectComponent
import com.sms.presentation.main.ui.mypage.state.ExpandableProjectData
import com.sms.presentation.main.ui.mypage.state.ProjectTechStack

@Composable
fun ProjectsSection(
    data: ExpandableProjectData,
    techStacks: ProjectTechStack,
    enteredPreviews: List<Bitmap>,
    onNameValueChange: (value: String) -> Unit,
    onKeyTaskValueChange: (value: String) -> Unit,
    onLinkNameChanged: (index: Int, value: String) -> Unit,
    onLinkChanged: (index: Int, value: String) -> Unit,
    onRemoveProjectImage: (list: List<String>) -> Unit,
    onAddBitmap: (list: List<Bitmap>) -> Unit,
    onAddLink: () -> Unit,
    onClickSearchBar: () -> Unit,
    onRemoveBitmapButton: (itemIndex: Int) -> Unit,
    onRemoveProjectDetailStack: (value: String) -> Unit,
    onRemoveRelatedLink: (index: Int) -> Unit,
) {
    val context = LocalContext.current
    val multiGalleyLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) {
        if (enteredPreviews.size + data.projectImage.size + it.size <= 4) {
            val enteredList = if (Build.VERSION.SDK_INT < 28) {
                it.map { uri -> MediaStore.Images.Media.getBitmap(context.contentResolver, uri) }
            } else {
                it.map { uri ->
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                }
            }
            onAddBitmap(enteredList)
        } else {
            Toast.makeText(context, "최대 4개까지 가능합니다.", Toast.LENGTH_SHORT).show()
        }
    }
    ProjectComponent(
        data = data,
        techStacks = techStacks,
        onNameValueChange = onNameValueChange,
        onKeyTaskValueChange = onKeyTaskValueChange,
        onLinkNameChanged = onLinkNameChanged,
        onLinkChanged = onLinkChanged,
        onRemoveProjectImageButton = onRemoveProjectImage,
        onRemoveBitmapButton = { onRemoveBitmapButton(it) },
        enteredList = enteredPreviews,
        onOpenGallery = { multiGalleyLauncher.launch("image/*") },
        onAddLinkButton = onAddLink,
        onRemoveProjectDetailStack = onRemoveProjectDetailStack,
        onRemoveRelatedLInk = onRemoveRelatedLink,
        onClickSearchBar = onClickSearchBar
    )
}

@Preview
@Composable
private fun ProjectSectionPre() {
    ProjectsSection(data = ExpandableProjectData(
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
        ), isExpand = true
    ),
        enteredPreviews = listOf(),
        onNameValueChange = {},
        onKeyTaskValueChange = {},
        onLinkNameChanged = { _, _ -> },
        onLinkChanged = { _, _ -> },
        onRemoveProjectImage = {},
        onAddBitmap = {},
        onAddLink = {},
        onRemoveBitmapButton = {},
        onRemoveProjectDetailStack = {},
        onRemoveRelatedLink = {},
        onClickSearchBar = {},
        techStacks = ProjectTechStack(listOf())
    )
}