plugins {
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

apply {
    from("$rootDir/common-android-library-compose.gradle")
}

android {
    namespace = "com.example.yaroslavhorach.CreateTask"
}

dependencies {
    implementation(project(Modules.coreDesignSystem))
    implementation(Libs.hilt)
    implementation(Libs.hiltHavigationCompose)
    implementation(Libs.composeDialogsCalendar)
    implementation(Libs.composeDialogsCore)
    kapt(Libs.hiltCompiler)
}