package com.sms.presentation.main.ui.main

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msg.sms.design.component.SmsDialog
import com.sms.presentation.main.ui.base.BaseActivity
import com.sms.presentation.main.ui.detail_stack_search.DetailStackSearchScreen
import com.sms.presentation.main.ui.filter.screen.FilterScreen
import com.sms.presentation.main.ui.login.LoginActivity
import com.sms.presentation.main.ui.main.screen.MainScreen
import com.sms.presentation.main.ui.mypage.MyPageScreen
import com.sms.presentation.main.ui.mypage.state.ProjectTechStack
import com.sms.presentation.main.ui.util.createCurrentTime
import com.sms.presentation.main.viewmodel.AuthViewModel
import com.sms.presentation.main.viewmodel.FillOutViewModel
import com.sms.presentation.main.viewmodel.MyProfileViewModel
import com.sms.presentation.main.viewmodel.SearchDetailStackViewModel
import com.sms.presentation.main.viewmodel.StudentListViewModel
import com.sms.presentation.main.viewmodel.util.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private enum class MainPage(val value: String) {
    Main("Main"),
    Filter("Filter"),
    Search("Search"),
    MyPage("MyPage")
}

private enum class SelectedTechStack {
    MyPage, Project, Filter
}

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val fillOutViewModel by viewModels<FillOutViewModel>()
    private val searchDetailStackViewModel by viewModels<SearchDetailStackViewModel>()
    private val myProfileViewModel by viewModels<MyProfileViewModel>()

    private val searchDetailStack = mutableStateOf(listOf<String>())

    override fun init() {
        observeEvent()
        authViewModel.getRoleInfo()
        fillOutViewModel.getMajorList()
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            searchDetailStackViewModel.searchResultEvent.collect {
                if (it is Event.Success) {
                    searchDetailStack.value = it.data!!.techStack
                }
            }
        }
        lifecycleScope.launch {
            studentListViewModel.logoutResponse.collect {
                if (it is Event.Success) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            studentListViewModel.withdrawalResponse.collect {
                if (it is Event.Success) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            authViewModel.getRoleResponse.collect { response ->
                if (response is Event.Success) {
                    setContent {
                        val navController = rememberNavController()
                        val filterTechStack = remember {
                            mutableStateListOf(*studentListViewModel.filterDetailStackList.toTypedArray())
                        }
                        val selectedTechStack = remember {
                            mutableStateOf(SelectedTechStack.Filter)
                        }
                        val projectIndex = remember {
                            mutableStateOf(0)
                        }
                        NavHost(
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            composable(MainPage.Main.value) {
                                MainScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    myProfileVIewModel = viewModel(LocalContext.current as MainActivity),
                                    lifecycleScope = lifecycleScope,
                                    role = response.data!!,
                                    onFilterClick = { navController.navigate(MainPage.Filter.value) },
                                    onProfileClick = { role ->
                                        if (role == "ROLE_STUDENT") {
                                            myProfileViewModel.getMyProfile()
                                            navController.navigate(MainPage.MyPage.value)
                                        } else {
                                            authViewModel.deleteToken()
                                            this@MainActivity.startActivity(
                                                Intent(
                                                    this@MainActivity,
                                                    LoginActivity::class.java
                                                )
                                            )
                                            this@MainActivity.finish()
                                        }
                                    }
                                ) {
                                    controlTheStackWhenBackPressed()
                                }
                            }
                            composable(MainPage.Filter.name) {
                                FilterScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    role = response.data!!,
                                    onFilteringTechStackValueChanged = { list ->
                                        studentListViewModel.setSelectedDetailStackList(list)
                                    },
                                    onBackPressed = {
                                        navController.navigate(MainPage.Main.value) {
                                            popUpTo(route = MainPage.Main.value) {
                                                inclusive = false
                                            }
                                        }
                                    },
                                    onChangeToMainPage = {
                                        studentListViewModel.setFilterGradeList(studentListViewModel.selectedGradeList)
                                        studentListViewModel.setFilterClassList(studentListViewModel.selectedClassList)
                                        studentListViewModel.setFilterDepartmentList(studentListViewModel.selectedDepartmentList)
                                        studentListViewModel.setFilterMajorList(studentListViewModel.selectedMajorList)
                                        studentListViewModel.setFilterTypeOfEmploymentList(studentListViewModel.selectedTypeOfEmploymentList)
                                        studentListViewModel.setFilterGsmScoreSliderValues(studentListViewModel.selectedGsmScoreSliderValues.value)
                                        studentListViewModel.setFilterDesiredAnnualSalarySliderValues(studentListViewModel.selectedDesiredAnnualSalarySliderValues.value)
                                        studentListViewModel.setFilterSchoolNumberAscendingValue(studentListViewModel.selectedSchoolNumberAscendingOrder.value)
                                        studentListViewModel.setFilterGsmScoreAscendingValue(studentListViewModel.selectedGsmScoreAscendingOrder.value)
                                        studentListViewModel.setFilterDesiredAnnualSalaryAscendingValue(studentListViewModel.selectedDesiredAnnualSalaryAscendingOrder.value)
                                        studentListViewModel.setFilterDetailStackList(studentListViewModel.selectedDetailStack)

                                        navController.navigate(MainPage.Main.value)
                                    },
                                    onChangeToSearchPage = {
                                        navController.navigate(MainPage.Search.value)
                                    },
                                    onRightButtonClick = {
                                        studentListViewModel.setSelectedDetailStackList(studentListViewModel.filterDetailStackList)
                                        navController.navigate(MainPage.Main.value) {
                                            popUpTo(route = MainPage.Main.value) {
                                                inclusive = false
                                            }
                                        }
                                    },
                                    onLeftButtonClick = {
                                        filterTechStack.clear()
                                    },
                                    //Selector
                                    gradeList = studentListViewModel.gradeList.map { it.value },
                                    classList = studentListViewModel.classList.map { it.value },
                                    departmentList = studentListViewModel.departmentList.map { it.value },
                                    majorList = studentListViewModel.majorList,
                                    typeOfEmploymentList = studentListViewModel.typeOfEmploymentList.map { it.value },
                                    selectedGradeList = studentListViewModel.filterGradeList,
                                    selectedClassList = studentListViewModel.filterClassList,
                                    selectedDepartmentList = studentListViewModel.filterDepartmentList,
                                    selectedMajorList = studentListViewModel.filterMajorList,
                                    selectedTypeOfEmploymentList = studentListViewModel.filterTypeOfEmploymentList,
                                    onGradeListValueChanged = { gradeList ->
                                        studentListViewModel.setSelectedGradeList(gradeList)
                                    },
                                    onClassListValueChanged = { classList ->
                                        studentListViewModel.setSelectedClassList(classList)
                                    },
                                    onDepartmentListValueChanged = { departmentList ->
                                        studentListViewModel.setSelectedDepartmentList(departmentList)
                                    },
                                    onMajorListValueChanged = { majorList ->
                                        studentListViewModel.setSelectedMajorList(majorList)
                                    },
                                    onTypeOfEmploymentListValueChanged = { typeOfEmploymentList ->
                                        studentListViewModel.setSelectedTypeOfEmploymentList(typeOfEmploymentList)
                                    },
                                    //Slider
                                    selectedGsmScoreSliderValue = studentListViewModel.filterGsmScoreSliderValues.value,
                                    selectedDesiredAnnualSalarySliderValue = studentListViewModel.filterDesiredAnnualSalarySliderValues.value,
                                    onGsmScoreSliderValueChanged = { gsmScoreSliderValue ->
                                        studentListViewModel.setSelectedGsmScoreSliderValues(gsmScoreSliderValue)
                                    },
                                    onDesiredAnnualSalarySliderValueChanged = { desiredAnnualSalarySliderValue ->
                                        studentListViewModel.setSelectedDesiredAnnualSalarySliderValues(desiredAnnualSalarySliderValue)
                                    },
                                    //SelectionControl
                                    selectedSchoolNumberAscendingValue = studentListViewModel.filterSchoolNumberAscendingOrder.value,
                                    selectedGsmScoreAscendingValue = studentListViewModel.filterGsmScoreAscendingOrder.value,
                                    selectedDesiredAnnualSalaryAscendingValue = studentListViewModel.filterDesiredAnnualSalaryAscendingOrder.value,
                                    onSchoolNumberAscendingValueChanged = { schoolNumberAscendingValue ->
                                        studentListViewModel.setSelectedSchoolNumberAscendingValue(schoolNumberAscendingValue)
                                    },
                                    onGsmScoreAscendingValueChanged = { gsmScoreAscendingValue ->
                                        studentListViewModel.setSelectedGsmScoreAscendingValue(gsmScoreAscendingValue)
                                    },
                                    onDesiredAnnualSalaryAscendingValueChanged = { desiredAnnualSalaryAscendingValue ->
                                        studentListViewModel.setSelectedDesiredAnnualSalaryAscendingValue(desiredAnnualSalaryAscendingValue)
                                    },
                                    //DetailStack
                                    detailStacks = studentListViewModel.selectedDetailStack,
                                )
                            }
                            composable(MainPage.Search.name) {
                                setSoftInputMode("RESIZE")
                                DetailStackSearchScreen(
                                    navController = navController,
                                    onSearchStack = {
                                        searchDetailStackViewModel.searchDetailStack(it)
                                    },
                                    selectedStack = when (selectedTechStack.value) {
                                        SelectedTechStack.MyPage -> myProfileViewModel.techStacks.value
                                        SelectedTechStack.Filter -> studentListViewModel.selectedDetailStack
                                        SelectedTechStack.Project -> myProfileViewModel.projects.value[projectIndex.value].techStacks
                                    },
                                    detailStack = searchDetailStack.value,
                                ) { list ->
                                    when (selectedTechStack.value) {
                                        SelectedTechStack.MyPage -> {
                                            val techStacks =
                                                myProfileViewModel.techStacks.value.toMutableList()
                                            techStacks.removeAll(techStacks.filter {
                                                !list.contains(it)
                                            })
                                            techStacks.addAll(list.filter {
                                                !techStacks.contains(it)
                                            })
                                            myProfileViewModel.onChangeTechStackList(techStacks = techStacks)
                                        }

                                        SelectedTechStack.Filter -> {
                                            studentListViewModel.setSelectedDetailStackList(list)
                                        }

                                        SelectedTechStack.Project -> {
                                            myProfileViewModel.onChangeProjectTechStack(
                                                projectIndex = projectIndex.value,
                                                techStacks = list
                                            )
                                        }
                                    }
                                    navController.popBackStack()
                                }
                            }
                            composable(MainPage.MyPage.value) {
                                MyPageScreen(
                                    viewModel = viewModel(LocalContext.current as MainActivity),
                                    myProfileData = myProfileViewModel.myProfileData.value,
                                    navController = navController,
                                    bitmapPreviews = myProfileViewModel.bitmapPreviews.value,
                                    projects = myProfileViewModel.projects.value,
                                    majorList = studentListViewModel.majorList,
                                    awards = myProfileViewModel.awards.value,
                                    selectedTechList = myProfileViewModel.techStacks.value,
                                    selectedTechListOnProject = myProfileViewModel.projects.value.map {
                                        ProjectTechStack(
                                            it.techStacks
                                        )
                                    },
                                    onAddAward = { myProfileViewModel.onAddAward() },
                                    onAddProject = { myProfileViewModel.onAddProject() },
                                    onWithdrawal = {
                                        studentListViewModel.withdrawal()
                                        authViewModel.deleteToken()
                                    },
                                    onLogout = {
                                        studentListViewModel.logout()
                                        authViewModel.deleteToken()
                                    },
                                    onClickSearchBar = {
                                        selectedTechStack.value = SelectedTechStack.MyPage
                                        navController.navigate(MainPage.Search.value)
                                    },
                                    onClickBackButton = {
                                        navController.popBackStack()
                                    },
                                    onClickProjectSearchBar = {
                                        projectIndex.value = it
                                        selectedTechStack.value = SelectedTechStack.Project
                                        navController.navigate(MainPage.Search.value)
                                    },
                                    onRemoveDetailStack = {
                                        myProfileViewModel.removeTechStack(it)
                                    },
                                    onRemoveProject = {
                                        myProfileViewModel.removeProject(index = it)
                                    },
                                    onRemoveProjectDetailStack = { index: Int, value: String ->
                                        myProfileViewModel.removeProjectTechStack(
                                            projectIndex = index,
                                            techStack = value
                                        )
                                    },
                                    onRemoveAward = { myProfileViewModel.removeAward(awardIndex = it) },
                                    onProjectValueChange = { index, data ->
                                        myProfileViewModel.onChangeProjectValue(
                                            index = index,
                                            value = data
                                        )
                                    },
                                    onAwardValueChange = { index, award ->
                                        myProfileViewModel.onChangeAwardValue(
                                            awardIndex = index,
                                            award = award
                                        )
                                    },
                                    onAddBitmapPreview = { indexOfProject, previews ->
                                        myProfileViewModel.addBitmapPreview(
                                            indexOfProject = indexOfProject,
                                            previews = previews
                                        )
                                    },
                                    onRemoveBitmapPreview = { indexOfProject, indexOfPreview ->
                                        myProfileViewModel.removeBitmapPreview(
                                            indexOfProject = indexOfProject,
                                            indexOfPreview = indexOfPreview
                                        )
                                    },
                                    isExpandedProject = myProfileViewModel.isExpandedProject.value,
                                    isExpandedAward = myProfileViewModel.isExpandedAward.value,
                                    onExpandProjectClick = {
                                        myProfileViewModel.onChangeProjectExpand(index = it)
                                    },
                                    onExpandAwardClick = {
                                        myProfileViewModel.onChangeAwardExpand(index = it)
                                    },
                                    onProfileValueChange = {
                                        myProfileViewModel.onProfileValueChange(myProfile = it)
                                    },
                                    onSaveButtonClick = {
                                        myProfileViewModel.onChangeProfileChange(myProfileViewModel.myProfileData.value.profileImageBitmap)
                                        myProfileViewModel.onChangeProjectIcon(myProfileViewModel.bitmapIcons.value)
                                        myProfileViewModel.onChangeProjectPreviews(
                                            myProfileViewModel.bitmapPreviews.value
                                        )
                                    },
                                    setBitmap = { index, element ->
                                        myProfileViewModel.onChangeProjectIcon(
                                            index = index,
                                            value = element
                                        )
                                    },
                                    bitmapIcons = myProfileViewModel.bitmapIcons.value,
                                    onChangeProjectDateValue = { index, value, isStart ->
                                        val activityDuration =
                                            myProfileViewModel.projects.value[index].activityDuration
                                        myProfileViewModel.onChangeProjectValue(
                                            index = index,
                                            value = myProfileViewModel.projects.value[index].copy(
                                                activityDuration = if (isStart) activityDuration.copy(
                                                    start = value
                                                ) else activityDuration.copy(end = value)
                                            )
                                        )
                                    },
                                    onChangeAwardDateValue = { index, value ->
                                        myProfileViewModel.onChangeAwardValue(
                                            awardIndex = index,
                                            award = myProfileViewModel.awards.value[index].copy(date = value)
                                        )
                                    },
                                    onChangeProgressState = {
                                        val activityDuration =
                                            myProfileViewModel.projects.value[it].activityDuration
                                        val isProgress = activityDuration.end == null
                                        myProfileViewModel.onChangeProjectValue(
                                            index = it,
                                            value = myProfileViewModel.projects.value[it].copy(
                                                activityDuration = if (isProgress) {
                                                    activityDuration.copy(end = createCurrentTime("yyyy.MM"))
                                                } else {
                                                    activityDuration.copy(end = null)
                                                }
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                } else {
                    setContent {
                        SmsDialog(
                            title = "에러",
                            msg = "알 수 없는 오류 발생",
                            outLineButtonText = "취소",
                            importantButtonText = "확인",
                            outlineButtonOnClick = { finish() },
                            importantButtonOnClick = { finish() }
                        )
                    }
                }
            }
        }
        lifecycleScope.launch {
            fillOutViewModel.getMajorListResponse.collect { response ->
                studentListViewModel.majorList =
                    if (response is Event.Success) {
                        response.data!!.major
                    } else {
                        mutableStateListOf()
                    }
            }
        }
    }
}