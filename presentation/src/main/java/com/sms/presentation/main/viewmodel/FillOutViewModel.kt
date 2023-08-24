package com.sms.presentation.main.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.fileupload.response.FileUploadResponseModel
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.model.student.request.CertificateInformationModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.usecase.fileupload.ImageUploadUseCase
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.msg.sms.domain.usecase.student.EnterStudentInformationUseCase
import com.sms.presentation.main.ui.fill_out_information.data.*
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
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

    private val _imageUploadResponse =
        MutableStateFlow<Event<FileUploadResponseModel>>(Event.Loading)
    val imageUploadResponse = _imageUploadResponse.asStateFlow()

    private val _fileUploadCompleted = MutableStateFlow(false)
    val fileUploadCompleted = _fileUploadCompleted.asStateFlow()

    private val major = mutableStateOf("")
    private val enteredMajor = mutableStateOf("")
    private val techStacks = mutableStateOf("")
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
    private val projects = mutableStateListOf(ProjectInfo(isToggleOpen = true))
    private lateinit var profileImageUrl: String

    fun getEnteredProfileInformation(): ProfileData {
        return ProfileData(
            profileImageUri = profileImageUri.value,
            introduce = introduce.value,
            contactEmail = contactEmail.value,
            enteredMajor = enteredMajor.value,
            major = major.value,
            portfolioUrl = portfolioUrl.value,
            techStack = techStacks.value
        )
    }

    fun setEnteredProfileInformation(
        enteredMajor: String,
        major: String,
        techStack: String,
        profileImgUri: Uri,
        introduce: String,
        contactEmail: String,
        portfolioUrl: String,
    ) {
        this.enteredMajor.value = enteredMajor
        this.major.value = major
        this.techStacks.value = techStack
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

    fun getProfileImageUrl(): String {
        return profileImageUrl
    }

    fun setProfileImageUrl(profileImageUrl: String) {
        this.profileImageUrl = profileImageUrl
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
        portfolioUrl: String,
        contactEmail: String,
        formOfEmployment: String,
        gsmAuthenticationScore: Int,
        salary: Int,
        region: List<String>,
        languageCertificate: List<CertificateInformationModel>,
        militaryService: String,
        certificate: List<String>,
    ) = viewModelScope.launch {
        enterStudentInformationUseCase(
            EnterStudentInformationModel(
                major = major,
                techStacks = techStack,
                profileImgUrl = profileImgUrl,
                introduce = introduce,
                portfolioUrl = portfolioUrl,
                contactEmail = contactEmail,
                formOfEmployment = formOfEmployment,
                gsmAuthenticationScore = gsmAuthenticationScore,
                salary = salary,
                regions = region,
                languageCertificates = languageCertificate,
                militaryService = militaryService,
                certificates = certificate,
                projects = emptyList(),
                prizes = emptyList()
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

    fun imageUpload(file: MultipartBody.Part) = viewModelScope.launch {
        imageUploadUseCase(
            file = file
        ).onSuccess {
            it.catch { remoteError ->
                _imageUploadResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _imageUploadResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _imageUploadResponse.value = error.errorHandling()
        }
    }

    fun specifyWhenCompleteFileUpload() {
        _fileUploadCompleted.value =
            _imageUploadResponse.value is Event.Success
    }
}