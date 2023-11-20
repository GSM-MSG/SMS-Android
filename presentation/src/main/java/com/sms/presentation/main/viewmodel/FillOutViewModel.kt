package com.sms.presentation.main.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.model.common.CertificateModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.common.PrizeModel
import com.msg.sms.domain.model.student.request.ProjectModel
import com.msg.sms.domain.usecase.fileupload.ImageUploadUseCase
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.msg.sms.domain.usecase.student.EnterStudentInformationUseCase
import com.sms.presentation.main.ui.fill_out_information.data.*
import com.sms.presentation.main.ui.util.toMultipartBody
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class FillOutViewModel @Inject constructor(
    private val enterStudentInformationUseCase: EnterStudentInformationUseCase,
    private val getMajorListUseCase: GetMajorListUseCase,
    private val imageUploadUseCase: ImageUploadUseCase,
) : ViewModel() {
    private val _enterInformationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val enterInformationResponse = _enterInformationResponse.asStateFlow()

    private val _getMajorListResponse = MutableStateFlow<Event<MajorListModel>>(Event.Loading)
    val getMajorListResponse = _getMajorListResponse.asStateFlow()

    private val _profileImageUploadResponse = MutableStateFlow<Event<String>>(Event.Loading)
    val profileImageUploadResponse = _profileImageUploadResponse.asStateFlow()

    private val _projectIconImageUploadResponse =
        MutableStateFlow<Event<List<String>>>(Event.Loading)
    val projectIconImageUploadResponse = _projectIconImageUploadResponse.asStateFlow()

    private val _projectPreviewsImageUploadResponse =
        MutableStateFlow<Event<List<List<String>>>>(Event.Loading)
    val projectPreviewsImageUploadResponse = _projectPreviewsImageUploadResponse.asStateFlow()

    private val _onImageUploadComplete = MutableLiveData(false)
    val onImageUploadComplete: LiveData<Boolean> = _onImageUploadComplete

    private val major = mutableStateOf("")
    private val enteredMajor = mutableStateOf("")
    private val techStacks = mutableStateListOf<String>()
    private val profileImageUri = mutableStateOf(Uri.EMPTY)
    private val introduce = mutableStateOf("")
    private val portfolioUrl = mutableStateOf("")
    private val contactEmail = mutableStateOf("")
    private val formOfEmployment = mutableStateOf("")
    private val gsmAuthenticationScore = mutableStateOf("")
    private val salary = mutableStateOf(0)
    private val regions = mutableStateListOf("")
    private val militaryService = mutableStateOf("")
    private val certificates = mutableStateListOf("")
    private val foreignLanguages =
        mutableStateListOf(ForeignLanguageInfo(languageCertificateName = "", score = ""))
    private val projects = mutableStateListOf(ProjectInfo(isToggleOpen = true))
    private val awards = mutableStateListOf<AwardData>()

    private val _projectsRequiredInfoData = mutableStateOf(listOf(ProjectRequiredDataInfo()))
    val projectsRequiredInfoData: State<List<ProjectRequiredDataInfo>> = _projectsRequiredInfoData

    fun getEnteredProfileInformation(): ProfileData {
        return ProfileData(
            profileImageUri = profileImageUri.value,
            introduce = introduce.value,
            contactEmail = contactEmail.value,
            enteredMajor = enteredMajor.value,
            major = major.value,
            portfolioUrl = portfolioUrl.value,
            techStack = techStacks
        )
    }

    fun setEnteredProfileInformation(
        enteredMajor: String,
        major: String,
        techStack: List<String>,
        profileImgUri: Uri,
        introduce: String,
        contactEmail: String,
        portfolioUrl: String,
    ) {
        this.enteredMajor.value = enteredMajor
        this.major.value = major
        this.techStacks.removeAll { !techStack.contains(it) }
        this.techStacks.addAll(techStack.filter { !this.techStacks.contains(it) })
        this.profileImageUri.value = profileImgUri
        this.introduce.value = introduce
        this.contactEmail.value = contactEmail
        this.portfolioUrl.value = portfolioUrl
    }

    fun getEnteredWorkConditionInformation(): WorkConditionData {
        return WorkConditionData(
            formOfEmployment = formOfEmployment.value,
            salary = salary.value.toString(),
            regions = regions
        )
    }

    fun setEnteredWorkConditionInformation(
        formOfEmployment: String,
        salary: String,
        region: List<String>,
    ) {
        this.formOfEmployment.value = formOfEmployment
        this.salary.value = salary.toInt()
        this.regions.removeAll { !region.contains(it) }
        this.regions.addAll(region.filter { !this.regions.contains(it) })
    }

    fun getEnteredMilitaryServiceInformation(): MilitaryServiceData {
        return MilitaryServiceData(militaryService = militaryService.value)
    }

    fun setEnteredMilitaryServiceInformation(militaryService: String) {
        this.militaryService.value = militaryService
    }

    fun getEnteredCertification(): CertificationData {
        return CertificationData(certifications = certificates)
    }

    fun setEnteredCertification(certificate: List<String>) {
        this.certificates.removeAll { !certificate.contains(it) }
        this.certificates.addAll(certificate.filter { !this.certificates.contains(it) })
    }

    fun getEnteredSchoolLifeInformation(): SchoolLifeData {
        return SchoolLifeData(
            gsmAuthenticationScore = gsmAuthenticationScore.value
        )
    }

    fun setEnteredSchoolLifeInformation(
        gsmAuthenticationScore: String,
    ) {
        this.gsmAuthenticationScore.value = gsmAuthenticationScore
    }

    fun getEnteredProjectsInformation(): ProjectsData {
        return ProjectsData(projects = projects)
    }

    fun setEnteredProjectsInformation(
        projects: List<ProjectInfo>
    ) {
        this.projects.removeAll { !projects.contains(it) }
        this.projects.addAll(projects.filter { !this.projects.contains(it) })
    }

    fun getEnteredForeignLanguagesInformation(): ForeignLanguagesData {
        return ForeignLanguagesData(foreignLanguages = foreignLanguages)
    }

    fun setEnteredForeignLanguagesInformation(
        foreignLanguages: List<ForeignLanguageInfo>
    ) {
        this.foreignLanguages.removeAll { !foreignLanguages.contains(it) }
        this.foreignLanguages.addAll(foreignLanguages.filter { !this.foreignLanguages.contains(it) })
    }

    fun getEnteredAwardsInformation(): List<AwardData> {
        return this.awards
    }

    fun setEnteredAwardsInformation(
        awards: List<AwardData>
    ) {
        this.awards.removeAll { !awards.contains(it) }
        this.awards.addAll(awards.filter { !this.awards.contains(it) })
    }

    fun setProjectRequiredDataInformation(
        index: Int,
        data: ProjectRequiredDataInfo
    ) {
        val infoDataList = this._projectsRequiredInfoData.value.toMutableList()
        infoDataList[index] = data
        this._projectsRequiredInfoData.value = infoDataList
    }

    fun removeProjectRequiredDataInformation(
        index: Int
    ) {
        val infoDataList = this._projectsRequiredInfoData.value.toMutableList()
        infoDataList.removeAt(index)
        this._projectsRequiredInfoData.value = infoDataList
    }

    fun addProjectRequiredDataInformaion(
        data: ProjectRequiredDataInfo = ProjectRequiredDataInfo()
    ) {
        val infoDataList = this._projectsRequiredInfoData.value.toMutableList()
        infoDataList.add(data)
        this._projectsRequiredInfoData.value = infoDataList
    }

    fun getMajorList() {
        viewModelScope.launch {
            getMajorListUseCase().onSuccess {
                it.catch { remoteError ->
                    _getMajorListResponse.value = remoteError.errorHandling()
                }.collect { response ->
                    _getMajorListResponse.value = Event.Success(data = response)
                }
            }.onFailure {
                _getMajorListResponse.value = it.errorHandling()
            }
        }
    }

    fun enterStudentInformation(
        major: String,
        techStack: List<String>,
        profileImgUrl: String,
        introduce: String,
        contactEmail: String,
    ) = viewModelScope.launch {
        enterStudentInformationUseCase(
            EnterStudentInformationModel(
                major = major,
                techStacks = techStack,
                profileImgUrl = profileImgUrl,
                introduce = introduce,
                contactEmail = contactEmail
            )
        ).onSuccess {
            it.catch { remoteError ->
                _enterInformationResponse.value = remoteError.errorHandling()
            }.collect {
                _enterInformationResponse.value = Event.Success()
            }
        }.onFailure { error ->
            _enterInformationResponse.value = error.errorHandling()
        }
    }

    fun profileImageUploadAsync(profileImage: Uri, context: Context) = viewModelScope.async {
        imageUploadUseCase(
            file = profileImage.toMultipartBody(context)!!
        ).onSuccess {
            it.catch { remoteError ->
                _profileImageUploadResponse.value = remoteError.errorHandling()
                this@async.cancel()
            }.collect { response ->
                _profileImageUploadResponse.value = Event.Success(data = response.fileUrl)
            }
        }.onFailure { error ->
            _profileImageUploadResponse.value = error.errorHandling()
            this@async.cancel()
        }
    }

    fun projectsIconUploadAsync(projectsIcon: List<Uri>, context: Context) = viewModelScope.async {
        val iconUrlList = Array(projects.size) { "" }

        projectsIcon.forEachIndexed { index, uri ->
            imageUploadUseCase(
                file = uri.toMultipartBody(context)!!
            ).onSuccess {
                it.catch { remoteError ->
                    _projectIconImageUploadResponse.value = remoteError.errorHandling()
                    this@async.cancel()
                }.collect { response ->
                    iconUrlList[index] = response.fileUrl
                }
            }.onFailure { error ->
                _projectIconImageUploadResponse.value = error.errorHandling()
                this@async.cancel()
            }

            if (index == projects.lastIndex) {
                _projectIconImageUploadResponse.value = Event.Success(data = iconUrlList.toList())
            }
        }
    }

    fun projectsPreviewAsync(projectsPreviews: List<List<Uri>>, context: Context) =
        viewModelScope.async {
            val previewUrlList = Array(projects.size) { Array(projects[it].preview.size) { "" } }

            projectsPreviews.forEachIndexed { projectIndex, previews ->
                previews.forEachIndexed { index, uri ->
                    imageUploadUseCase(
                        file = uri.toMultipartBody(context)!!
                    ).onSuccess {
                        it.catch { remoteError ->
                            _projectPreviewsImageUploadResponse.value = remoteError.errorHandling()
                            this@async.cancel()
                        }.collect { response ->
                            previewUrlList[projectIndex][index] = response.fileUrl
                        }
                    }.onFailure { error ->
                        _projectPreviewsImageUploadResponse.value = error.errorHandling()
                        this@async.cancel()
                    }
                }

                if (projectIndex == projects.lastIndex) {
                    _projectPreviewsImageUploadResponse.value =
                        Event.Success(previewUrlList.toList().map { it.toList() })
                }
            }
        }
}