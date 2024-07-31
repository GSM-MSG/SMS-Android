package com.msg.sms_android.modules

import com.msg.sms.data.repository.AuthRepositoryImpl
import com.msg.sms.data.repository.AuthenticationRepositoryImpl
import com.msg.sms.data.repository.FileUploadRepositoryImpl
import com.msg.sms.data.repository.MajorRepositoryImpl
import com.msg.sms.data.repository.StackRepositoryImpl
import com.msg.sms.data.repository.StudentRepositoryImpl
import com.msg.sms.data.repository.TeacherRepositoryImpl
import com.msg.sms.data.repository.UserRepositoryImpl
import com.msg.sms.domain.repository.AuthRepository
import com.msg.sms.domain.repository.AuthenticationRepository
import com.msg.sms.domain.repository.FileUploadRepository
import com.msg.sms.domain.repository.MajorRepository
import com.msg.sms.domain.repository.StackRepository
import com.msg.sms.domain.repository.StudentRepository
import com.msg.sms.domain.repository.TeacherRepository
import com.msg.sms.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    abstract fun provideStudentRepository(
        studentRepositoryImpl: StudentRepositoryImpl,
    ): StudentRepository

    @Binds
    abstract fun provideFileUploadRepository(
        fileUploadRepositoryImpl: FileUploadRepositoryImpl,
    ): FileUploadRepository

    @Binds
    abstract fun provideMajorRepository(
        majorRepositoryImpl: MajorRepositoryImpl,
    ): MajorRepository

    @Binds
    abstract fun provideStackRepository(
        stackRepositoryImpl: StackRepositoryImpl,
    ): StackRepository

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun provideAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl,
    ): AuthenticationRepository

    @Binds
    abstract fun provideTeacherRepository(
        teacherRepositoryImpl: TeacherRepositoryImpl,
    ): TeacherRepository
}