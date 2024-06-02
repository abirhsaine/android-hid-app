plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "fr.enssat.bluetoothhid.Zakaria_Abir_Ferhat"
    compileSdk = 34

    defaultConfig {
        applicationId = "fr.enssat.bluetoothhid.Zakaria_Abir_Ferhat"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
     }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    buildFeatures {
        compose = true
    }

}
dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.20.0")

    implementation("androidx.compose.ui:ui:1.4.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.1")

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.1")

    implementation("br.com.devsrsouza.compose.icons:feather:1.1.0")

    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("com.google.firebase:firebase-inappmessaging:20.4.0")

    implementation("androidx.fragment:fragment:1.3.6")



    implementation("androidx.compose.material:material-icons-core:1.5.4")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation("androidx.compose.material:material:1.0.0")
    implementation("androidx.compose.material3:material3:1.1.2")

}
