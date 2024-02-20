package com.msg.sms.domain.repository

import kotlinx.coroutines.flow.Flow

interface TeacherRepository {
    suspend fun common(): Flow<Unit>

    suspend fun principal(): Flow<Unit>

    suspend fun vicePrincipal(): Flow<Unit>

    suspend fun headOfDepartment(): Flow<Unit>
}