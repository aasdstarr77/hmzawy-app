plugins {
 id("com.android.application")
 id("org.jetbrains.kotlin.android")
 id("org.jetbrains.kotlin.plugin.compose")
 id("com.google.devtools.ksp")
 id("com.google.dagger.hilt.android")
}
android {
 namespace = "com.hamzawysat.app"
 compileSdk = 35
 defaultConfig { applicationId = "com.hamzawysat.app"; minSdk = 26; targetSdk = 35; versionCode = 1; versionName = "1.0.0"; buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"") }
 buildFeatures { compose = true; buildConfig = true }
 compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
 kotlinOptions { jvmTarget = "17" }
}
dependencies {
 implementation(libs.androidx.core.ktx); implementation(libs.androidx.activity.compose); implementation(libs.androidx.lifecycle.runtime)
 implementation(platform(libs.androidx.compose.bom)); implementation(libs.androidx.compose.ui); implementation(libs.androidx.compose.preview); implementation(libs.androidx.compose.material3)
 implementation(libs.androidx.room.runtime); implementation(libs.androidx.room.ktx); ksp(libs.androidx.room.compiler)
 implementation(libs.retrofit); implementation(libs.retrofit.gson); implementation(libs.okhttp); implementation(libs.okhttp.logging)
 implementation(libs.hilt.android); ksp(libs.hilt.compiler); implementation(libs.androidx.work); implementation(libs.androidx.security.crypto)
 debugImplementation(libs.androidx.compose.tooling)
}
