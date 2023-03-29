plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

apply {
    from("$rootDir/common-android-library.gradle")
}

android {
    namespace = "com.example.yaroslavhorach.designsystem"
}

dependencies {
    api(Libs.coreKtx)
    api(Libs.appCompat)
    api(Libs.composeFoundation)
    api(Libs.composeLayout)
    api(Libs.composeRuntime)
    api(Libs.composeUtil)
    api(Libs.composeMaterial3)
    api(Libs.composePreview)
    api(Libs.navigationCompose)
    api(Libs.hiltHavigationCompose)
}