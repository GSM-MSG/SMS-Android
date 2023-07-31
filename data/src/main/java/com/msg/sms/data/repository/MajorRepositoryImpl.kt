package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.major.RemoteMajorDataSource
import com.msg.sms.data.remote.dto.major.toMajorListModel
import com.msg.sms.domain.model.major.MajorListModel
import com.msg.sms.domain.repository.MajorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MajorRepositoryImpl @Inject constructor(
    private val remoteMajorDataSource: RemoteMajorDataSource,
) : MajorRepository {
    override suspend fun getMajorList(): Flow<MajorListModel> {
        return remoteMajorDataSource.getMajorList().map { it.toMajorListModel() }
    }

}