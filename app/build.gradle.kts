plugins {
    id(Plugins.kotlinAndroid)
    id(Plugins.androidApplication)
}

apply {
    from("$rootDir/common-android-library.gradle")
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

    implementation(Libs.activityCompose)
    implementation(Libs.systemUiController)
    implementation(Libs.splechScreen)
}