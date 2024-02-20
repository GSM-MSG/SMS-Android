package com.msg.sms.data.remote.datasource.teacher

import kotlinx.coroutines.flow.Flow

interface RemoteTeacherDataSource {
    suspend fun common(): Flow<Unit>

    suspend fun principal(): Flow<Unit>

    suspend fun vicePrincipal(): Flow<Unit>
}