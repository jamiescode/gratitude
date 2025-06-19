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
    namespace = "com.jamiescode.showcase.dog"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":navigation"))

    implementation(libs.kotlin)
    implementation(libs.timber)
    implementation(libs.glide)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.compose)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.bundles.test.runtime)

    androidTestImplementation(libs.bundles.uitest)
    kaptAndroidTest(libs.hilt.compiler)
}