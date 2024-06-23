package com.msg.sms.data.remote.datasource.authentication

import com.msg.sms.data.remote.dto.athentication.request.SubmitAuthenticationFormRequest
import com.msg.sms.data.remote.dto.athentication.response.AuthenticationFormResponse
import kotlinx.coroutines.flow.Flow

interface RemoteAuthenticationDataSource {
    suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormResponse>

    suspend fun submitAuthenticationForm(formData: SubmitAuthenticationFormRequest): Flow<Unit>
}