package com.msg.sms.data.remote.datasource.major

import com.msg.sms.data.remote.dto.major.MajorListResponse

interface RemoteMajorDataSource {

    suspend fun getMajorList(): MajorListResponse
}