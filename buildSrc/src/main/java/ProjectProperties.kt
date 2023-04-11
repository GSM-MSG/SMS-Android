import org.gradle.api.JavaVersion

object ProjectProperties {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val APPLICATION_ID = "com.msg.sms_android"

    const val COMPILE_SDK = 33
    const val MIN_SDK = 24
    const val TARGET_SDK = 33

    val JAVA_VERSION = JavaVersion.VERSION_1_8
}