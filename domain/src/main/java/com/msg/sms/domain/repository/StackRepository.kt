package com.msg.sms.domain.repository

import com.msg.sms.domain.model.stack.DetailStackListModel
import kotlinx.coroutines.flow.Flow

interface StackRepository {
    suspend fun getDetailStack(name: String): Flow<DetailStackListModel>
}