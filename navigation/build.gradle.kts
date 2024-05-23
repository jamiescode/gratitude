plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jmailen.kotlinter")
}

android {
    namespace = "uk.co.jamiecruwys.navigation"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.bundles.compose)

    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5)
}