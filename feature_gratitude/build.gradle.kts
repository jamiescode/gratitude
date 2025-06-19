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
    namespace = "com.jamiescode.showcase.gratitude"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":theme"))
    implementation(project(":feature_quote"))

    implementation(libs.kotlin)
    implementation(libs.timber)
    implementation(libs.bundles.compose)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.bundles.test)
    testImplementation(libs.room.testing)
    testRuntimeOnly(libs.bundles.test.runtime)

    androidTestImplementation(libs.bundles.uitest)
    kaptAndroidTest(libs.hilt.compiler)
}