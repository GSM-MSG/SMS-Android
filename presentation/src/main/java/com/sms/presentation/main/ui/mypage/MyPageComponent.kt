package com.sms.presentation.main.ui.mypage

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.BlackAddItemButton
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopNavigation
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.icon.BlackLogoutIcon
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.component.button.SaveButtonComponent
import com.sms.presentation.main.ui.mypage.section.AwardSection
import com.sms.presentation.main.ui.mypage.section.CertificationsSection
import com.sms.presentation.main.ui.mypage.section.ForeignLanguagesSection
import com.sms.presentation.main.ui.mypage.section.MilitaryServiceSection
import com.sms.presentation.main.ui.mypage.section.ProfileSection
import com.sms.presentation.main.ui.mypage.section.ProjectsSection
import com.sms.presentation.main.ui.mypage.section.SchoolLifeSection
import com.sms.presentation.main.ui.mypage.section.WorkConditionSection
import com.sms.presentation.main.ui.mypage.state.ActivityDuration
import com.sms.presentation.main.ui.mypage.state.FormOfEmployment
import com.sms.presentation.main.ui.mypage.state.MilitaryService
import com.sms.presentation.main.ui.mypage.state.MyProfileData
import com.sms.presentation.main.ui.mypage.state.ProjectTechStack

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPageComponent(
    myProfileData: MyProfileData,
    isExpandedProject: List<Boolean>,
    isExpandedAward: List<Boolean>,
    projects: List<ProjectData>,
    awards: List<AwardData>,
    bitmapPreviews: List<List<Bitmap>>,
    selectedTechList: List<String>,
    selectedTechListOnProject: List<ProjectTechStack>,
    iconBitmaps: List<Bitmap?>,
    setBitmap: (index: Int, element: Bitmap) -> Unit,
    onAddProject: () -> Unit,
    onAddAward: () -> Unit,
    onAddRegion: () -> Unit,
    onAddCertificate: () -> Unit,
    onAddForeignLanguage: () -> Unit,
    onOpenStart: (index: Int) -> Unit,
    onOpenEnd: (index: Int) -> Unit,
    onChangeProgressState: (index: Int) -> Unit,
    onEnteredMajorValue: (value: String) -> Unit,
    onProfileValueChange: (value: MyProfileData) -> Unit,
    onClickMilitaryOpenButton: () -> Unit,
    onClickOpenWorkForm: () -> Unit,
    onClickTopLeftButton: () -> Unit,
    onClickTopRightButton: () -> Unit,
    onOpenNumberPicker: (index: Int) -> Unit,
    onClickMajorButton: () -> Unit,
    onExpandProjectClick: (index: Int) -> Unit,
    onExpandAwardClick: (index: Int) -> Unit,
    onRemoveDetailStack: (value: String) -> Unit,
    onRemoveAward: (index: Int) -> Unit,
    onRemoveProject: (index: Int) -> Unit,
    onAddBitmapPreview: (projectIndex: Int, item: List<Bitmap>) -> Unit,
    onRemoveBitmapPreview: (projectIndex: Int, itemIndex: Int) -> Unit,
    onRemoveProjectDetailStack: (index: Int, value: String) -> Unit,
    onProjectValueChange: (index: Int, data: ProjectData) -> Unit,
    onProjectSearchBar: (index: Int) -> Unit,
    onAwardValueChange: (index: Int, value: AwardData) -> Unit,
    onMyPageSearchBar: () -> Unit,
    onSaveButtonClick: () -> Unit,
) {
    val isButtonClicked = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(color = Color.White)
        ) {
            item {
                TopNavigation(
                    text = "마이페이지",
                    leftIcon = { BackButtonIcon() },
                    rightIcon = { BlackLogoutIcon() },
                    onClickRightButton = onClickTopRightButton,
                    onClickLeftButton = onClickTopLeftButton,
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "프로필 *")
            }
            item {
                ProfileSection(
                    myProfileData = myProfileData,
                    selectedTechList = selectedTechList,
                    onClickMajorComponent = onClickMajorButton,
                    onClickSearchBar = onMyPageSearchBar,
                    onRemoveDetailStack = onRemoveDetailStack,
                    onValueChange = onProfileValueChange,
                    onEnteredMajorValue = onEnteredMajorValue
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "학교생활 *")
            }
            item {
                SchoolLifeSection(
                    score = myProfileData.gsmAuthenticationScore,
                    onValueChange = {
                        onProfileValueChange(myProfileData.copy(gsmAuthenticationScore = it.toInt()))
                    },
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "근무 조건 *")
            }
            item {
                WorkConditionSection(
                    wantWorkingAreas = myProfileData.regions,
                    wantPay = myProfileData.salary.toString(),
                    wantWorkForm = myProfileData.formOfEmployment,
                    onClickOpenButton = onClickOpenWorkForm,
                    onClickAddButton = onAddRegion,
                    onValueChange = { index, item ->
                        val regionList = myProfileData.regions.toMutableList()
                        regionList[index] = item
                        onProfileValueChange(myProfileData.copy(regions = regionList))
                    },
                    onPayrollValueChange = {
                        onProfileValueChange(myProfileData.copy(salary = it.toInt()))
                    },
                    onClickRemoveButton = {
                        val regionList = myProfileData.regions.toMutableList()
                        regionList.removeAt(index = it)
                        onProfileValueChange(myProfileData.copy(regions = regionList))
                    })
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "병역 *")
            }
            item {
                MilitaryServiceSection(
                    setMilitary = myProfileData.militaryService.text,
                    onClickMilitaryOpenButton = onClickMilitaryOpenButton
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "자격증")
            }
            item {
                CertificationsSection(
                    certifications = myProfileData.certificates,
                    onValueChange = { index, value ->
                        val certificates = myProfileData.certificates.toMutableList()
                        certificates[index] = value
                        onProfileValueChange(myProfileData.copy(certificates = certificates))
                    },
                    onClickRemoveButton = {
                        val certificates = myProfileData.certificates.toMutableList()
                        certificates.removeAt(index = it)
                        onProfileValueChange(myProfileData.copy(certificates = certificates))
                    },
                    onClickAddButton = onAddCertificate
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "외국어")
            }
            item {
                ForeignLanguagesSection(
                    foreignLanguages = myProfileData.languageCertificates.map {
                        Pair(
                            it.languageCertificateName,
                            it.score
                        )
                    },
                    onValueChangeForeignName = { index, value ->
                        val foreignLanguages = myProfileData.languageCertificates.toMutableList()
                        foreignLanguages[index] =
                            foreignLanguages[index].copy(languageCertificateName = value)
                        onProfileValueChange(myProfileData.copy(languageCertificates = foreignLanguages))
                    },
                    onValueChangeForeignValue = { index, value ->
                        val foreignLanguages = myProfileData.languageCertificates.toMutableList()
                        foreignLanguages[index] = foreignLanguages[index].copy(score = value)
                        onProfileValueChange(myProfileData.copy(languageCertificates = foreignLanguages))
                    },
                    onClickRemoveButton = {
                        val foreignLanguages = myProfileData.languageCertificates.toMutableList()
                        foreignLanguages.removeAt(it)
                        onProfileValueChange(myProfileData.copy(languageCertificates = foreignLanguages))
                    },
                    onClickAddButton = onAddForeignLanguage
                )
                SmsSpacer()
            }
            projects.forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it.name,
                        isExpandable = true,
                        isRemovable = true,
                        isExpand = isExpandedProject[index],
                        onClickToggleButton = {
                            onExpandProjectClick(index)
                        },
                        onClickRemoveButton = {
                            onRemoveProject(index)
                        })
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = isExpandedProject[index],
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        val itemData = projects[index]
                        ProjectsSection(
                            data = itemData,
                            techStacks = selectedTechListOnProject[index],
                            onLinkNameChanged = { itemIndex, value ->
                                val relatedLink = itemData.relatedLinks.toMutableList()
                                relatedLink.set(
                                    index = itemIndex,
                                    element = itemData.relatedLinks[itemIndex].copy(name = value)
                                )
                                onProjectValueChange(
                                    index,
                                    itemData.copy(relatedLinks = relatedLink)
                                )
                            },
                            onLinkChanged = { itemIndex, value ->
                                val relatedLink = itemData.relatedLinks.toMutableList()
                                relatedLink.set(
                                    index = itemIndex,
                                    element = itemData.relatedLinks[itemIndex].copy(link = value)
                                )
                                onProjectValueChange(
                                    index,
                                    itemData.copy(relatedLinks = relatedLink)
                                )
                            },
                            onRemoveProjectImage = {
                                onProjectValueChange(
                                    index,
                                    itemData.copy(projectImage = it)
                                )
                            },
                            onRemoveProjectDetailStack = { onRemoveProjectDetailStack(index, it) },
                            onAddBitmap = { onAddBitmapPreview(index, it) },
                            onAddLink = {
                                val relatedLink = itemData.relatedLinks.toMutableList()
                                relatedLink.add(RelatedLinksData(name = "", link = ""))
                                onProjectValueChange(
                                    index,
                                    itemData.copy(relatedLinks = relatedLink)
                                )
                            },
                            onRemoveBitmapButton = {
                                onRemoveBitmapPreview(index, it)
                            },
                            enteredPreviews = bitmapPreviews[index],
                            onRemoveRelatedLink = {
                                onProjectValueChange(
                                    index,
                                    itemData.copy(relatedLinks = itemData.relatedLinks.filterIndexed { index, _ -> index != it })
                                )
                            },
                            onClickSearchBar = { onProjectSearchBar(index) },
                            onProjectValueChange = {
                                onProjectValueChange(
                                    index,
                                    it
                                )
                            },
                            setBitmap = { setBitmap(index, it) },
                            bitmap = iconBitmaps[index],
                            onOpenStart = { onOpenStart(index) },
                            onOpenEnd = { onOpenEnd(index) },
                            onChangeProgressState = { onChangeProgressState(index) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    BlackAddItemButton(modifier = Modifier.align(Alignment.TopEnd)) {
                        onAddProject()
                    }
                }
            }
            awards.forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it.title,
                        isExpandable = true,
                        isRemovable = true,
                        isExpand = isExpandedAward[index],
                        onClickRemoveButton = { onRemoveAward(index) },
                        onClickToggleButton = {
                            onExpandAwardClick(index)
                        })
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = isExpandedAward[index],
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        AwardSection(
                            awardData = it,
                            onNameValueChange = {
                                onAwardValueChange(index, awards[index].copy(title = it))
                            },
                            onTypeValueChange = {
                                onAwardValueChange(index, awards[index].copy(organization = it))
                            },
                            onDateValueChange = {
                                onAwardValueChange(index, awards[index].copy(date = it))
                            },
                            onClickCalendar = { onOpenNumberPicker(index) }
                        )
                        SmsSpacer()
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    BlackAddItemButton(modifier = Modifier.align(Alignment.TopEnd)) {
                        onAddAward()
                    }
                }
            }
            item {
                SmsSpacer(height = 128)
            }
        }
        SaveButtonComponent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp),
            visibility = true,
            enabled = !isButtonClicked.value,
            onClickSaveButton = {
                isButtonClicked.value = true
                onSaveButtonClick()
            }
        )
    }
}

