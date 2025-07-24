import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)



}
val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
android {
    namespace = "vcmsa.projects.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "vcmsa.projects.weatherapp"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "ACCUWEATHER_API_KEY",
            apikeyProperties["ACCUWEATHER_API_KEY"].toString())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildFeatures{
            buildConfig = true
            viewBinding = true
        }
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
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}