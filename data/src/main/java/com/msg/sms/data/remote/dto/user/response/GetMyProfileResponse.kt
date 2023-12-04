package com.msg.sms.data.remote.dto.user.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.domain.model.user.response.LanguageCertificateModel
import com.msg.sms.domain.model.user.response.MyProfileModel

data class GetMyProfileResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("portfolioUrl")
    val portfolioUrl: String?,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("classNum")
    val classNum: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("department")
    val department: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("profileImg")
    val profileImg: String,
    @SerializedName("contactEmail")
    val contactEmail: String,
    @SerializedName("gsmAuthenticationScore")
    val gsmAuthenticationScore: Int,
    @SerializedName("formOfEmployment")
    val formOfEmployment: String,
    @SerializedName("regions")
    val regions: List<String>,
    @SerializedName("militaryService")
    val militaryService: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("languageCertificates")
    val languageCertificates: List<LanguageCertificateResponse>,
    @SerializedName("certificates")
    val certificates: List<String>,
    @SerializedName("techStacks")
    val techStacks: List<String>,
    @SerializedName("projects")
    val projects: List<ProjectResponse>,
    @SerializedName("prizes")
    val prizes: List<PrizeResponse>,
)

fun GetMyProfileResponse.toMyProfileModel(): MyProfileModel {
    return MyProfileModel(
        name = this.name,
        introduce = this.introduce,
        portfolioUrl = this.portfolioUrl,
        grade = this.grade,
        classNum = this.classNum,
        number = this.number,
        department = this.department,
        major = this.major,
        profileImg = this.profileImg,
        contactEmail = this.contactEmail,
        gsmAuthenticationScore = this.gsmAuthenticationScore,
        formOfEmployment = this.formOfEmployment,
        regions = this.regions,
        militaryService = this.militaryService,
        salary = this.salary,
        languageCertificates = this.languageCertificates.map {
            LanguageCertificateModel(
                languageCertificateName = it.languageCertificateName,
                score = it.score
            )
        },
        certificates = this.certificates,
        techStacks = this.techStacks,
        projects = this.projects.map { it.toProjectModel() },
        prizes = this.prizes.map { it.toPrizeModel() }
    )
}