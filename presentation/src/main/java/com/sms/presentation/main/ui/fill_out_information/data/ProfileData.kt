package com.sms.presentation.main.ui.fill_out_information.data

import android.net.Uri

data class ProfileData(
    val profileImageUri: Uri,
    val introduce: String,
    val contactEmail: String,
    val enteredMajor: String,
    val major: String,
    val techStack: List<String>
)
