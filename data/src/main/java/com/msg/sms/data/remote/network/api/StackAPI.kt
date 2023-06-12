package com.msg.sms.data.remote.network.api

import com.msg.sms.data.remote.dto.stack.reponse.SearchingDetailStackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StackAPI {

    @GET("stack/list")
    suspend fun getSearchDetailStack(
        @Query("stack") name: String
    ): SearchingDetailStackResponse
}