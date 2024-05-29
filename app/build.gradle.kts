plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.unisagrado.appcompcrianca"
    compileSdk = 34

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }

    defaultConfig {
        applicationId = "com.unisagrado.appcompcrianca"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }



}

dependencies {
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    testImplementation("androidx.arch.core:core-testing:2.1.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.12")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("org.mockito:mockito-core:3.11.2")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.google.firebase:firebase-database:21.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}}