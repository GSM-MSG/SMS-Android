package com.msg.sms.data.repository

import com.msg.sms.data.remote.datasource.stack.RemoteStackDataSource
import com.msg.sms.data.remote.dto.stack.reponse.toDetailStackListModel
import com.msg.sms.domain.model.stack.DetailStackListModel
import com.msg.sms.domain.repository.StackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StackRepositoryImpl @Inject constructor(
    private val remoteStackDataSource: RemoteStackDataSource,
) : StackRepository {
    override suspend fun getDetailStack(name: String): Flow<DetailStackListModel> {
        return remoteStackDataSource.getSearchDetailStack(name = name)
            .map { it.toDetailStackListModel() }
    }
}