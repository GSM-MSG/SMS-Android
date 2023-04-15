pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven(url = "https://jitpack.io" )
        mavenCentral()
    }
}
rootProject.name = "SMS-Android"
include(":app")
include(":presentation")
include(":data")
include(":domain")
