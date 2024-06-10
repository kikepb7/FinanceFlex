plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    // Firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.enriquepalmadev.financeflex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.enriquepalmadev.financeflex"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    val liveDataVersion = "2.7.0"
    val fragmentVersion = "1.6.2"
    val navVersion = "2.7.7"
    val retrofitVersion = "2.10.0"
    val interceptorVersion = "4.12.0"
    val hiltVersion = "2.51"
    val hiltCompiler = "1.2.0"
    val extendedIcons = "1.6.7"
    val openCsv = "5.5.2"
    val room = "2.6.1"
    val mockkVersion = "1.13.11"
    val coroutinesTestVersion = "1.6.4"
    val archCoreTestVersion = "2.2.0"
    val turbineVersion = "1.0.0"
    val jUnitVersion = "4.13.2"
    val jUnitTestVersion = "1.1.5"
    val espressoVersion = "3.5.1"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.play.services.auth)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // LIVE DATA
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$liveDataVersion")

    // FRAGMENT
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    // NAVIGATION
    implementation("androidx.navigation:navigation-compose:${navVersion}")

    // VIEWMODEL
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.7")
    implementation("androidx.activity:activity-compose:1.6.0-alpha01")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")

    // COIL
    implementation("io.coil-kt:coil-compose:2.6.0")

    // RETROFIT & MOSHI
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    // INTERCEPTOR
    implementation("com.squareup.okhttp3:logging-interceptor:$interceptorVersion")

    // HILT
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltCompiler")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kapt("androidx.hilt:hilt-compiler:$hiltCompiler")

    // MOSHI
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // EXTENDED ICONS
    implementation("androidx.compose.material:material-icons-extended:${extendedIcons}")

    // OPENCSV
    implementation("com.opencsv:opencsv:$openCsv")

    // ROOM
    implementation("androidx.room:room-runtime:$room")
    kapt("androidx.room:room-compiler:$room")

    // KOTLIN EXTENSIONS AND COROUTINES SUPPORT FOR ROOM
    implementation("androidx.room:room-ktx:$room")

    // FIREBASE BOM
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")

    // SPLASH
    implementation("androidx.core:core-splashscreen:1.0.1")

    // BIOMETRIC
    implementation("androidx.biometric:biometric:1.1.0")

    // TEST
    testImplementation("junit:junit:$jUnitVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion")
    testImplementation("androidx.arch.core:core-testing:$archCoreTestVersion")
    testImplementation("app.cash.turbine:turbine:$turbineVersion")

    androidTestImplementation("androidx.test.ext:junit:$jUnitTestVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")

}