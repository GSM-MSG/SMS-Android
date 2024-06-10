package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.data.remote.dto.common.*
import com.msg.sms.domain.model.student.response.GetStudentModel

data class GetStudentResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("portfolioUrl")
    val portfolioUrl: String?,
    @SerializedName("grade")
    val grade: Int?,
    @SerializedName("classNum")
    val classNum: Int?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("major")
    val major: String,
    @SerializedName("profileImgUrl")
    val profileImg: String?,
    @SerializedName("contactEmail")
    val contactEmail: String?,
    @SerializedName("gsmAuthenticationScore")
    val gsmAuthenticationScore: Int?,
    @SerializedName("formOfEmployment")
    val formOfEmployment: String?,
    @SerializedName("regions")
    val regions: List<String>?,
    @SerializedName("militaryService")
    val militaryService: String?,
    @SerializedName("salary")
    val salary: Int?,
    @SerializedName("languageCertificates")
    val languageCertificates: List<CertificateData>?,
    @SerializedName("certificates")
    val certificates: List<String>?,
    @SerializedName("techStacks")
    val techStacks: List<String>,
    @SerializedName("projects")
    val projects: List<ProjectData>,
    @SerializedName("prizes")
    val prize: List<PrizeData>,
)


fun GetStudentResponse.toGetStudentForTeacherModel(): GetStudentModel {
    return GetStudentModel(
        name = this.name,
        introduce = this.introduce,
        profileImg = this.profileImg,
        portfolioUrl = this.portfolioUrl,
        grade = this.grade,
        classNum = this.classNum,
        number = this.number,
        department = this.department,
        major = this.major,
        contactEmail = this.contactEmail,
        gsmAuthenticationScore = this.gsmAuthenticationScore,
        formOfEmployment = this.formOfEmployment,
        regions = this.regions,
        militaryService = this.militaryService,
        salary = this.salary,
        languageCertificates = this.languageCertificates?.map { it.toCertificateModel() },
        techStacks = this.techStacks,
        certificates = this.certificates,
        projects = this.projects.map { it.toProjectModel() },
        prize = this.prize.map { it.toPrizeModel() }
    )
}
