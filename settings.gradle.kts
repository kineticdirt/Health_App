pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        versionCatalogs {
//            create("libs") {
//                // Define your dependencies here
//                // Example:
//                // alias("core-ktx").to("androidx.core:core-ktx:1.9.0")
//            }
//        }
        flatDir {
            dirs("app/libs") // For some Reason, both are needed??? I guess its due to the multiple Libs directories????
            dirs("libs")
        }
    }
}


rootProject.name = "Health_APP"
include(":app")
