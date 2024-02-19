package com.msg.sms.domain.repository

import kotlinx.coroutines.flow.Flow

interface TeacherRepository {
    suspend fun common(): Flow<Unit>
}