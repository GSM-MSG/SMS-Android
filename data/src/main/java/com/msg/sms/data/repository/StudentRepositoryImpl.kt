package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSource
import com.msg.sms.data.remote.dto.student.request.CertificateInformation
import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val dataSource: RemoteStudentDataSource
) : StudentRepository {
    override suspend fun enterStudentInformation(body: EnterStudentInformationModel): Flow<Unit> {
        return dataSource.enterStudentInformation(
            body = EnterStudentInformationRequest(
                major = body.major,
                techStack = body.techStack,
                profileImgUrl = body.profileImgUrl,
                introduce = body.introduce,
                portfolioUrl = body.portfolioUrl,
                contactEmail = body.contactEmail,
                formOfEmployment = body.formOfEmployment,
                gsmAuthenticationScore = body.gsmAuthenticationScore,
                salary = body.salary,
                region = body.region,
                languageCertificate = body.languageCertificate.map {
                    CertificateInformation(
                        languageCertificateName = it.languageCertificateName,
                        score = it.score
                    )
                },
                dreamBookFileUrl = body.dreamBookFileUrl,
                militaryService = body.militaryService,
                certificate = body.certificate
            )
        )
    }
}