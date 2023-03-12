plugins {
    id(Plugins.application) version Versions.Dependencies.androidGradlePlugin apply false
    id(Plugins.androidTest) version Versions.Dependencies.androidGradlePlugin apply false
    id(Plugins.jetBrainsKotlinAndroid) version Versions.Dependencies.kotlin apply false
    id(Plugins.hiltAndroid) version Versions.Dependencies.hilt apply false
}
