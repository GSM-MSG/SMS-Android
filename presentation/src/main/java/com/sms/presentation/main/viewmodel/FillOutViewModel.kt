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
import com.msg.sms.domain.usecase.fileupload.DreamBookUploadUseCase
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
    private val dreamBookUploadUseCase: DreamBookUploadUseCase
) : ViewModel() {
    private val _enterInformationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val enterInformationResponse = _enterInformationResponse.asStateFlow()

    private val _getMajorListEvent = MutableStateFlow<Event<MajorListModel>>(Event.Loading)
    val getMajorListEvent = _getMajorListEvent.asStateFlow()

    private val _imageUploadResponse =
        MutableStateFlow<Event<FileUploadResponseModel>>(Event.Loading)
    val imageUploadResponse = _imageUploadResponse.asStateFlow()

    private val _dreamBookUploadResponse =
        MutableStateFlow<Event<FileUploadResponseModel>>(Event.Loading)
    val dreamBookUploadResponse = _dreamBookUploadResponse.asStateFlow()

    private val _fileUploadCompleted = MutableStateFlow(false)
    val fileUploadCompleted = _fileUploadCompleted.asStateFlow()

    private val major = mutableStateOf("")
    private val techStack = mutableStateOf("")
    private val profileImageUri = mutableStateOf(Uri.EMPTY)
    private val introduce = mutableStateOf("")
    private val portfolioUrl = mutableStateOf("")
    private val contactEmail = mutableStateOf("")
    private val formOfEmployment = mutableStateOf("")
    private val gsmAuthenticationScore = mutableStateOf("")
    private val salary = mutableStateOf(0)
    private val region = mutableStateListOf("")
    private val dreamBookFileUri = mutableStateOf(Uri.EMPTY)
    private val militaryService = mutableStateOf("")
    private val certificate = mutableStateListOf("")
    private lateinit var profileImageUrl: String
    private lateinit var dreamBookFileUrl: String

    fun getEnteredProfileInformation(): ProfileData {
        return ProfileData(
            profileImageUri = profileImageUri.value,
            introduce = introduce.value,
            contactEmail = contactEmail.value,
            major = major.value,
            portfolioUrl = portfolioUrl.value,
            techStack = techStack.value
        )
    }

    fun setEnteredProfileInformation(
        major: String,
        techStack: String,
        profileImgUri: Uri,
        introduce: String,
        contactEmail: String,
        portfolioUrl: String,
    ) {
        this.major.value = major
        this.techStack.value = techStack
        this.profileImageUri.value = profileImgUri
        this.introduce.value = introduce
        this.contactEmail.value = contactEmail
        this.portfolioUrl.value = portfolioUrl
    }

    fun getEnteredWorkConditionInformation(): WorkConditionData {
        return WorkConditionData(
            formOfEmployment = formOfEmployment.value,
            salary = salary.value.toString(),
            region = region
        )
    }

    fun setEnteredWorkConditionInformation(
        formOfEmployment: String,
        salary: String,
        region: List<String>,
    ) {
        this.formOfEmployment.value = formOfEmployment
        this.salary.value = salary.toInt()
        this.region.removeAll { !region.contains(it) }
        this.region.addAll(region.filter { !this.region.contains(it) })
    }

    fun getEnteredMilitaryServiceInformation(): MilitaryServiceData {
        return MilitaryServiceData(militaryService = militaryService.value)
    }

    fun setEnteredMilitaryServiceInformation(militaryService: String) {
        this.militaryService.value = militaryService
    }

    fun getEnteredCertification(): CertificationData {
        return CertificationData(certification = certificate)
    }

    fun setEnteredCertification(certificate: List<String>) {
        this.certificate.removeAll { !certificate.contains(it) }
        this.certificate.addAll(certificate.filter { !this.certificate.contains(it) })
    }

    fun getEnteredSchoolLifeInformation(): SchoolLifeData {
        return SchoolLifeData(
            gsmAuthenticationScore = gsmAuthenticationScore.value,
            dreamBookFileUri = dreamBookFileUri.value
        )
    }

    fun setEnteredSchoolLifeInformation(
        gsmAuthenticationScore: String,
        dreamBookFileUri: Uri
    ) {
        this.gsmAuthenticationScore.value = gsmAuthenticationScore
        this.dreamBookFileUri.value = dreamBookFileUri
    }

    fun getProfileImageUrl(): String {
        return profileImageUrl
    }

    fun setProfileImageUrl(profileImageUrl: String) {
        this.profileImageUrl = profileImageUrl
    }

    fun getDreamBookFileUrl(): String {
        return dreamBookFileUrl
    }

    fun setDreamBookFileUrl(dreamBookFileUrl: String) {
        this.dreamBookFileUrl = dreamBookFileUrl
    }


    fun getMajorList() {
        viewModelScope.launch {
            getMajorListUseCase().onSuccess {
                it.catch { remoteError ->
                    _getMajorListEvent.value = remoteError.errorHandling()
                }.collect { response ->
                    _getMajorListEvent.value = Event.Success(data = response)
                }
            }.onFailure {
                _getMajorListEvent.value = it.errorHandling()
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
        dreamBookFileUrl: String,
        militaryService: String,
        certificate: List<String>,
    ) = viewModelScope.launch {
        enterStudentInformationUseCase(
            EnterStudentInformationModel(
                major = major,
                techStack = techStack,
                profileImgUrl = profileImgUrl,
                introduce = introduce,
                portfolioUrl = portfolioUrl,
                contactEmail = contactEmail,
                formOfEmployment = formOfEmployment,
                gsmAuthenticationScore = gsmAuthenticationScore,
                salary = salary,
                region = region,
                languageCertificate = languageCertificate,
                dreamBookFileUrl = dreamBookFileUrl,
                militaryService = militaryService,
                certificate = certificate
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
            specifyWhenCompleteFileUpload()
            it.catch { remoteError ->
                _imageUploadResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _imageUploadResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _imageUploadResponse.value = error.errorHandling()
        }
    }

    fun dreamBookUpload(file: MultipartBody.Part) = viewModelScope.launch {
        dreamBookUploadUseCase(
            file = file
        ).onSuccess {
            specifyWhenCompleteFileUpload()
            it.catch { remoteError ->
                _dreamBookUploadResponse.value = remoteError.errorHandling()
            }.collect { response ->
                _dreamBookUploadResponse.value = Event.Success(data = response)
            }
        }.onFailure { error ->
            _dreamBookUploadResponse.value = error.errorHandling()
        }
    }

    private fun specifyWhenCompleteFileUpload() {
        _fileUploadCompleted.value =
            _imageUploadResponse.value is Event.Success && _dreamBookUploadResponse.value is Event.Success
    }
}