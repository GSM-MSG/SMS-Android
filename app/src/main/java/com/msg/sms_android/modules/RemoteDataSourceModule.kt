package com.msg.sms_android.modules

import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSource
import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSourceImpl
import com.msg.sms.data.remote.datasource.fileupload.RemoteFileUploadDataSource
import com.msg.sms.data.remote.datasource.fileupload.RemoteFileUploadDataSourceImpl
import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSource
import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun provideRemoteAuthDataSource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl
    ): RemoteAuthDataSource

    @Binds
    abstract fun provideRemoteStudentDataSource(
        remoteStudentDataSourceImpl: RemoteStudentDataSourceImpl
    ): RemoteStudentDataSource

    @Binds
    abstract fun provideRemoteFileUploadDataSource(
        remoteFileUploadDataSourceImpl: RemoteFileUploadDataSourceImpl
    ): RemoteFileUploadDataSource
}