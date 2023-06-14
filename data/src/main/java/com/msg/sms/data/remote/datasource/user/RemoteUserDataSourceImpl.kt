package com.msg.sms.data.remote.datasource.user

import com.msg.sms.data.remote.dto.user.response.GetProfileImageResponse
import com.msg.sms.data.remote.network.api.UserAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val service: UserAPI
) : RemoteUserDataSource {
    override suspend fun getProfileImage(): Flow<GetProfileImageResponse> {
        return flow {
            emit(
                SMSApiHandler<GetProfileImageResponse>()
                    .httpRequest { service.getProfileImage() }
                    .sendRequest()
            )
        }.flowOn(Dispatchers.IO)
    }
}