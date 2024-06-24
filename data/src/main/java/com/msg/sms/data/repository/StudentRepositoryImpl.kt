package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSource
import com.msg.sms.data.remote.dto.common.CertificateData
import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import com.msg.sms.data.remote.dto.common.PrizeData
import com.msg.sms.data.remote.dto.common.ProjectData
import com.msg.sms.data.remote.dto.common.ProjectDateData
import com.msg.sms.data.remote.dto.common.ProjectRelatedLinkData
import com.msg.sms.data.remote.dto.student.request.CreateInformationLinkRequest
import com.msg.sms.data.remote.dto.student.request.PutChangedProfileRequest
import com.msg.sms.data.remote.dto.student.response.toCreateInformationLinkModel
import com.msg.sms.data.remote.dto.student.response.toGetStudentForTeacherModel
import com.msg.sms.data.remote.dto.student.response.toStudentListModel
import com.msg.sms.domain.model.student.request.CreateInformationLinkRequestModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.response.CreateInformationLinkResponseModel
import com.msg.sms.domain.model.student.response.GetStudentModel
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.model.user.response.MyProfileModel
import com.msg.sms.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val dataSource: RemoteStudentDataSource,
) : StudentRepository {
    override suspend fun enterStudentInformation(body: EnterStudentInformationModel): Flow<Unit> {
        return dataSource.enterStudentInformation(
            body = EnterStudentInformationRequest(
                major = body.major,
                techStacks = body.techStacks,
                profileImgUrl = body.profileImgUrl,
                introduce = body.introduce,
                contactEmail = body.contactEmail
            )
        )
    }

    override suspend fun getStudentList(
        page: Int,
        size: Int,
        majors: List<String>?,
        techStacks: List<String>?,
        grade: List<Int>?,
        classNum: List<Int>?,
        department: List<String>?,
        stuNumSort: String?,
        formOfEmployment: List<String>?,
        minGsmAuthenticationScore: Int?,
        maxGsmAuthenticationScore: Int?,
        minSalary: Int?,
        maxSalary: Int?,
        gsmAuthenticationScoreSort: String?,
        salarySort: String?,
    ): Flow<StudentListModel> {
        return dataSource.getStudentList(
            page = page,
            size = size,
            majors = majors,
            techStacks = techStacks,
            grade = grade,
            classNum = classNum,
            department = department,
            stuNumSort = stuNumSort,
            formOfEmployment = formOfEmployment,
            minGsmAuthenticationScore = minGsmAuthenticationScore,
            maxGsmAuthenticationScore = maxGsmAuthenticationScore,
            minSalary = minSalary,
            maxSalary = maxSalary,
            gsmAuthenticationScoreSort = gsmAuthenticationScoreSort,
            salarySort = salarySort
        ).map { it.toStudentListModel() }
    }

    override suspend fun getUserDetail(role: String, uuid: UUID): Flow<GetStudentModel> {
        return if (role.isEmpty()) dataSource.getUserDetail(role = role, uuid = uuid).map { it.toGetStudentForTeacherModel() }
        else dataSource.getUserDetailRole(role = role, uuid = uuid).map { it.toGetStudentForTeacherModel() }
    }

    override suspend fun putChangedProfile(profile: MyProfileModel): Flow<Unit> {
        return dataSource.putChangedProfile(
            body = PutChangedProfileRequest(
                major = profile.major,
                techStacks = profile.techStacks,
                profileImgUrl = profile.profileImg,
                introduce = profile.introduce,
                portfolioUrl = profile.portfolioUrl,
                contactEmail = profile.contactEmail,
                formOfEmployment = profile.formOfEmployment,
                gsmAuthenticationScore = profile.gsmAuthenticationScore,
                salary = profile.salary,
                regions = profile.regions,
                languageCertificates = profile.languageCertificates.map {
                    CertificateData(
                        languageCertificateName = it.languageCertificateName,
                        score = it.score
                    )
                },
                militaryService = profile.militaryService,
                certificates = profile.certificates,
                projects = profile.projects.map {
                    ProjectData(
                        name = it.name,
                        icon = it.icon,
                        previewImages = it.previewImages,
                        description = it.description,
                        links = it.links.map { link ->
                            ProjectRelatedLinkData(
                                name = link.name,
                                url = link.url
                            )
                        }, techStacks = it.techStacks,
                        myActivity = it.task,
                        inProgress = ProjectDateData(
                            start = it.activityDuration.start,
                            end = it.activityDuration.end
                        )
                    )
                },
                prizes = profile.prizes.map {
                    PrizeData(name = it.name, type = it.type, date = it.date)
                },
            )
        )
    }

    override suspend fun createInformationLink(body: CreateInformationLinkRequestModel): Flow<CreateInformationLinkResponseModel> {
        return dataSource.createInformationLink(
            body = CreateInformationLinkRequest(
                studentId = body.studentId,
                periodDay = body.periodDay
            )
        ).map { it.toCreateInformationLinkModel() }
    }
}