package com.msg.sms.domain.repository

import com.msg.sms.domain.model.auth.request.GAuthLoginRequestModel
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseModel

interface AuthRepository {
    suspend fun gAuthLogin(body: GAuthLoginRequestModel): GAuthLoginResponseModel

    suspend fun saveTheLoginData(data: GAuthLoginResponseModel)
}