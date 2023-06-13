package com.sms.presentation.main.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.msg.sms.design.component.profile.ProfileImageComponent
import com.msg.sms.design.icon.DeleteButtonIcon

@Composable
fun StudentDetailScreen(
    onDissmissButtonClick: () -> Unit
) {
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
            profileImage = "",
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
                    model = "",
                    contentScale = ContentScale.Crop
                ), contentDescription = "User Image",
                modifier = modifier
            )
        }
        // Todo(LeeHyeonbin) - 데이터 띄울 때 파라미터로 넘기기
        StudentDetailComponent(
            imageHeight = imageHeight.value,
            techStack = listOf("Kotlin", "Compose"),
            name = "이현빈",
            major = "Android",
            modifier = Modifier.align(Alignment.TopCenter),
            isNotGuest = true,
            grade = "3",
            classNumber = "2",
            schoolNumber = "15",
            departments = "사과",
            introduce = "ljasfd;lsfdlk;asfdloi;jsdf;oijsadfoi;jsadf;jsdfa;lfl;fa;lasfd;l jasfd ; lsdfa;l asf d;l sadf ;l safd;l fads;l sfad;jl af;oij asf;oij fauij fweio;j fewi;o jferai;ojefai;o jeawri;j wfwea rilj f wae iljfaer wi faijl awefj iawef ilj ferw ie iefarijl erfawijlo ;erfi;jlo o ie",
            isTeacher = true,
            onDreamBookButtonClick = { /* TODO(LeeHyeonbin) - 드림북 다운로드하기*/ }
        )
        IconButton(onClick = onDissmissButtonClick, modifier = Modifier.align(Alignment.TopEnd)) {
            DeleteButtonIcon()
        }
    }
}