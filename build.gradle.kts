plugins {
    id(Dependency.Gradle.APPLICATION) version Versions.GRADLE apply false
    id(Dependency.Gradle.LIBRARY) version Versions.GRADLE apply false
    id(Dependency.Gradle.KOTLIN) version Versions.KOTLIN apply false
    id(Dependency.Google.HILT_PLUGIN) version Versions.HILT apply false
    id(Dependency.Google.GOOGLE_SERVICES_PLUGIN) version Versions.GOOGLE_SERVICE_PLUGIN apply false
}