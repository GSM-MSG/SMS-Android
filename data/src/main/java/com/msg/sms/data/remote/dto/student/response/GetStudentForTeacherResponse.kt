package com.msg.sms.data.remote.dto.student.response

import com.google.gson.annotations.SerializedName
import com.msg.sms.data.remote.dto.student.request.ProjectData
import com.msg.sms.data.remote.dto.student.request.ProjectDateData
import com.msg.sms.domain.model.student.response.CertificationModel
import com.msg.sms.domain.model.student.response.GetStudentForTeacher

data class GetStudentForTeacherResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("portfolioUrl")
    val portfolioUrl: String,
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
    @SerializedName("profileImgUrl")
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
    val languageCertificates: List<CertificationResponse>,
    @SerializedName("certificates")
    val certificates: List<String>,
    @SerializedName("techStacks")
    val techStacks: List<String>,
    @SerializedName("projects")
    val projects: List<ProjectData>,
    @SerializedName("prize")
    val prize: List<ProjectDateData>,
)

data class CertificationResponse(
    val languageCertificateName: String,
    val score: String,
)
//
//fun GetStudentForTeacherResponse.toGetStudentForTeacher(): GetStudentForTeacher {
//    return GetStudentForTeacher(
//        name = name,
//        introduce = introduce,
//        profileImg = profileImg,
//        portfolioUrl = portfolioUrl,
//        grade = grade,
//        classNum = classNum,
//        number = number,
//        department = department,
//        major = major,
//        contactEmail = contactEmail,
//        gsmAuthenticationScore = gsmAuthenticationScore,
//        formOfEmployment = formOfEmployment,
//        regions = regions,
//        militaryService = militaryService,
//        salary = salary,
//        languageCertificates = languageCertificates.map { it.toCertificationModel() },
//        techStacks = techStacks,
//        certificates = certificates
//    )
//}
//
fun CertificationResponse.toCertificationModel(): CertificationModel {
    return CertificationModel(languageCertificateName = languageCertificateName, score = score)
}