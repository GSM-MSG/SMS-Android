package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.authentication.RemoteAuthenticationDataSource
import com.msg.sms.data.remote.dto.athentication.request.AuthenticationFieldRequest
import com.msg.sms.data.remote.dto.athentication.request.AuthenticationGroupRequest
import com.msg.sms.data.remote.dto.athentication.request.AuthenticationSectionRequest
import com.msg.sms.data.remote.dto.athentication.request.SubmitAuthenticationFormRequest
import com.msg.sms.data.remote.dto.athentication.response.toVerifyAuthenticationResponseModel
import com.msg.sms.domain.model.authentication.request.SubmitAuthenticationModel
import com.msg.sms.domain.model.authentication.response.AuthenticationFormModel
import com.msg.sms.domain.model.authentication.response.VerifyAuthenticationModel
import com.msg.sms.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteAuthenticationDataSource,
) : AuthenticationRepository {
    override suspend fun fetchAuthenticationForm(): Flow<AuthenticationFormModel> =
        remoteDataSource.fetchAuthenticationForm().map {
            it.toAuthenticationFormModel()
        }

    override suspend fun submitAuthenticationForm(formData: SubmitAuthenticationModel): Flow<Unit> {
        return remoteDataSource.submitAuthenticationForm(
            formData = SubmitAuthenticationFormRequest(
                contents = formData.contents.map {
                    AuthenticationSectionRequest(
                        sectionId = it.sectionId,
                        objects = it.objects.map { data ->
                            AuthenticationGroupRequest(
                                groupId = data.groupId,
                                fields = data.fields.map { field ->
                                    AuthenticationFieldRequest(
                                        fieldId = field.fieldId,
                                        value = field.value,
                                        fieldType = field.fieldType.name,
                                        selectId = field.selectId
                                    )
                                }
                            )
                        }
                    )
                }
            )
        )
    }

    override suspend fun verifyAuthentication(): Flow<VerifyAuthenticationModel> {
        return remoteDataSource.verifyAuthentication()
            .map { it.toVerifyAuthenticationResponseModel() }
    }
}