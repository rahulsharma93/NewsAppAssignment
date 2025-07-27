plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

apply(plugin = "kotlin-kapt")

android {
    namespace = "com.news.assignment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.news.assignment"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            buildConfigField("String", "API_BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"dcd2a46c4e9045c4b2377ec5a82ecfa6\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "API_BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"dcd2a46c4e9045c4b2377ec5a82ecfa6\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableAggregatingTask = false
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
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
    implementation (libs.androidx.viewmodel.ktx)
    implementation (libs.androidx.livedata.ktx)
    implementation (libs.okhttp)
    implementation (libs.okhttp.logging.interceptor)
    implementation (libs.retrofit)
    implementation (libs.retrofit.converter.gson)
    implementation (libs.androidx.cardview)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.paging.compose)
    testImplementation(libs.androidx.paging.common)
    testImplementation(libs.androidx.paging.testing)
    implementation(libs.androidx.material)
    implementation (libs.material)
    implementation(libs.hilt.android)
    implementation (libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    testImplementation (libs.junit)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.coroutines.test)
    testImplementation (libs.mockito.kotlin.legacy)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.assertj.android)
    testImplementation (libs.androidx.annotation)
    testImplementation (libs.arch.core.testing)
    implementation (libs.kotlin.test)
}