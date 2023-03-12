plugins {
    id(Plugins.application)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.hiltAndroid)
    kotlin(Plugins.serialization) version Versions.Dependencies.kotlin
}

android {

    namespace = BuildConfig.applicationId
    compileSdk = Versions.App.compileSdk

    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdk = Versions.App.minSdk
        targetSdk = Versions.App.targetSdk
        versionCode = Versions.App.code
        versionName = Versions.App.name

        testInstrumentationRunner = BuildConfig.testInstrumentationRunner

        buildConfigField("String", "API_HOST", "\"${BuildConfig.apiHost}\"")
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = Versions.Other.compiler
        targetCompatibility = Versions.Other.compiler
    }

    java {
        sourceCompatibility = Versions.Other.compiler
        targetCompatibility = Versions.Other.compiler
    }

    kotlinOptions {
        jvmTarget = Versions.Other.compiler.toString()
        freeCompilerArgs = freeCompilerArgs + BuildConfig.optInApis.map { "-opt-in=$it" }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Dependencies.composeCompiler
    }
}

dependencies {

    // Hilt
    implementation(Dependencies.Google.hiltAndroid)
    kapt(Dependencies.Google.hiltCompiler)

    // Compose
    val composeBom = platform(Dependencies.AndroidX.Compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Dependencies.AndroidX.Compose.material3)
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.uiToolingPreview)
    implementation(Dependencies.AndroidX.Compose.activity)
    implementation(Dependencies.AndroidX.Compose.lifecycleRuntime)
    implementation(Dependencies.AndroidX.Compose.navigation)
    implementation(Dependencies.AndroidX.Compose.hiltNavigation)
    debugImplementation(Dependencies.AndroidX.Compose.uiTooling)

    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.accompanistPermissions)
    implementation(Dependencies.Google.accompanistSystemIiController)

    // Ktor
    implementation(Dependencies.Ktor.clientCore)
    implementation(Dependencies.Ktor.clientAndroid)
    implementation(Dependencies.Ktor.clientLogging)
    implementation(Dependencies.Ktor.clientContentNegotiation)
    implementation(Dependencies.Ktor.serialization)

    // Others
    implementation(Dependencies.Jetbrains.kotlinxSerialization)

    // Desugar
    coreLibraryDesugaring(Dependencies.Android.desugarJdkLibs)

    // Tests
    testImplementation(Dependencies.AndroidX.jUnit)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}