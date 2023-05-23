package com.msg.sms_android.modules

import com.msg.sms.data.repository.AuthRepositoryImpl
import com.msg.sms.data.repository.FileUploadRepositoryImpl
import com.msg.sms.data.repository.StudentRepositoryImpl
import com.msg.sms.domain.repository.AuthRepository
import com.msg.sms.domain.repository.FileUploadRepository
import com.msg.sms.domain.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideStudentRepository(
        studentRepositoryImpl: StudentRepositoryImpl
    ): StudentRepository

    @Binds
    abstract fun provideFileUploadRepository(
        fileUploadRepositoryImpl: FileUploadRepositoryImpl
    ): FileUploadRepository
}