package com.msg.sms.domain.repository

import com.msg.sms.domain.model.teacher.request.HomeroomTeacherRequestModel
import kotlinx.coroutines.flow.Flow

interface TeacherRepository {
    suspend fun common(): Flow<Unit>

    suspend fun principal(): Flow<Unit>

    suspend fun vicePrincipal(): Flow<Unit>

    suspend fun headOfDepartment(): Flow<Unit>

    suspend fun homeroom(body: HomeroomTeacherRequestModel): Flow<Unit>
}