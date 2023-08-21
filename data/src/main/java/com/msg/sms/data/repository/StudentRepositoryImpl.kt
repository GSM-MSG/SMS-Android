package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSource
import com.msg.sms.data.remote.dto.student.request.*
import com.msg.sms.data.remote.dto.student.response.toGetStudentForAnonymous
import com.msg.sms.data.remote.dto.student.response.toGetStudentForStudent
import com.msg.sms.data.remote.dto.student.response.toGetStudentForTeacher
import com.msg.sms.data.remote.dto.student.response.toStudentListModel
import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import com.msg.sms.domain.model.student.response.GetStudentForAnonymous
import com.msg.sms.domain.model.student.response.GetStudentForStudent
import com.msg.sms.domain.model.student.response.GetStudentForTeacher
import com.msg.sms.domain.model.student.response.StudentListModel
import com.msg.sms.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
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
                portfolioUrl = body.portfolioUrl,
                contactEmail = body.contactEmail,
                formOfEmployment = body.formOfEmployment,
                gsmAuthenticationScore = body.gsmAuthenticationScore,
                salary = body.salary,
                regions = body.regions,
                languageCertificates = body.languageCertificates.map {
                    CertificateData(
                        languageCertificateName = it.languageCertificateName,
                        score = it.score
                    )
                },
                militaryService = body.militaryService,
                certificates = body.certificates,
                projects = body.projects.map { project ->
                    ProjectData(
                        name = project.name,
                        icon = project.icon,
                        previewImages = project.previewImages,
                        description = project.description,
                        links = project.links.map {
                            ProjectRelatedLinkData(
                                name = it.name,
                                url = it.url
                            )
                        },
                        techStacks = project.techStacks,
                        myActivity = project.myActivity,
                        inProgress = ProjectDateData(
                            start = project.inProgress.start,
                            end = project.inProgress.end
                        )
                    )
                },
                prizes = body.prizes.map {
                    PrizeData(
                        name = it.name,
                        type = it.type,
                        date = it.date
                    )
                }
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

    override suspend fun getUserDetailForStudent(uuid: UUID): Flow<GetStudentForStudent> {
        return dataSource.getUserDetailForStudent(uuid = uuid).map { it.toGetStudentForStudent() }
    }

    override suspend fun getUserDetailForAnonymous(uuid: UUID): Flow<GetStudentForAnonymous> {
        return dataSource.getUserDetailForAnonymous(uuid = uuid)
            .map { it.toGetStudentForAnonymous() }
    }

    override suspend fun getUserDetailForTeacher(uuid: UUID): Flow<GetStudentForTeacher> {
        return dataSource.getUserDetailForTeacher(uuid = uuid).map { it.toGetStudentForTeacher() }
    }
}