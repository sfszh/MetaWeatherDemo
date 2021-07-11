
package co.ruizhang.buildsrc

object Versions {
    const val ktlint = "0.41.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-beta05"

    object Accompanist {
        const val version = "0.13.0"
        const val coil = "com.google.accompanist:accompanist-coil:$version"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
    }

    object Arch {
        private const val version = "1.1.1"
        const val testing = "android.arch.core:core-testing:$version"
    }

    object Kotlin {
        private const val version = "1.5.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.5.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Room {
        private const val version = "2.3.0"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val testing = "androidx.room:room-testing:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object Mockito {
        const val core = "org.mockito:mockito-core:1.10.19"
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:3.2.0"
    }

    object MockK {
        const val mockk = "io.mockk:mockk:1.11.0"
    }

    object Kotlinx {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.2.1"
    }

    object Hilt {
        private const val version = "2.37"
        private const val miscVersion = "1.0.0-alpha03"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$miscVersion"
        const val compiler = "androidx.hilt:hilt-compiler:$miscVersion"
        const val test = "com.google.dagger:hilt-android-testing:$version"
        const val composeNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha02"
    }
    object Napier {
        private const val version = "1.5.0"
        const val  napier = "io.github.aakira:napier:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha04"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha07"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.0.0-beta09"

            const val animation = "androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
            const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Lifecycle {
            private const val version = "2.4.0-alpha02"
            const val runTime = "androidx.lifecycle:lifecycle-runtime:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val extensions= "androidx.lifecycle:lifecycle-extensions:2.2.0"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"

        }

        object ConstraintLayout {
            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha05"
        }
        object DataStore {
            private const val version =  "1.0.0-beta01"
            const val preferences = "androidx.datastore:datastore-preferences:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }


            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }
}