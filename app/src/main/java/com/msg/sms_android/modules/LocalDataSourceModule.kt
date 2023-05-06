package com.msg.sms_android.modules

import com.msg.sms.data.local.datasource.auth.LocalAuthDataSource
import com.msg.sms.data.local.datasource.auth.LocalAuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun provideLocalAuthDataSource(
        localAuthDataSourceImpl: LocalAuthDataSourceImpl
    ): LocalAuthDataSource
}