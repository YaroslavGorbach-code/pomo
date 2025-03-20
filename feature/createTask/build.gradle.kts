plugins {
    id(Plugins.ksp)
    id(Plugins.hilt)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.compose)
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
    implementation(Libs.hiltNavigationCompose)
    implementation(Libs.composeDialogsCalendar)
    implementation(Libs.composeDialogsCore)
    ksp(Libs.hiltCompiler)
}