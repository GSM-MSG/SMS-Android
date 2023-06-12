package com.msg.sms.domain.repository

import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun gAuthLogin(body: GAuthLoginRequestModel): GAuthLoginResponseModel

    suspend fun saveTheLoginData(data: GAuthLoginResponseModel)

    suspend fun accessValidation(): Flow<Unit>
}