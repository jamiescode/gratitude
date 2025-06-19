plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kover)
}

android {
    namespace = "com.jamiescode.showcase.navigation"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.bundles.compose)
    implementation(libs.oss.licenses)
    implementation(libs.chrome.custom.tabs)

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.bundles.test.runtime)
    androidTestImplementation(libs.bundles.uitest)
    kaptAndroidTest(libs.hilt.compiler)
}