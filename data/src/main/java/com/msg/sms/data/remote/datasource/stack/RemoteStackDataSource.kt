package com.msg.sms.data.remote.datasource.stack

import com.msg.sms.data.remote.dto.stack.reponse.SearchingDetailStackResponse
import kotlinx.coroutines.flow.Flow

interface RemoteStackDataSource {
    suspend fun getSearchDetailStack(name: String): Flow<SearchingDetailStackResponse>
}