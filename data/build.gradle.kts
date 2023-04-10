plugins {
    id(Dependency.Gradle.LIBRARY)
    id(Dependency.Gradle.KOTLIN)
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.sms.data"
    compileSdk = Versions.COMPILESDK

    defaultConfig {
        minSdk = Versions.MINSDK
        targetSdk = Versions.TARGETSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVAVERSION
        targetCompatibility = Versions.JAVAVERSION
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.Google.MATERIAL)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO)
}