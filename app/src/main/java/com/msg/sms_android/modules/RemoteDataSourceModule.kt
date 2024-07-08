package com.msg.sms_android.modules

import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSource
import com.msg.sms.data.remote.datasource.auth.RemoteAuthDataSourceImpl
import com.msg.sms.data.remote.datasource.fileupload.RemoteFileUploadDataSource
import com.msg.sms.data.remote.datasource.fileupload.RemoteFileUploadDataSourceImpl
import com.msg.sms.data.remote.datasource.major.RemoteMajorDataSource
import com.msg.sms.data.remote.datasource.major.RemoteMajorDataSourceImpl
import com.msg.sms.data.remote.datasource.stack.RemoteStackDataSource
import com.msg.sms.data.remote.datasource.stack.RemoteStackDataSourceImpl
import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSource
import com.msg.sms.data.remote.datasource.student.RemoteStudentDataSourceImpl
import com.msg.sms.data.remote.datasource.teacher.RemoteTeacherDataSource
import com.msg.sms.data.remote.datasource.teacher.RemoteTeacherDataSourceImpl
import com.msg.sms.data.remote.datasource.user.RemoteUserDataSource
import com.msg.sms.data.remote.datasource.user.RemoteUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun provideRemoteAuthDataSource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl,
    ): RemoteAuthDataSource

    @Binds
    abstract fun provideRemoteStudentDataSource(
        remoteStudentDataSourceImpl: RemoteStudentDataSourceImpl,
    ): RemoteStudentDataSource

    @Binds
    abstract fun provideRemoteFileUploadDataSource(
        remoteFileUploadDataSourceImpl: RemoteFileUploadDataSourceImpl,
    ): RemoteFileUploadDataSource

    @Binds
    abstract fun provideRemoteMajorDataSource(
        remoteMajorDataSourceImpl: RemoteMajorDataSourceImpl,
    ): RemoteMajorDataSource

    @Binds
    abstract fun provideRemoteStackDataSource(
        remoteStackDataSourceImpl: RemoteStackDataSourceImpl,
    ): RemoteStackDataSource

    @Binds
    abstract fun provideRemoteUserDataSource(
        remoteUserDataSourceImpl: RemoteUserDataSourceImpl,
    ): RemoteUserDataSource

    @Binds
    abstract fun provideRemoteTeacherDataSource(
        remoteTeacherDataSourceImpl: RemoteTeacherDataSourceImpl
    ): RemoteTeacherDataSource
}