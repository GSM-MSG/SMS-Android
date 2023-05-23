import java.io.FileInputStream
import java.util.Properties

plugins {
    id(Dependency.Gradle.LIBRARY)
    id(Dependency.Gradle.KOTLIN)
    id(Dependency.Google.HILT_PLUGIN)
    kotlin(Dependency.Gradle.KAPT)
}

@Suppress("UnstableApiUsage")
android {
    namespace = ProjectProperties.NameSpace.PRESENTATION
    compileSdk = ProjectProperties.Versions.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectProperties.Versions.MIN_SDK
        targetSdk = ProjectProperties.Versions.TARGET_SDK

        testInstrumentationRunner = ProjectProperties.Test.TEST_RUNNER
        consumerProguardFiles(ProjectProperties.Files.CONSUMER_PROGUARDFILES)

        buildConfigField(
            "String",
            "CLIENT_ID",
            getApiKey("CLIENT_ID")
        )

        buildConfigField(
            "String",
            "REDIRECT_URI",
            getApiKey("REDIRECT_URI")
        )
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":design-system"))

    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.LIFECYCLE)
    implementation(Dependency.Compose.ACTIVITY_COMPOSE)
    implementation(Dependency.Compose.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_TOOLING)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_PREVIEW)
    implementation(Dependency.Msg.GAUTH)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependency.Test.ESPRESSO)
    androidTestImplementation(Dependency.Test.COMPOSE_JUNIT)
    debugImplementation(Dependency.Compose.COMPOSE_TOOLING)
    debugImplementation(Dependency.Test.COMPOSE_MANIFEST)

    implementation(Dependency.Google.HILT)
    kapt(Dependency.Google.HILT_COMPILER)
    implementation(Dependency.Navigation.NAVIGATION)

    implementation(Dependency.Libraries.OKHTTP)

    implementation(Dependency.Libraries.COIL)
}

fun getApiKey(propertyKey: String): String {
    val propFile = rootProject.file("./local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty(propertyKey)
}