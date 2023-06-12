package com.msg.sms.data.remote.datasource.stack

import com.msg.sms.data.remote.dto.stack.reponse.SearchingDetailStackResponse
import com.msg.sms.data.remote.network.api.StackAPI
import com.msg.sms.data.util.SMSApiHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteStackDataSourceImpl @Inject constructor(
    private val stackAPI: StackAPI,
) : RemoteStackDataSource {
    override suspend fun getSearchDetailStack(name: String): Flow<SearchingDetailStackResponse> {
        return flow {
            emit(SMSApiHandler<SearchingDetailStackResponse>()
                .httpRequest { stackAPI.getSearchDetailStack(name = name) }
                .sendRequest())
        }.flowOn(Dispatchers.IO)
    }
}