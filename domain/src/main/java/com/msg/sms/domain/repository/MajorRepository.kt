package com.msg.sms.domain.repository

import com.msg.sms.domain.model.major.MajorListModel
import kotlinx.coroutines.flow.Flow

interface MajorRepository {

    suspend fun getMajorList(): Flow<MajorListModel>
}