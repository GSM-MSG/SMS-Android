package com.sms.presentation.main.ui.mypage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
    onClickOpenWorkForm: () -> Unit,
    onClickTopLeftButton: () -> Unit,
    onClickTopRightButton: () -> Unit,
    onClickMajorButton: () -> Unit,
) {

    val projectExpandList = remember {
        mutableStateListOf(*listOf("프로젝트 1", "프로젝트 2").map { true }.toTypedArray())
    }
    val awardExpandList = remember {
        mutableStateListOf(*listOf("수상 1", "수상 2").map { true }.toTypedArray())
    }
    val wantWorkingArea = remember {
        mutableStateListOf(*listOf("광저우", "충칭", "하노이", "도쿄").toTypedArray())
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
            listOf("프로젝트 1", "프로젝트 2").forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it,
                        isExpandable = true,
                        isRemovable = true,
                        onClickToggleButton = { projectExpandList[index] = it },
                        onClickRemoveButton = {})
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = projectExpandList[index],
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        ProjectsSection(data = it)
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
            listOf("수상 1", "수상 2").forEachIndexed { index, it ->
                stickyHeader {
                    TitleHeader(
                        titleText = it,
                        isExpandable = true,
                        isRemovable = true,
                        onClickRemoveButton = {},
                        onClickToggleButton = { rotateState ->
                            awardExpandList[index] = rotateState
                        })
                }
                item {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = awardExpandList[index],
                        enter = expandVertically(),
                        exit = shrinkVertically(),
                    ) {
                        AwardSection(awardData = it)
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