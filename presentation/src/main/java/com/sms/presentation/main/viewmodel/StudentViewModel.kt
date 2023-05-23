package com.sms.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.sms.domain.model.student.request.CertificateInformationModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.usecase.student.EnterStudentInformationUseCase
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
    private val enterStudentInformationUseCase: EnterStudentInformationUseCase
) : ViewModel() {
    private val _enterInformationResponse = MutableStateFlow<Event<Unit>>(Event.Loading())
    val enterInformationResponse: StateFlow<Event<Unit>> get() = _enterInformationResponse

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
        certificate: List<String>
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