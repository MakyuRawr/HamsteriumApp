pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()

        maven{
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create <BasicAuthentication>("basic")
            }

            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                val MAPBOX_DOWNLOADS_TOKEN: String by settings
                password = MAPBOX_DOWNLOADS_TOKEN
            }
        }

    }
}

rootProject.name = "Hamsterium"
include(":app")
