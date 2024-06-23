package com.msg.sms.domain.repository

import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFormModel
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormModel>

    suspend fun submitAuthenticationForm(formData: SubmitAuthenticationModel): Flow<Unit>
}