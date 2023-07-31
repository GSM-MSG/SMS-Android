package com.sms.presentation.main.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.component.profile.ProfileImageComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.sms.presentation.main.ui.main.data.StudentDetailData
import com.sms.presentation.main.ui.util.departmentEnumToString
import com.sms.presentation.main.ui.util.employmentEnumToSting
import com.sms.presentation.main.ui.util.militaryServiceEnumToString
import com.sms.presentation.main.viewmodel.util.downloader.AndroidDownloader
import kotlinx.coroutines.launch

@Composable
fun StudentDetailScreen(
    studentDetailData: StudentDetailData,
    role: String,
    onDismissButtonClick: () -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val imageHeight = remember {
            mutableStateOf(0.dp)
        }
        val localDensity = LocalDensity.current
        ProfileImageComponent(
            profileImage = studentDetailData.profileImg,
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    imageHeight.value = with(localDensity) { layoutCoordinates.size.height.toDp() }
                }
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 24.dp,
                        bottomStart = 24.dp,
                        topEnd = 0.dp,
                        topStart = 0.dp
                    )
                )
                .align(Alignment.TopCenter)
        ) { modifier ->
            Image(
                painter = rememberAsyncImagePainter(
                    model = studentDetailData.profileImg,
                ), contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
        }
        StudentDetailComponent(
            imageHeight = imageHeight.value,
            techStack = studentDetailData.techStacks,
            name = studentDetailData.name.replace("**", "소금"),
            major = studentDetailData.major,
            modifier = Modifier.align(Alignment.TopCenter),
            isNotGuest = role == "ROLE_TEACHER" || role == "ROLE_STUDENT",
            grade = studentDetailData.grade.toString(),
            classNumber = studentDetailData.classNum.toString(),
            schoolNumber = studentDetailData.number.toString(),
            departments = studentDetailData.department.departmentEnumToString(),
            introduce = studentDetailData.introduce,
            isTeacher = role == "ROLE_TEACHER",
            onDreamBookButtonClick = {
                val downloader = AndroidDownloader(
                    context = context,
                    fileName =
                    "${studentDetailData.grade}${studentDetailData.classNum}${if (studentDetailData.number.toString().length == 1) "0" + studentDetailData.number else studentDetailData.number}${studentDetailData.name}의 드림북.hwp"
                )
                downloader.downloadFile(url = studentDetailData.dreamBookFileUrl)
            },
            certificationData = studentDetailData.certificates,
            email = studentDetailData.contactEmail,
            gsmAuthenticationScore = studentDetailData.gsmAuthenticationScore.toString(),
            foreignLanguage = studentDetailData.languageCertificates,
            formOfEmployment = studentDetailData.formOfEmployment.employmentEnumToSting(),
            militaryService = studentDetailData.militaryService.militaryServiceEnumToString(),
            portfolioLink = studentDetailData.portfolioUrl,
            region = studentDetailData.regions,
            salary = studentDetailData.salary.toString(),
            scrollState = scrollState
        )
        IconButton(
            onClick = {
                onDismissButtonClick()
                scope.launch {
                    scrollState.scrollTo(0)
                }
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            DeleteButtonIcon()
        }
    }
}