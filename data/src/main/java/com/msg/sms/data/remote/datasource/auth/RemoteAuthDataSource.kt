package com.msg.sms.data.remote.datasource.auth

import com.msg.sms.data.remote.dto.auth.response.GAuthLoginResponse
import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import kotlinx.coroutines.flow.Flow

interface RemoteAuthDataSource {
    suspend fun gAuthLogin(body: GAuthLoginRequestModel): Flow<GAuthLoginResponse>
}