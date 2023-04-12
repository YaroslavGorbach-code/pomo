import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdkVersion = 33
    const val targetSdkVersion = 33
    const val minSdkVersion = 24
    const val applicationId = "com.example.yaroslavhorach.pomo"
    const val versionCode = 1
    const val versionName = "0.1"
    const val jvmTarget = "11"
    const val kotlinCompiler = "1.4.3"

    val javaCompatibilityVersion = JavaVersion.VERSION_11
    val kotlinCompatibilityVersion = JavaVersion.VERSION_11
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    const val kapt = "kapt"
    const val kapt_kotlin = "kotlin-kapt"
    const val hilt = "com.google.dagger.hilt.android"
}

object Modules {
    const val core = ":core"
    const val coreDesignSystem = ":core:designsystem"
    const val coreCommon = ":core:common"
    const val data = ":data"
    const val dataDatabase = ":data:database"
    const val dataDatastore = ":data:datastore"
    const val domain = ":domain"
    const val featureHome = ":feature:home"
    const val featureCreateTask = ":feature:createTask"
}

object Versions {
    const val coreKtx = "1.9.0"
    const val appCompat = "1.6.1"
    const val lifecycle = "2.6.0"
    const val composeFoundation = "1.3.1"
    const val compose = "1.3.3"
    const val composeRuntime = "1.4.0"
    const val lifecicleCompose = "2.6.1"
    const val material3 = "1.0.1"
    const val corutines = "1.6.4"
    const val room = "2.5.0"
    const val datastorePrefs = "1.0.0"
    const val hiltNavigationCompose = "1.0.0"
    const val hilt = "2.44"
    const val systemUiController = "0.29.2-rc"
    const val splashScreen = "1.0.0"
    const val navigationCompose = "2.6.0-alpha08"
}

object Libs {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.appCompat}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecicleCompose}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.composeFoundation}"
    const val composeLayout = "androidx.compose.foundation:foundation-layout:${Versions.composeFoundation}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.composeRuntime}"
    const val composeUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val corutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.corutines}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val datastorePreferences = "androidx.datastore:datastore-preferences:${Versions.datastorePrefs}"
    const val hiltHavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}"
    const val splechScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
}