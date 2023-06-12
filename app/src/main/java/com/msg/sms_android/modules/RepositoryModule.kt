package com.msg.sms_android.modules

import com.msg.sms.data.repository.*
import com.msg.sms.domain.repository.*
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

    @Binds
    abstract fun provideMajorRepository(
        majorRepositoryImpl: MajorRepositoryImpl
    ): MajorRepository

    @Binds
    abstract fun provideStackRepository(
        stackRepositoryImpl: StackRepositoryImpl
    ): StackRepository
}