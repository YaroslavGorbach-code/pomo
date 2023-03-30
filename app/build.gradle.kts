plugins {
    id(Plugins.kotlinAndroid)
    id(Plugins.androidApplication)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
}

apply {
    from("$rootDir/common-android-library-compose.gradle")
}

android {
    namespace = "com.example.yaroslavhorach.pomo"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(Modules.coreDesignSystem))
    implementation(project(Modules.featureHome))
    implementation(project(Modules.data))

    implementation(Libs.activityCompose)
    implementation(Libs.systemUiController)
    implementation(Libs.splechScreen)

    implementation(Libs.hilt)
    implementation(Libs.hiltHavigationCompose)
    kapt(Libs.hiltCompiler)
}