package com.msg.sms.domain.repository

import com.msg.sms.domain.model.authentication.AuthenticationFormModel
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormModel>
}