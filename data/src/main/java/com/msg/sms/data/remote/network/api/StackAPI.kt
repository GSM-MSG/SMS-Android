package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.stack.reponse.SearchingDetailStackResponse
import retrofit2.http.GET

interface StackAPI {

    @GET
    suspend fun getSearchDetailStack(name: String): SearchingDetailStackResponse
}