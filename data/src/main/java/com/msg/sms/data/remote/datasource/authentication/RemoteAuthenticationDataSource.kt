package com.msg.sms.data.remote.datasource.authentication

import com.msg.sms.data.remote.dto.athentication.AuthenticationFormResponse
import kotlinx.coroutines.flow.Flow

interface RemoteAuthenticationDataSource {
    suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormResponse>
}