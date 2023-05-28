package com.msg.sms.data.util

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.msg.sms.data.local.datasource.auth.LocalAuthDataSource
import com.msg.sms.domain.exception.NeedLoginException
import com.sms.data.BuildConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataSource: LocalAuthDataSource
) : Interceptor {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val currentTime = dateFormat.format(System.currentTimeMillis())
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

            if (refreshTime >= currentTime) throw NeedLoginException()
//            access 토큰 재 발급
            if (accessTime <= currentTime) {
                val client = OkHttpClient()
                val refreshRequest = Request.Builder()
                    .url(BuildConfig.BASE_URL + "auth")
                    .patch("".toRequestBody("application/json".toMediaTypeOrNull()))
                    .addHeader("Refresh-Token", "Bearer ${dataSource.getRefreshToken().first()}")
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
            builder.addHeader("Authorization", "Bearer ${dataSource.getAccessToken().first()}")
        }
        return chain.proceed(builder.build())
    }
}