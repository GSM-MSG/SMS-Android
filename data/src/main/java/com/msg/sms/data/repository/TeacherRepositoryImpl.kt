package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.teacher.RemoteTeacherDataSource
import com.msg.sms.data.remote.dto.teacher.request.HomeroomTeacherRequest
import com.msg.sms.domain.model.teacher.request.HomeroomTeacherRequestModel
import com.msg.sms.domain.repository.TeacherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun vicePrincipal(): Flow<Unit> {
        return remoteTeacherDataSource.vicePrincipal()
    }

    override suspend fun headOfDepartment(): Flow<Unit> {
        return remoteTeacherDataSource.headOfDepartment()
    }

    override suspend fun homeroom(body: HomeroomTeacherRequestModel): Flow<Unit> {
        return remoteTeacherDataSource.homeroom(
            body = HomeroomTeacherRequest(
                grade = body.grade,
                classNum = body.classNum
            )
        )
    }
}