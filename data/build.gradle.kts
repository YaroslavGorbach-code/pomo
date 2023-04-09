plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
}

apply {
    from("$rootDir/common-android-library.gradle")
}

android{
    namespace = "com.example.yaroslavhorach.data"
}

dependencies {
    api(project(Modules.coreDesignSystem))
    implementation(project(Modules.dataDatabase))
    implementation(project(Modules.dataDatastore))
    implementation(Libs.corutines)
    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)
}