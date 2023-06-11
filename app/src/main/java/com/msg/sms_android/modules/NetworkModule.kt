package com.msg.sms_android.modules

import com.msg.sms.data.remote.network.api.AuthAPI
import com.msg.sms.data.remote.network.api.FileUploadAPI
import com.msg.sms.data.remote.network.api.MajorAPI
import com.msg.sms.data.remote.network.api.StudentAPI
import com.msg.sms.data.util.AuthInterceptor
import com.msg.sms_android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .cookieJar(CookieJar.NO_COOKIES)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideStudentService(retrofit: Retrofit): StudentAPI {
        return retrofit.create(StudentAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideFileUploadService(retrofit: Retrofit): FileUploadAPI {
        return retrofit.create(FileUploadAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMajorService(retrofit: Retrofit): MajorAPI {
        return retrofit.create(MajorAPI::class.java)
    }
}