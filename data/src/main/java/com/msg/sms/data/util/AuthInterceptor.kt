package com.msg.sms.data.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.msg.sms.data.local.datasource.auth.LocalAuthDataSource
import com.msg.sms.domain.exception.NeedLoginException
import com.sms.data.BuildConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataSource: LocalAuthDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val currentTime = System.currentTimeMillis().toSmsTimeDate()
        val ignorePath = listOf("/auth")
        val ignoreMethod = listOf("POST")
        val path = request.url.encodedPath
        val method = request.method

        ignorePath.forEachIndexed { index, s ->
            if (s == path && ignoreMethod[index] == method) {
                return chain.proceed(request)
            }
        }
        runBlocking {
            val refreshTime = dataSource.getRefreshTime().first()
            val accessTime = dataSource.getAccessTime().first()

            if (refreshTime == "" || currentTime.after(refreshTime.toDate())) throw NeedLoginException()
//            access 토큰 재 발급
            if (currentTime.after(accessTime.toDate())) {
                val client = OkHttpClient()
                val refreshRequest = Request.Builder()
                    .url(BuildConfig.BASE_URL + "auth")
                    .patch(chain.request().body ?: RequestBody.create(null, byteArrayOf()))
                    .addHeader("Refresh-Token", dataSource.getRefreshToken().first())
                    .build()
                val jsonParser = JsonParser()
                val response = client.newCall(refreshRequest).execute()
                if (response.isSuccessful) {
                    val token = jsonParser.parse(response.body!!.string()) as JsonObject
                    dataSource.setAccessToken(token["accessToken"].toString())
                    dataSource.setRefreshToken(token["refreshToken"].toString())
                    dataSource.setAccessTime(token["accessTokenExp"].toString())
                    dataSource.setRefreshTime(token["refreshTokenExp"].toString())
                } else throw NeedLoginException()
            }
            val accessToken = dataSource.getAccessToken().first()
            builder.addHeader("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}