plugins {
    id(Plugins.kotlinAndroid)
    id(Plugins.androidApplication)
    id(Plugins.compose)
    id(Plugins.ksp)
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
    implementation(project(Modules.data))
    implementation(project(Modules.coreDesignSystem))
    implementation(project(Modules.featureHome))
    implementation(project(Modules.featureCreateTask))

    implementation(Libs.activityCompose)
    implementation(Libs.systemUiController)
    implementation(Libs.splashScreen)

    implementation(Libs.hilt)
    implementation(Libs.hiltNavigationCompose)
    ksp(Libs.hiltCompiler)
}