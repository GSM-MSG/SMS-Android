package com.sms.presentation.main.ui.mypage.state

import android.graphics.Bitmap
import com.msg.sms.domain.model.user.response.LanguageCertificateModel

data class MyProfileData(
    val name: String,
    val introduce: String,
    val portfolioUrl: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: String,
    val major: String,
    val enteredMajor: String = "",
    val profileImg: String,
    val profileImageBitmap: Bitmap? = null,
    val contactEmail: String,
    val gsmAuthenticationScore: Int,
    val formOfEmployment: String,
    val regions: List<String>,
    val militaryService: String,
    val salary: Int,
    val languageCertificates: List<LanguageCertificateModel>,
    val certificates: List<String>,
)
