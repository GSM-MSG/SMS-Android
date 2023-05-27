package com.sms.presentation.main.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.model.student.request.CertificateInformationModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.usecase.major.GetMajorListUseCase
import com.msg.sms.domain.usecase.student.EnterStudentInformationUseCase
import com.sms.presentation.main.ui.fill_out_information.data.CertificationData
import com.sms.presentation.main.ui.fill_out_information.data.MilitaryServiceData
import com.sms.presentation.main.ui.fill_out_information.data.ProfileData
import com.sms.presentation.main.ui.fill_out_information.data.WorkConditionData
import com.sms.presentation.main.viewmodel.util.Event
import com.sms.presentation.main.viewmodel.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val enterStudentInformationUseCase: EnterStudentInformationUseCase,
    private val getMajorListUseCase: GetMajorListUseCase,
) : ViewModel() {
    private val _enterInformationResponse = MutableStateFlow<Event<Unit>>(Event.Loading)
    val enterInformationResponse: StateFlow<Event<Unit>> get() = _enterInformationResponse

    private val _getMajorListResponse = MutableStateFlow<Event<MajorListModel>>(Event.Loading)
    val getMajorListResponse: StateFlow<Event<MajorListModel>> get() = _getMajorListResponse

    private val major = mutableStateOf("")
    private val techStack = mutableStateOf("")
    private val profileImageUri = mutableStateOf(Uri.EMPTY)
    private val introduce = mutableStateOf("")
    private val stuNum = mutableStateOf("")
    private val portfolioUrl = mutableStateOf("")
    private val contactEmail = mutableStateOf("")
    private val formOfEmployment = mutableStateOf("")
    private val gsmAuthenticationScore = mutableStateOf(0)
    private val salary = mutableStateOf(0)
    private val region = mutableStateListOf("")
    private val dreamBookFileUrl = mutableStateListOf("")
    private val militaryService = mutableStateOf("")
    private val certificate = mutableStateListOf("")

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

    suspend fun getMajorList() {
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

    fun enterStudentInformation(
        major: String,
        techStack: List<String>,
        profileImgUrl: String,
        introduce: String,
        stuNum: String,
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
                stuNum = stuNum,
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
}