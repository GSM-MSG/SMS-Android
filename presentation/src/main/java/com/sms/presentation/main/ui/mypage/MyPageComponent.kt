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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msg.sms.design.component.button.BlackAddItemButton
import com.msg.sms.design.component.header.TitleHeader
import com.msg.sms.design.component.spacer.SmsSpacer
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.BackButtonIcon
import com.msg.sms.design.icon.BlackLogoutIcon
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
import com.sms.presentation.main.ui.mypage.state.ExpandableAwardDate
import com.sms.presentation.main.ui.mypage.state.ExpandableProjectData
import com.sms.presentation.main.ui.mypage.state.ProjectTechStack

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPageComponent(
    setMajor: String,
    setWantWorkForm: String,
    setMilitary: String,
    selectedTechList: List<String>,
    selectedTechListOnProject: List<ProjectTechStack>,
    onClickMilitaryOpenButton: () -> Unit,
    onClickOpenWorkForm: () -> Unit,
    onClickTopLeftButton: () -> Unit,
    onClickTopRightButton: () -> Unit,
    onClickMajorButton: () -> Unit,
    onRemoveDetailStack: (value: String) -> Unit,
    onRemoveProjectDetailStack: (index: Int, value: String) -> Unit,
    onProjectSearchBar: (index: Int) -> Unit,
    onMyPageSearchBar: () -> Unit,
) {
    val projects = remember {
        mutableStateListOf(
            ExpandableProjectData(
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
                    RelatedLinksData("GitHub", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                ),
                isExpand = true
            ),
            ExpandableProjectData(
                name = "MOIZA",
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
        )
    }

    val awards = remember {
        mutableStateListOf(
            ExpandableAwardDate(title = "수상 1", organization = "", date = "", isExpand = true),
            ExpandableAwardDate(title = "수상 2", organization = "", date = "", isExpand = true)
        )
    }
    val wantWorkingArea = remember {
        mutableStateListOf(*listOf("광저우", "충칭", "하노이", "도쿄").toTypedArray())
    }
    val bitmapPreviews = remember {
        mutableStateListOf(*projects.map { listOf<Bitmap>() }.toTypedArray())
    }
    val foreignLanguages = remember {
        mutableStateListOf(*listOf(Pair("한국어", "원어민"), Pair("토익", "990")).toTypedArray())
    }
    val certifications = remember {
        mutableStateListOf(*listOf("정보처리 산업기사").toTypedArray())
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
                TopBarComponent(
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
                    setMajor = setMajor,
                    selectedTechList = selectedTechList,
                    onClickMajorComponent = onClickMajorButton,
                    onClickSearchBar = onMyPageSearchBar,
                    onRemoveDetailStack = onRemoveDetailStack
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "학교생활 *")
            }
            item {
                SchoolLifeSection(score = 800)
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "근무 조건 *")
            }
            item {
                WorkConditionSection(
                    wantWorkingAreas = wantWorkingArea,
                    wantPay = "2000",
                    wantWorkForm = setWantWorkForm,
                    onClickOpenButton = onClickOpenWorkForm,
                    onValueChange = { index, item ->
                        wantWorkingArea[index] = item
                    },
                    onClickAddButton = { wantWorkingArea.add("") }) {
                    wantWorkingArea.remove(it)
                }
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "병역 *")
            }
            item {
                MilitaryServiceSection(
                    setMilitary = setMilitary,
                    onClickMilitaryOpenButton = onClickMilitaryOpenButton
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "자격증")
            }
            item {
                CertificationsSection(
                    certifications = certifications,
                    onValueChange = { index, value -> certifications[index] = value },
                    onClickRemoveButton = { certifications.removeAt(index = it) },
                    onClickAddButton = { certifications.add("") }
                )
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "외국어")
            }
            item {
                ForeignLanguagesSection(
                    foreignLanguages = foreignLanguages,
                    onValueChangeForeignName = { index, value ->
                        foreignLanguages[index] = foreignLanguages[index].copy(first = value)
                    },
                    onValueChangeForeignValue = { index, value ->
                        foreignLanguages[index] = foreignLanguages[index].copy(second = value)
                    },
                    onClickRemoveButton = { foreignLanguages.removeAt(it) }
                ) {
                    foreignLanguages.add(Pair("", ""))
                }
                SmsSpacer()
            }
            projects.forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it.name,
                        isExpandable = true,
                        isRemovable = true,
                        isExpand = it.isExpand,
                        onClickToggleButton = {
                            projects[index] =
                                projects[index].copy(isExpand = !it.isExpand)
                        },
                        onClickRemoveButton = {
                            bitmapPreviews.removeAt(index)
                            projects.removeAt(index)
                        })
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = projects[index].isExpand,
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        val itemData = projects[index]
                        ProjectsSection(
                            data = itemData,
                            techStacks = selectedTechListOnProject[index],
                            onNameValueChange = {
                                projects[index] = itemData.copy(name = it)
                            },
                            onKeyTaskValueChange = {
                                projects[index] = itemData.copy(keyTask = it)
                            },
                            onLinkNameChanged = { itemIndex, value ->
                                val relatedLink = itemData.relatedLinks.toMutableList()
                                relatedLink.set(
                                    index = itemIndex,
                                    element = itemData.relatedLinks[itemIndex].copy(name = value)
                                )
                                projects[index] = itemData.copy(relatedLinks = relatedLink)
                            },
                            onLinkChanged = { itemIndex, value ->
                                val relatedLink = itemData.relatedLinks.toMutableList()
                                relatedLink.set(
                                    index = itemIndex,
                                    element = itemData.relatedLinks[itemIndex].copy(link = value)
                                )
                                projects[index] = itemData.copy(relatedLinks = relatedLink)
                            },
                            onRemoveProjectImage = {
                                projects[index] = itemData.copy(projectImage = it)
                            },
                            onRemoveProjectDetailStack = { onRemoveProjectDetailStack(index, it) },
                            onAddBitmap = { bitmapPreviews[index] = bitmapPreviews[index] + it },
                            onAddLink = {
                                val relatedLink = itemData.relatedLinks.toMutableList()
                                relatedLink.add(RelatedLinksData(name = "", link = ""))
                                projects[index] = itemData.copy(relatedLinks = relatedLink)
                            },
                            onRemoveBitmapButton = {
                                bitmapPreviews[index] =
                                    bitmapPreviews[index].filterIndexed { index, _ -> it != index }
                            },
                            enteredPreviews = bitmapPreviews[index],
                            onRemoveRelatedLink = {
                                projects[index] =
                                    itemData.copy(relatedLinks = itemData.relatedLinks.filterIndexed { index, _ -> index != it })
                            },
                            onClickSearchBar = { onProjectSearchBar(index) }
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
                        bitmapPreviews.add(listOf())
                        projects.add(
                            ExpandableProjectData(
                                name = "프로젝트 ${projects.size + 1}",
                                activityDuration = "",
                                projectImage = listOf(),
                                icon = "",
                                keyTask = "",
                                relatedLinks = listOf(),
                                isExpand = true
                            )
                        )
                    }
                }
            }
            awards.forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it.title,
                        isExpandable = true,
                        isRemovable = true,
                        isExpand = it.isExpand,
                        onClickRemoveButton = {},
                        onClickToggleButton = {
                            awards[index] = awards[index].copy(isExpand = !it.isExpand)
                        })
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = awards[index].isExpand,
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        AwardSection(
                            awardData = it,
                            onNameValueChange = {
                                awards[index] = awards[index].copy(title = it)
                            },
                            onTypeValueChange = {
                                awards[index] = awards[index].copy(organization = it)
                            },
                            onDateValueChange = {
                                awards[index] = awards[index].copy(date = it)
                            },
                            onClickCalendar = { /*(Todo): kimhs - 넘버핔커 열어줘요*/ }
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
                        awards.add(
                            ExpandableAwardDate(
                                title = "수상 ${awards.size + 1}",
                                organization = "",
                                date = "",
                                isExpand = true
                            )
                        )
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
            visibility = true
        ) {
            // TODO(LeeHyeonbin): 수정된 것들 저장하는 기능 만들기
        }
    }
}

@Preview
@Composable
private fun MyPageComponentPre() {
    MyPageComponent(
        setMajor = "Android",
        setWantWorkForm = "정규직",
        setMilitary = "병특 희망",
        selectedTechList = listOf("Android", ""),
        onClickMilitaryOpenButton = {},
        onClickTopLeftButton = {},
        onClickTopRightButton = {},
        onClickMajorButton = {},
        onClickOpenWorkForm = {},
        onMyPageSearchBar = {},
        onProjectSearchBar = {},
        onRemoveDetailStack = {},
        onRemoveProjectDetailStack = { _, _ -> },
        selectedTechListOnProject = listOf()
    )
}