@Preview
@Composable
private fun MyPageComponentPre() {
    MyPageComponent(
        myProfileData = MyProfileData(
            name = "",
            introduce = "",
            portfolioUrl = "",
            grade = 0,
            classNum = 0,
            number = 0,
            department = "",
            major = "",
            profileImg = "",
            contactEmail = "",
            gsmAuthenticationScore = 0,
            formOfEmployment = FormOfEmployment.NOT_SELECT,
            regions = listOf(),
            militaryService = MilitaryService.NOT_SELECT,
            salary = 0,
            languageCertificates = listOf(),
            certificates = listOf(),
            profileImageBitmap = null
        ),
        bitmapPreviews = listOf(),
        projects = listOf(
            ProjectData(
                name = "SMS",
                activityDuration = ActivityDuration(start = "2023. 03", end = null),
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
                    RelatedLinksData("GitHub", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                ),
                techStacks = listOf("Android", "Kotlin"),
                description = "sljfkd"
            )
        ),
        awards = listOf(),
        selectedTechList = listOf("Android", ""),
        selectedTechListOnProject = listOf(),
        onAddProject = {},
        onAddAward = {},
        onAddCertificate = {},
        onAddForeignLanguage = {},
        onAddRegion = {},
        isExpandedAward = listOf(),
        isExpandedProject = listOf(),
        onExpandAwardClick = {},
        onExpandProjectClick = {},
        onClickMilitaryOpenButton = {},
        onClickTopLeftButton = {},
        onClickTopRightButton = {},
        onClickMajorButton = {},
        onClickOpenWorkForm = {},
        onMyPageSearchBar = {},
        onProjectSearchBar = {},
        onRemoveBitmapPreview = { _, _ -> },
        onRemoveDetailStack = {},
        onRemoveProjectDetailStack = { _, _ -> },
        onRemoveProject = {},
        onProfileValueChange = {},
        onProjectValueChange = { _, _ -> },
        onAwardValueChange = { _, _ -> },
        onAddBitmapPreview = { _, _ -> },
        onRemoveAward = {},
        onEnteredMajorValue = {},
        onSaveButtonClick = {},
        iconBitmaps = listOf(),
        setBitmap = { _, _ -> },
        onOpenNumberPicker = {},
        onOpenEnd = {},
        onOpenStart = {},
        onChangeProgressState = {}
    )
}