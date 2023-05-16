package com.msg.sms.domain.repository

import com.msg.sms.domain.model.student.request.EnterStudentInformationModel
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun enterStudentInformation(body: EnterStudentInformationModel): Flow<Unit>
}