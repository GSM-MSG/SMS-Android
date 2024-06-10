package com.msg.sms.data.remote.datasource.teacher

import com.msg.sms.data.remote.dto.teacher.request.HomeroomTeacherRequest
import kotlinx.coroutines.flow.Flow

interface RemoteTeacherDataSource {
    suspend fun common(): Flow<Unit>

    suspend fun principal(): Flow<Unit>

    suspend fun vicePrincipal(): Flow<Unit>

    suspend fun headOfDepartment(): Flow<Unit>

    suspend fun homeroom(body: HomeroomTeacherRequest): Flow<Unit>
}