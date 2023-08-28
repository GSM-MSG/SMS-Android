package com.sms.presentation.main.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.exception.BadRequestException
import com.msg.sms.domain.exception.UnKnownException
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.model.student.request.CertificateInformationModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.request.PrizeModel
import com.msg.sms.domain.model.student.request.ProjectModel
import com.msg.sms.domain.usecase.fileupload.ImageUploadUseCase
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.msg.sms.domain.usecase.student.EnterStudentInformationUseCase
import com.sms.presentation.main.ui.fill_out_information.data.*
import com.sms.presentation.main.ui.util.toMultipartBody
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    private val _imageUploadComplete = MutableStateFlow(false)
    val imageUploadComplete = _imageUploadComplete.asStateFlow()

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

    private var profileImageUploadComplete = false
    private var projectsIconImageUploadComplete = false
    private var projectsPreviewImageUploadComplete = false

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
        return awards
    }

    fun setEnteredAwardsInformation(
        awards: List<AwardData>
    ) {
        this.awards.removeAll { !awards.contains(it) }
        this.awards.addAll(awards.filter { !this.awards.contains(it) })
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
        projects: List<ProjectModel>,
        award: List<PrizeModel>
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
                projects = projects,
                prizes = award
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

    private suspend fun imageUpload(file: MultipartBody.Part): Event<String> {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                imageUploadUseCase(
                    file = file
                ).onSuccess {
                    it.catch { remoteError ->
                        continuation.resume(remoteError.errorHandling())
                    }.collect { response ->
                        continuation.resume(Event.Success(data = response.fileUrl))
                    }
                }.onFailure { error ->
                    continuation.resume(error.errorHandling())
                }
            }
        }
    }

    fun profileImageUpload(
        context: Context,
        onSuccess: (url: String) -> Unit
    ) = viewModelScope.launch {
        val request = async { imageUpload(profileImageUri.value.toMultipartBody(context)!!) }

        when (request.await()) {
            is Event.Success -> {
                profileImageUploadComplete = true
                setImageCompleteResponseInfo()
                onSuccess(request.await().data!!)
            }
            is Event.BadRequest -> throw BadRequestException("프로필 이미지의 형식이 맞지 않습니다.")
            else -> throw UnKnownException("알 수 없는 에러 발생")
        }
    }

    fun projectsIconUpload(
        context: Context,
        onSuccess: (iconList: List<String>) -> Unit
    ) = viewModelScope.launch {
        val urlList = Array(projects.size) { "" }

        projects.forEachIndexed { index, projectInfo ->
            val request = async { imageUpload(projectInfo.icon.toMultipartBody(context)!!) }

            when (request.await()) {
                is Event.Success -> urlList[index] = request.await().data!!
                is Event.BadRequest -> throw BadRequestException("${index + 1}번째 프로젝트의 아이콘 형식이 맞지 않습니다.")
                else -> throw UnKnownException("알 수 없는 에러 발생")
            }

            if (index == projects.lastIndex) {
                projectsIconImageUploadComplete = true
                setImageCompleteResponseInfo()
                onSuccess(urlList.toList())
            }
        }
    }

    fun projectsPreview(
        context: Context,
        onSuccess: (previewList: List<List<String>>) -> Unit
    ) = viewModelScope.launch {
        val previewList = Array(projects.size) { Array(projects[it].preview.size) { "" } }

        projects.forEachIndexed { projectIndex, projectInfo ->
            projectInfo.preview.forEachIndexed { previewIndex, preview ->
                val request = async { imageUpload(preview.toMultipartBody(context)!!) }

                when (request.await()) {
                    is Event.Success -> previewList[projectIndex][previewIndex] = request.await().data!!
                    is Event.BadRequest -> throw BadRequestException("${projectIndex + 1}번째 프로젝트의 ${previewIndex + 1}번째 프리뷰 이미지의 형식이 맞지 않습니다.")
                    else -> throw UnKnownException("알 수 없는 에러 발생")
                }
            }

            if (projectIndex == projects.lastIndex) {
                projectsPreviewImageUploadComplete = true
                setImageCompleteResponseInfo()
                onSuccess(previewList.toList().map { it.toList() })
            }
        }
    }

    private fun setImageCompleteResponseInfo() {
        _imageUploadComplete.value = profileImageUploadComplete && projectsIconImageUploadComplete && projectsPreviewImageUploadComplete
    }
}