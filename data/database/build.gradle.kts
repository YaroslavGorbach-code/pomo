plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

apply {
    from("$rootDir/common-android-library.gradle")
}

android{
    namespace = "com.example.yaroslavhorach.database"
}

dependencies {
    implementation(Libs.corutines)
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    annotationProcessor(Libs.roomCompiler)
    kapt(Libs.roomCompiler)
}