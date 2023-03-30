plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

apply {
    from("$rootDir/common-android-library.gradle")
}

android{
    namespace = "com.example.yaroslavhorach.common"
}

dependencies {
    implementation(Libs.coreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.viewModel)
}