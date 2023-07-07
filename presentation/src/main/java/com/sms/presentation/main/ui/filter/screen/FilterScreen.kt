package com.sms.presentation.main.ui.filter.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msg.sms.design.component.topbar.TopBarComponent
import com.msg.sms.design.icon.DeleteButtonIcon
import com.msg.sms.design.theme.SMSTheme
import com.sms.presentation.main.ui.filter.component.FilterSelectorComponent
import com.sms.presentation.main.viewmodel.StudentListViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: StudentListViewModel,
    lifecycleScope: CoroutineScope,
    role: String
) {
    val sliderValues = remember {
        mutableStateOf(0f..990f)
    }

    SMSTheme { colors, typography ->
        Column(
            Modifier
                .fillMaxSize()
                .background(colors.WHITE)
        ) {
            TopBarComponent(text = "필터", leftIcon = {
                Text(
                    text = "초기화",
                    style = typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = colors.BLACK
                )
            }, rightIcon = { DeleteButtonIcon() }, onClickLeftButton = {
                viewModel.resetFilter()
            }, onClickRightButton = {
                navController.popBackStack()
            })
            Divider(thickness = 16.dp, color = colors.N10)
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (role != "") {
                    item {
                        FilterSelectorComponent(
                            title = "학년",
                            itemList = viewModel.gradeList,
                            selectedList = viewModel.selectedGradeList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedGradeList.add(text)
                            else viewModel.selectedGradeList.remove(text)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    item {
                        FilterSelectorComponent(
                            title = "반",
                            itemList = viewModel.classList,
                            selectedList = viewModel.selectedClassList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedClassList.add(text)
                            else viewModel.selectedClassList.remove(text)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    item {
                        FilterSelectorComponent(
                            title = "학과",
                            itemList = viewModel.departmentList,
                            selectedList = viewModel.selectedDepartmentList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedDepartmentList.add(text)
                            else viewModel.selectedDepartmentList.remove(text)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
                item {
                    FilterSelectorComponent(
                        title = "분야",
                        itemList = viewModel.majorList,
                        selectedList = viewModel.selectedMajorList
                    ) { checked, text ->
                        if (!checked) viewModel.selectedMajorList.add(text)
                        else viewModel.selectedMajorList.remove(text)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
                if (role == "ROLE_TEACHER") {
                    item {
                        FilterSelectorComponent(
                            title = "희망 고용 형태",
                            itemList = viewModel.typeOfEmploymentList,
                            selectedList = viewModel.selectedTypeOfEmploymentList
                        ) { checked, text ->
                            if (!checked) viewModel.selectedTypeOfEmploymentList.add(text)
                            else viewModel.selectedTypeOfEmploymentList.remove(text)
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "인증제 점수",
                            style = typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = colors.BLACK
                        )
                        Spacer(modifier = Modifier.height(23.dp))
                        RangeSlider(value = sliderValues.value,
                            valueRange = 0f..990f,
                            onValueChange = {
                                sliderValues.value = it
                            },
                            colors = SliderDefaults.colors(
                                activeTrackColor = colors.P2,
                                inactiveTrackColor = colors.N10,
                                thumbColor = colors.P2
                            ),
                            startThumb = {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(colors.WHITE)
                                        .border(2.dp, colors.P2, CircleShape)
                                )
                            },
                            endThumb = {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(colors.WHITE)
                                        .border(2.dp, colors.P2, CircleShape)
                                )
                            },
                            track = { sliderPositions ->
                                val inactiveTrackColor = colors.N10
                                val activeTrackColor = colors.P2
                                val inactiveTickColor = colors.N10
                                val activeTickColor = colors.P2
                                Canvas(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(2.dp)
                                ) {
                                    val isRtl = layoutDirection == LayoutDirection.Rtl
                                    val sliderLeft = Offset(0f, center.y)
                                    val sliderRight = Offset(size.width, center.y)
                                    val sliderStart = if (isRtl) sliderRight else sliderLeft
                                    val sliderEnd = if (isRtl) sliderLeft else sliderRight
                                    val tickSize = 2.dp.toPx()
                                    val trackStrokeWidth = 2.dp.toPx()
                                    drawLine(
                                        inactiveTrackColor,
                                        sliderStart,
                                        sliderEnd,
                                        trackStrokeWidth,
                                        StrokeCap.Round
                                    )
                                    val sliderValueEnd = Offset(
                                        sliderStart.x +
                                                (sliderEnd.x - sliderStart.x) * sliderPositions.activeRange.endInclusive,
                                        center.y
                                    )

                                    val sliderValueStart = Offset(
                                        sliderStart.x +
                                                (sliderEnd.x - sliderStart.x) * sliderPositions.activeRange.start,
                                        center.y
                                    )

                                    drawLine(
                                        activeTrackColor,
                                        sliderValueStart,
                                        sliderValueEnd,
                                        trackStrokeWidth,
                                        StrokeCap.Round
                                    )
                                    sliderPositions.tickFractions.groupBy {
                                        it > sliderPositions.activeRange.endInclusive ||
                                                it < sliderPositions.activeRange.start
                                    }.forEach { (outsideFraction, list) ->
                                        drawPoints(
                                            list.map {
                                                Offset(lerp(sliderStart, sliderEnd, it).x, center.y)
                                            },
                                            PointMode.Points,
                                            (if (outsideFraction) inactiveTickColor else activeTickColor),
                                            tickSize,
                                            StrokeCap.Round
                                        )
                                    }
                                }
                            })
                        Spacer(modifier = Modifier.height(23.dp))
                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(value = sliderValues.value.start.toInt().toString(),
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .clip(RoundedCornerShape(10.dp))
                                    .width(80.dp)
                                    .height(50.dp)
                                    .background(colors.N10),
                                colors = OutlinedTextFieldDefaults.colors(
                                    disabledContainerColor = colors.N10,
                                    focusedContainerColor = colors.N10,
                                    disabledBorderColor = Color.Transparent,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    cursorColor = colors.P2
                                ),
                                textStyle = typography.body1,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                onValueChange = {
                                    if (it.isNotBlank()) {
                                        if (it.toInt() in 1..sliderValues.value.endInclusive.toInt()) sliderValues.value =
                                            it.toInt().toFloat()..sliderValues.value.endInclusive
                                    } else {
                                        sliderValues.value = 0f..sliderValues.value.endInclusive
                                    }
                                    Log.d("dd", it)
                                })
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(colors.N10)
                                    .widthIn(min = 80.dp)
                            ) {
                                Text(
                                    text = sliderValues.value.endInclusive.toInt().toString(),
                                    style = typography.body1,
                                    fontWeight = FontWeight.Normal,
                                    color = colors.N50,
                                    modifier = Modifier.padding(
                                        vertical = 13.5.dp, horizontal = 12.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}