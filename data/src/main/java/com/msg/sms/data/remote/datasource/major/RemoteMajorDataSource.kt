package com.msg.sms.data.remote.datasource.major

import com.msg.sms.data.remote.dto.major.MajorListResponse
import kotlinx.coroutines.flow.Flow

interface RemoteMajorDataSource {

    suspend fun getMajorList(): Flow<MajorListResponse>
}