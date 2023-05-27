package com.msg.sms.data.remote.datasource.major

import com.msg.sms.data.remote.dto.major.MajorListResponse
import com.msg.sms.data.remote.network.api.MajorAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteMajorDataSourceImpl @Inject constructor(
    private val majorAPI: MajorAPI,
) : RemoteMajorDataSource {
    override suspend fun getMajorList(): Flow<MajorListResponse> {
        return flow {
            emit(SMSApiHandler<MajorListResponse>()
                .httpRequest { majorAPI.getMajorList() }
                .sendRequest())
        }.flowOn(Dispatchers.IO)
    }
}