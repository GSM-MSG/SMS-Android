import java.io.FileInputStream
import java.util.Properties

plugins {
    id(Dependency.Gradle.APPLICATION)
    id(Dependency.Gradle.KOTLIN)
    id(Dependency.Google.HILT_PLUGIN)
    kotlin(Dependency.Gradle.KAPT)
    id(Dependency.Google.GOOGLE_SERVICES_PLUGIN)
    id(Dependency.Google.FIREBASE_CRASHLYTICS_PLUGIN)
}

@Suppress("UnstableApiUsage")
android {
    namespace = ProjectProperties.NameSpace.APP
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        applicationId = ProjectProperties.Id.APPLICATION_ID
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK
        versionCode = ProjectProperties.Versions.VERSION_CODE
        versionName = ProjectProperties.Versions.VERSION_NAME

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
        productFlavors {
            create("dev") {
                buildConfigField("String",
                    "BASE_URL",
                    getApiKey("BASE_URL_DEV")
                )
                applicationIdSuffix=".dev"
                versionNameSuffix="-DEV"
            }
            create("live") {
                buildConfigField("String",
                    "BASE_URL",
                    getApiKey("BASE_URL")
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ProjectProperties.Files.DEFAULT_PROGUARDFILES),
                ProjectProperties.Files.PROGUARDFILES
            )
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.Versions.JAVA_VERSION
        targetCompatibility = ProjectProperties.Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.Versions.JVM_TARGET
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.Google.MATERIAL)
    implementation(Dependency.DataStore.PREFERENCES)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO)

    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)

    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(platform(Dependency.Google.FIREBASE_BOM))
    implementation(Dependency.Google.FIREBASE_ANALYTICS)
    implementation(Dependency.Google.FIREBASE_CRASHLYTICS)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}