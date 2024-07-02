package com.sms.presentation.main.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.common.LinkModel
import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.common.ProjectModel
import com.msg.sms.domain.model.student.request.PutChangeProfileRequestModel
import com.msg.sms.domain.model.user.response.ActivityDuration
import com.msg.sms.domain.model.user.response.LanguageCertificateModel
import com.msg.sms.domain.model.user.response.MyProfileModel
import com.msg.sms.domain.usecase.fileupload.ImageUploadUseCase
import com.msg.sms.domain.usecase.student.PutChangedPortfolioPdfUseCase
import com.msg.sms.domain.usecase.student.PutChangedProfileUseCase
import com.msg.sms.domain.usecase.user.GetMyProfileUseCase
import com.sms.presentation.main.ui.detail.data.AwardData
import com.sms.presentation.main.ui.detail.data.ProjectData
import com.sms.presentation.main.ui.detail.data.RelatedLinksData
import com.sms.presentation.main.ui.mypage.state.FormOfEmployment
import com.sms.presentation.main.ui.mypage.state.MilitaryService
import com.sms.presentation.main.ui.mypage.state.MyProfileData
import com.sms.presentation.main.ui.util.createCurrentTime
import com.sms.presentation.main.ui.util.createFileFromBitmap
import com.sms.presentation.main.ui.util.toMultipartBody
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val imageUploadUseCase: ImageUploadUseCase,
    private val putChangedProfileUseCase: PutChangedProfileUseCase,
    private val putChangedPortfolioPdfUseCase: PutChangedPortfolioPdfUseCase,
) : ViewModel() {
    private val _getProfileResponse = MutableStateFlow<Event<MyProfileModel>>(Event.Loading)
    val getProfileResponse = _getProfileResponse.asStateFlow()

    private val _putChangedProfileResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val putChangedProfileResponse = _putChangedProfileResponse.asStateFlow()

    private val _putChangedPortfolioPdfResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val putChangedPortfolioPdfResponse = _putChangedPortfolioPdfResponse.asStateFlow()

    private val _myProfileData = mutableStateOf(
        MyProfileData(
            name = "",
            introduce = "",
            portfolioUrl = null,
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
        )
    )
    val myProfileData: State<MyProfileData> = _myProfileData

    private val _myPortfolioPdfData = mutableStateOf<String?>(null)
    val myPortfolioPdfData: State<String?> = _myPortfolioPdfData

    private val _pdfData = mutableStateOf<Uri?>(null)
    val pdfData: State<Uri?> = _pdfData

    private val _techStacks = mutableStateOf<List<String>>(listOf())
    val techStacks: State<List<String>> = _techStacks

    private val _projects = mutableStateOf<List<ProjectData>>(listOf())
    val projects: State<List<ProjectData>> = _projects

    private val _awards = mutableStateOf<List<AwardData>>(listOf())
    val awards: State<List<AwardData>> = _awards

    private val _isExpandedProject = mutableStateOf<List<Boolean>>(listOf())
    val isExpandedProject: State<List<Boolean>> = _isExpandedProject

    private val _isExpandedAward = mutableStateOf<List<Boolean>>(listOf())
    val isExpandedAward: State<List<Boolean>> = _isExpandedAward

    private val _bitmapPreviews = mutableStateOf<List<List<Bitmap>>>(listOf())
    val bitmapPreviews: State<List<List<Bitmap>>> = _bitmapPreviews

    private val _bitmapIcons = mutableStateOf<List<Bitmap?>>(listOf())
    val bitmapIcons: State<List<Bitmap?>> = _bitmapIcons

    private val _iconChangeCount = mutableStateOf(0)

    private val _previewChangeCount = mutableStateOf(0)

    private val _isProfileChanged = mutableStateOf(false)
    val isProfileChanged: State<Boolean> = _isProfileChanged

    private val _isProjectIconChanged = mutableStateOf(false)
    val isProjectIconChanged: State<Boolean> = _isProjectIconChanged

    private val _isProjectPreviewsChanged = mutableStateOf(false)
    val isProjectPreviewChanged: State<Boolean> = _isProjectPreviewsChanged

    fun removeTechStack(techStack: String) {
        _techStacks.value = _techStacks.value.minus(techStack)
    }

    fun changeProfileState() {
        _putChangedProfileResponse.value = Event.Loading
    }

    fun removeProjectTechStack(projectIndex: Int, techStack: String) {
        _projects.value = _projects.value.mapIndexed { itemIndex: Int, item: ProjectData ->
            if (projectIndex == itemIndex) {
                _projects.value[projectIndex].copy(
                    techStacks = _projects.value[projectIndex].techStacks.minus(
                        techStack
                    )
                )
            } else
                item
        }
    }

    fun onChangeProjectIcon(index: Int, value: Bitmap) {
        val bitmapIconList = _bitmapIcons.value.toMutableList()
        bitmapIconList[index] = value
        _bitmapIcons.value = bitmapIconList
    }

    fun onChangeProfileChange(bitmap: Bitmap?) {
        if (bitmap == null) {
            _isProfileChanged.value = true
        } else {
            changeBitmapToUrl(bitmap, typeOfRequest = 0)
        }
    }

    fun onChangeProjectIcon(bitmaps: List<Bitmap?>) {
        if (bitmaps.filterNotNull().isEmpty()) {
            _isProjectIconChanged.value = true
        } else {
            bitmaps.forEachIndexed { index, bitmap ->
                if (bitmap != null) {
                    changeBitmapToUrl(bitmap, typeOfRequest = 1, projectIndex = index)
                }
            }
        }
    }

    fun onChangeProjectPreviews(bitmaps: List<List<Bitmap?>>) {
        if (bitmaps.sumOf { it.size } != 0) {
            bitmaps.forEachIndexed { projectIndex, projectPreviews ->
                projectPreviews.forEach { bitmap ->
                    if (bitmap != null) {
                        changeBitmapToUrl(bitmap, typeOfRequest = 2, projectIndex = projectIndex)
                    }
                }
            }
        } else {
            _isProjectPreviewsChanged.value = true
        }
    }

    fun onChangeMajorValue(value: String) {
        _myProfileData.value = _myProfileData.value.copy(major = value)
    }

    fun onEnteredMajorValue(value: String) {
        _myProfileData.value = _myProfileData.value.copy(enteredMajor = value)
    }

    fun onChangeProjectValue(index: Int, value: ProjectData) {
        _projects.value =
            _projects.value.mapIndexed { itemIndex, projectData -> if (index == itemIndex) value else projectData }
    }

    fun onChangeProjectExpand(index: Int) {
        _isExpandedProject.value =
            _isExpandedProject.value.mapIndexed { itemIndex, isExpanded -> if (index == itemIndex) !isExpanded else isExpanded }
    }

    fun onChangeAwardExpand(index: Int) {
        _isExpandedAward.value =
            _isExpandedAward.value.mapIndexed { itemIndex, isExpanded -> if (index == itemIndex) !isExpanded else isExpanded }
    }

    fun onChangeTechStackList(techStacks: List<String>) {
        _techStacks.value = techStacks
    }

    fun onChangeProjectTechStack(projectIndex: Int, techStacks: List<String>) {
        val item = _projects.value[projectIndex]
        _projects.value =
            _projects.value.mapIndexed { itemIndex: Int, projectData: ProjectData ->
                if (projectIndex == itemIndex) item.copy(
                    techStacks = techStacks
                ) else projectData
            }
    }

    fun onChangeAwardValue(awardIndex: Int, award: AwardData) {
        val awards = _awards.value.toMutableList()
        awards[awardIndex] = award
        _awards.value = awards
    }

    private fun setProjectData(data: MyProfileModel) {
        if (data.projects.isEmpty()) {
            onAddProject()
            return
        }

        _isExpandedProject.value = data.projects.map { true }
        setBitmapProjectPromotions(data = data)
        setProjectIcon(data = data)
        _projects.value = data.projects.map { project ->
            ProjectData(
                name = project.name,
                activityDuration = project.activityDuration.let { activityDuration ->
                    ActivityDuration(
                        start = activityDuration.start.replace("-", "."),
                        end = activityDuration.end?.replace("-", ".")
                    )
                },
                projectImage = project.previewImages,
                icon = project.icon,
                techStacks = project.techStacks,
                keyTask = project.task,
                relatedLinks = project.links.map { link ->
                    RelatedLinksData(
                        name = link.name,
                        link = link.url
                    )
                },
                description = project.description
            )
        }
    }

    private fun setAwardData(data: MyProfileModel) {
        if (data.prizes.isEmpty()) {
            onAddAward()
            return
        }

        _isExpandedAward.value = data.prizes.map { true }
        _awards.value =
            data.prizes.map {
                AwardData(
                    title = it.name,
                    organization = it.type,
                    date = it.date.replace("-", ".")
                )
            }
    }

    private fun setTechStack(data: MyProfileModel) {
        _techStacks.value = data.techStacks
    }

    private fun setBitmapProjectPromotions(data: MyProfileModel) {
        _bitmapPreviews.value = data.projects.map { listOf() }
    }

    fun addRegion() {
        val regionList = myProfileData.value.regions.toMutableList()
        regionList.add("")
        _myProfileData.value = _myProfileData.value.copy(regions = regionList)
    }

    fun addCertificate() {
        val certificateList = myProfileData.value.certificates.toMutableList()
        certificateList.add("")
        _myProfileData.value = _myProfileData.value.copy(certificates = certificateList)
    }

    fun addForeignLanguage() {
        val foreignLanguageList = myProfileData.value.languageCertificates.toMutableList()
        foreignLanguageList.add(LanguageCertificateModel("", ""))
        _myProfileData.value = _myProfileData.value.copy(languageCertificates = foreignLanguageList)
    }

    fun addBitmapPreview(indexOfProject: Int, previews: List<Bitmap>) {
        val bitmapPreviewList = bitmapPreviews.value.toMutableList()
        _bitmapPreviews.value =
            _bitmapPreviews.value.mapIndexed { index, bitmaps -> if (indexOfProject == index) bitmapPreviewList[indexOfProject] + previews else bitmaps }
    }

    fun removeBitmapPreview(indexOfProject: Int, indexOfPreview: Int) {
        val bitmapPreviewList = bitmapPreviews.value.toMutableList()
        bitmapPreviewList[indexOfProject] =
            _bitmapPreviews.value[indexOfProject].filterIndexed { index, _ -> index != indexOfPreview }
        _bitmapPreviews.value = bitmapPreviewList
    }

    fun removeProject(index: Int) {
        //
        val projectList = projects.value.toMutableList()
        projectList.removeAt(index = index)
        _projects.value = projectList
        //
        val bitmapIcons = bitmapIcons.value.toMutableList()
        bitmapIcons.removeAt(index = index)
        _bitmapIcons.value = bitmapIcons
        //
        val bitmapPreviews = bitmapPreviews.value.toMutableList()
        bitmapPreviews.removeAt(index = index)
        _bitmapPreviews.value = bitmapPreviews
        //
        val expandProjectList = isExpandedProject.value.toMutableList()
        expandProjectList.removeAt(index = index)
        _isExpandedProject.value
    }

    fun removeAward(awardIndex: Int) {
        //
        val awardList = awards.value.toMutableList()
        awardList.removeAt(index = awardIndex)
        _awards.value = awardList
        //
        val expandAwardList = isExpandedAward.value.toMutableList()
        expandAwardList.removeAt(index = awardIndex)
        _isExpandedAward.value = expandAwardList
    }

    private fun setProjectIcon(data: MyProfileModel) {
        _bitmapIcons.value = data.projects.map { null }
    }

    private fun setProfileData(data: MyProfileModel) {
        _myProfileData.value = MyProfileData(
            name = data.name,
            introduce = data.introduce,
            portfolioUrl = data.portfolioUrl,
            grade = data.grade,
            classNum = data.classNum,
            number = data.number,
            department = data.department,
            major = data.major,
            profileImg = data.profileImg,
            contactEmail = data.contactEmail,
            gsmAuthenticationScore = data.gsmAuthenticationScore,
            formOfEmployment = FormOfEmployment.valueOf(data.formOfEmployment),
            regions = data.regions,
            militaryService = MilitaryService.valueOf(data.militaryService),
            salary = data.salary,
            languageCertificates = data.languageCertificates,
            certificates = data.certificates,
            profileImageBitmap = null
        )
        _myPortfolioPdfData.value = data.portfolioFileUrl
        if (data.portfolioFileUrl != null) _pdfData.value = Uri.parse(data.portfolioFileUrl)
    }

    private fun setMyProfileData(data: MyProfileModel) {
        setProfileData(data = data)
        setAwardData(data = data)
        setTechStack(data = data)
        setProjectData(data = data)
    }

    fun onAddProject() {
        val projectExpandList = isExpandedProject.value.toMutableList()
        projectExpandList.add(true)
        _isExpandedProject.value = projectExpandList
        //
        val bitmapProjectPromotionList = bitmapPreviews.value.toMutableList()
        bitmapProjectPromotionList.add(listOf())
        _bitmapPreviews.value = bitmapProjectPromotionList
        //
        val bitmapIconList = bitmapIcons.value.toMutableList()
        bitmapIconList.add(null)
        _bitmapIcons.value = bitmapIconList
        val projects = _projects.value.toMutableList()
        projects.add(
            ProjectData(
                name = "프로젝트 ${_projects.value.size + 1}",
                activityDuration = ActivityDuration(
                    start = createCurrentTime("yyyy.MM"),
                    end = createCurrentTime("yyyy.MM")
                ),
                projectImage = listOf(),
                icon = "",
                techStacks = listOf(),
                keyTask = "",
                relatedLinks = listOf(),
                description = ""
            )
        )
        _projects.value = projects
    }

    fun onAddAward() {
        val awardExpandList = isExpandedAward.value.toMutableList()
        awardExpandList.add(true)
        _isExpandedAward.value = awardExpandList
        val awards = _awards.value.toMutableList()
        awards.add(
            AwardData(
                title = "수상 ${_awards.value.size + 1}",
                organization = "",
                date = createCurrentTime("yyyy.MM")
            )
        )
        _awards.value = awards
    }

    @SuppressLint("SimpleDateFormat")
    // typeOfRequest: 0 -> profile image, 1 -> project icon, 2 -> project image
    fun changeBitmapToUrl(
        bitmap: Bitmap,
        typeOfRequest: Int,
        projectIndex: Int = 0,
    ) = viewModelScope.launch {
        imageUploadUseCase(
            file = bitmap.createFileFromBitmap(
                name = "file",
                fileName = "SMS-${SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())}.jpg"
            )
        ).onSuccess {
            it.catch { remoteError ->
            }.collect { response ->
                when (typeOfRequest) {
                    0 -> updateProfileImage(url = response.fileUrl)
                    1 -> appendProjectIcon(projectIndex = projectIndex, url = response.fileUrl)
                    2 -> appendProjectPromotion(projectIndex = projectIndex, url = response.fileUrl)
                }
            }
        }.onFailure {
        }
    }

    private fun updateProfileImage(url: String) {
        _myProfileData.value = _myProfileData.value.copy(profileImg = url)
        _isProfileChanged.value = true
    }

    private fun appendProjectIcon(projectIndex: Int, url: String) {
        if (_bitmapIcons.value.isEmpty()) {
            _isProjectIconChanged.value = true
        } else {
            val changedData = _projects.value[projectIndex].copy(icon = url)
            _projects.value =
                _projects.value.mapIndexed { index, projectData -> if (index == projectIndex) changedData else projectData }
            _iconChangeCount.value += 1
            if (bitmapIcons.value.size == _iconChangeCount.value) {
                _isProjectIconChanged.value = true
            }
        }
    }

    private fun appendProjectPromotion(projectIndex: Int, url: String) {
        if (bitmapPreviews.value.sumOf { it.size } == 0) {
            _isProjectPreviewsChanged.value = true
        } else {
            val projectPromotions = _projects.value[projectIndex].projectImage.toMutableList()
            projectPromotions.add(url)
            val changedData = _projects.value[projectIndex].copy(projectImage = projectPromotions)
            _projects.value =
                _projects.value.mapIndexed { index, projectData -> if (index == projectIndex) changedData else projectData }
            _previewChangeCount.value += 1
            if (bitmapPreviews.value.sumOf { it.size } == _previewChangeCount.value) {
                _isProjectPreviewsChanged.value = true
            }
        }
    }

    fun getMyProfile() = viewModelScope.launch {
        getMyProfileUseCase().onSuccess {
            it.catch { remoteError ->
                _getProfileResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _getProfileResponse.value = Event.Success(data = response)
                setMyProfileData(data = response)
            }
        }.onFailure {
            _getProfileResponse.value = it.errorHandling()
        }
    }

    fun onProfileValueChange(myProfile: MyProfileData) {
        _myProfileData.value = myProfile
    }

    fun onPdfValueChange(uri: Uri) {
        _pdfData.value = uri
    }

    private fun putChangedProfile(changedProfile: PutChangeProfileRequestModel) = viewModelScope.launch {
        _isProfileChanged.value = false
        _isProjectIconChanged.value = false
        _isProjectPreviewsChanged.value = false
        putChangedProfileUseCase(profile = changedProfile).onSuccess {
            it.catch { remoteError ->
                _putChangedProfileResponse.value = remoteError.errorHandling()
            }.collect {
                _putChangedProfileResponse.value = Event.Success()
            }
        }.onFailure {
            _putChangedProfileResponse.value = it.errorHandling()
        }
    }

    fun putChangeProfile() {
        val myProfile = myProfileData.value
        putChangedProfile(
            PutChangeProfileRequestModel(
                name = myProfile.name,
                introduce = myProfile.introduce,
                portfolioUrl = myProfile.portfolioUrl,
                portfolioFileUrl = null,
                grade = myProfile.grade,
                classNum = myProfile.classNum,
                number = myProfile.number,
                department = myProfile.department,
                major = myProfile.major,
                certificates = myProfile.certificates,
                contactEmail = myProfile.contactEmail,
                gsmAuthenticationScore = myProfile.gsmAuthenticationScore,
                formOfEmployment = myProfile.formOfEmployment.name,
                regions = myProfile.regions,
                militaryService = myProfile.militaryService.name,
                salary = myProfile.salary,
                languageCertificates = myProfile.languageCertificates,
                prizes = awards.value.map {
                    PrizeModel(
                        name = it.title,
                        type = it.organization,
                        date = it.date
                    )
                },
                techStacks = techStacks.value,
                profileImg = myProfile.profileImg,
                projects = projects.value.map {
                    ProjectModel(
                        name = it.name,
                        icon = it.icon,
                        previewImages = it.projectImage,
                        description = it.description,
                        links = it.relatedLinks.map { link ->
                            LinkModel(
                                name = link.name,
                                url = link.link
                            )
                        },
                        techStacks = it.techStacks,
                        task = it.keyTask,
                        activityDuration = ActivityDuration(
                            start = it.activityDuration.start,
                            end = it.activityDuration.end
                        )
                    )
                }
            )
        )
    }

    fun putChangedPortfolioPdf(context: Context) = viewModelScope.launch {
        putChangedPortfolioPdfUseCase(file = pdfData.value?.toMultipartBody(context)!!).onSuccess {
            it.catch { remoteError ->
                _putChangedPortfolioPdfResponse.value = remoteError.errorHandling()
            }.collect {
                _putChangedPortfolioPdfResponse.value = Event.Success()
            }
        }.onFailure {
            _putChangedPortfolioPdfResponse.value = it.errorHandling()
        }
    }
}