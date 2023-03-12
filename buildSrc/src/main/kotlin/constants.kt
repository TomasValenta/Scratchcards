import org.gradle.api.JavaVersion

object BuildConfig {

    const val applicationId = "cz.yav.scratchcards"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val apiHost = "api.o2.sk"

    val optInApis = listOf(
        "androidx.compose.ui.ExperimentalComposeUiApi",
        "com.google.accompanist.permissions.ExperimentalPermissionsApi",
    )
}

object Versions {

    object App {

        const val compileSdk = 33
        const val minSdk = 21
        const val targetSdk = compileSdk

        const val code = 1
        const val name = "1.0.0"

    }

    object Dependencies {
        const val kotlin = "1.8.10"
        const val androidGradlePlugin = "7.4.2"

        const val composeBom = "2023.01.00"
        const val composeCompiler = "1.4.3"
        const val activityCompose = "1.6.1"
        const val navigationCompose = "2.5.3"
        const val hiltNavigationCompose = "1.0.0"
        const val accompanist = "0.28.0"
        const val lifecycle = "2.6.0-rc01"

        const val material = "1.8.0"
        const val hilt = "2.45"
        const val ktor = "2.2.3"
        const val kotlinxSerialization = "1.4.1"
        const val desugarJdkLibs = "2.0.2"

        const val jUnit = "4.13.2"
    }

    object Other {
        val compiler = JavaVersion.VERSION_11
    }
}

object Dependencies {

    object Jetbrains {
        const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Dependencies.kotlinxSerialization}"
    }

    object Android {
        const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.Dependencies.desugarJdkLibs}"
    }

    object AndroidX {

        object Compose {
            const val bom = "androidx.compose:compose-bom:${Versions.Dependencies.composeBom}"
            const val material3 = "androidx.compose.material3:material3"
            const val ui = "androidx.compose.ui:ui"
            const val uiTooling = "androidx.compose.ui:ui-tooling"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val activity = "androidx.activity:activity-compose:${Versions.Dependencies.activityCompose}"
            const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.Dependencies.lifecycle}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.Dependencies.navigationCompose}"
            const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.Dependencies.hiltNavigationCompose}"
        }

        const val jUnit = "junit:junit:${Versions.Dependencies.jUnit}"
    }

    object Google {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.Dependencies.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.Dependencies.hilt}"
        const val material = "com.google.android.material:material:${Versions.Dependencies.material}"
        const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:${Versions.Dependencies.accompanist}"
        const val accompanistSystemIiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.Dependencies.accompanist}"
    }

    object Ktor {
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.Dependencies.ktor}"
        const val clientCore = "io.ktor:ktor-client-core:${Versions.Dependencies.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.Dependencies.ktor}"
        const val clientLogging = "io.ktor:ktor-client-logging:${Versions.Dependencies.ktor}"
        const val clientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.Dependencies.ktor}"
    }
}

object Plugins {
    const val application = "com.android.application"
    const val android = "android"
    const val kapt = "kapt"
    const val serialization = "plugin.serialization"
    const val hiltAndroid = "com.google.dagger.hilt.android"
    const val jetBrainsKotlinAndroid = "org.jetbrains.kotlin.android"
    const val androidTest = "com.android.test"
}
