plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.android.gms.oss-licenses-plugin")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kover)
}

android {
    namespace = "com.jamiescode.showcase"
    defaultConfig {
        applicationId = "com.jamiescode.showcase"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.compile.get().toInt()
        compileSdk = libs.versions.sdk.compile.get().toInt()
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":feature_gratitude"))
    implementation(project(":feature_settings"))
    implementation(project(":feature_under_construction"))
    implementation(project(":navigation"))
    implementation(project(":theme"))

    implementation(libs.kotlin)
    implementation(libs.appcompat)
    implementation(libs.startup)
    implementation(libs.timber)
    implementation(libs.bundles.compose)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    debugImplementation(libs.leakcanary)

    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.bundles.test.runtime)

    androidTestImplementation(libs.bundles.uitest)
    kaptAndroidTest(libs.hilt.compiler)
}