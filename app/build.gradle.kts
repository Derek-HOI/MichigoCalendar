plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt) apply true
}

android {
    namespace = "ai.derek.michigo"
    compileSdk = 35

    defaultConfig {
        applicationId = "ai.derek.michigo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.hilt.navigation.fragment)

    // When using Kotlin.
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.converter.gson)

    implementation(libs.glide.compose)

    // retrofit, okhttp3
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // define a BOM and its version
    implementation(platform(libs.okhttp.bom))
    // define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // data store
    implementation(libs.datastore.preferences)
    implementation(libs.datastore.preferences.core)

    // orbit
    implementation(libs.orbit.core)
    // or, if on Android:
    implementation(libs.orbit.viewmodel)
    // If using Jetpack Compose include
    implementation(libs.orbit.compose)
    // Tests
    testImplementation(libs.orbit.test)

    //jsoup
    implementation(libs.jsoup)

    // desugaring
    coreLibraryDesugaring(libs.desugar.jdk.libs)

}
hilt {
    enableAggregatingTask = false
}
