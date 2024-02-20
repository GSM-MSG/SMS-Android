package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.teacher.RemoteTeacherDataSource
import com.msg.sms.domain.repository.TeacherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val remoteTeacherDataSource: RemoteTeacherDataSource
) : TeacherRepository {
    override suspend fun common(): Flow<Unit> {
        return remoteTeacherDataSource.common()
    }

    override suspend fun principal(): Flow<Unit> {
        return remoteTeacherDataSource.principal()
    }
}