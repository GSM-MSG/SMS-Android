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
import com.sms.presentation.main.ui.detail.data.AwardNameData
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.ProjectNameData
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPageComponent(
    setMajor: String,
    setWantWorkForm: String,
    setMilitary: String,
    onClickMilitaryOpenButton: () -> Unit,
    onClickOpenWorkForm: () -> Unit,
    onClickTopLeftButton: () -> Unit,
    onClickTopRightButton: () -> Unit,
    onClickMajorButton: () -> Unit,
) {
    val projects = remember {
        mutableStateListOf(
            ProjectData(
                name = "SMS",
                activityDuration = "2023 ~",
                projectImage = listOf(
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
                ),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStack = listOf("Github", "Git", "Kotlin", "Android Studio"),
                keyTask = "모이자 ㅋㅋ",
                relatedLinks = listOf(
                    RelatedLinksData("Youtube", "https://dolmc.com"),
                    RelatedLinksData("GitHujb", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                )
            ),
            ProjectData(
                name = "MOIZA",
                activityDuration = "2023 ~",
                projectImage = listOf(
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                    "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4"
                ),
                icon = "https://avatars.githubusercontent.com/u/82383983?s=400&u=776e1d000088224cbabf4dec2bdea03071aaaef2&v=4",
                techStack = listOf("Github", "Git", "Kotlin", "Android Studio"),
                keyTask = "모이자 ㅋㅋ",
                relatedLinks = listOf(
                    RelatedLinksData("Youtube", "https://dolmc.com"),
                    RelatedLinksData("GitHujb", "https://youyu.com"),
                    RelatedLinksData("X", "https://asdgasgw.com")
                )
            ),
        )
    }
    val expandProjects = remember {
        mutableStateListOf(*projects.map { ProjectNameData(projectName = it.name, isExpand = true) }
            .toTypedArray())
    }
    val awards = remember {
        mutableStateListOf(AwardNameData("수상 1", true), AwardNameData("수상 2", true))
    }
    val wantWorkingArea = remember {
        mutableStateListOf(*listOf("광저우", "충칭", "하노이", "도쿄").toTypedArray())
    }
    val bitmapPreviews = remember {
        mutableStateListOf(*projects.map { listOf<Bitmap>() }.toTypedArray())
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
                ProfileSection(setMajor = setMajor, onClickMajorComponent = onClickMajorButton)
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
                CertificationsSection()
                SmsSpacer()
            }
            stickyHeader {
                TitleHeader(titleText = "외국어")
            }
            item {
                ForeignLanguagesSection()
                SmsSpacer()
            }
            expandProjects.forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it.projectName,
                        isExpandable = true,
                        isRemovable = true,
                        isExpand = it.isExpand,
                        onClickToggleButton = {
                            expandProjects[index] =
                                expandProjects[index].copy(isExpand = !it.isExpand)
                        },
                        onClickRemoveButton = {})
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = expandProjects[index].isExpand,
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        val itemData = projects[index]
                        ProjectsSection(
                            data = itemData,
                            onNameValueChange = {
                                expandProjects[index] = expandProjects[index].copy(projectName = it)
                                projects[index] = itemData.copy(name = it)
                            },
                            onRemoveProjectImage = {
                                projects[index] = itemData.copy(projectImage = it)
                            },
                            onAddBitmap = { bitmapPreviews[index] = bitmapPreviews[index] + it },
                            onRemoveBitmapButton = {
                                bitmapPreviews[index] =
                                    bitmapPreviews[index].filterIndexed { index, _ -> it != index }
                            },
                            enteredPreviews = bitmapPreviews[index],
                            onRemoveTechStack = {
                                projects[index] =
                                    itemData.copy(techStack = itemData.techStack.minus(it))
                            }
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

                    }
                }
            }
            awards.forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it.awardName,
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
                        AwardSection(awardData = it.awardName)
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
        onClickMilitaryOpenButton = {},
        onClickTopLeftButton = {},
        onClickTopRightButton = {},
        onClickMajorButton = {},
        onClickOpenWorkForm = {},
    )
}