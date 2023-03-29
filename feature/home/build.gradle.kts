plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

apply {
    from("$rootDir/common-android-library.gradle")
}

android {
    namespace = "com.example.yaroslavhorach.home"
}

dependencies {
    implementation(project(Modules.coreDesignSystem))

}