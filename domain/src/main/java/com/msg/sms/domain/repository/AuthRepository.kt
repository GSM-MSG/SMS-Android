package com.msg.sms.domain.repository

import com.msg.sms.domain.model.auth.request.GAuthLoginRequestData
import com.msg.sms.domain.model.auth.response.GAuthLoginResponseData

interface AuthRepository {
    suspend fun gAuthLogin(body: GAuthLoginRequestData): GAuthLoginResponseData
}