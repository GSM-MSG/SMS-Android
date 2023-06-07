package com.msg.sms.data.remote.datasource.student

import com.msg.sms.data.remote.dto.student.request.EnterStudentInformationRequest
import kotlinx.coroutines.flow.Flow

interface RemoteStudentDataSource {
    suspend fun enterStudentInformation(body: EnterStudentInformationRequest): Flow<Unit>

    suspend fun getStudentList(page: Int, size: Int): Flow<Unit>
}