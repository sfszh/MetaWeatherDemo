import co.ruizhang.buildsrc.Libs

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "co.ruizhang.metaweatherdemo"
        minSdk = 24
        targetSdk = 30
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
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
        kotlinCompilerVersion = "1.4.32"
    }
}
dependencies {

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.android)

    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.androidCompiler)
    kapt(Libs.Hilt.compiler)
    implementation(Libs.Hilt.viewModel)
    implementation(Libs.Hilt.composeNavigation)

    implementation(Libs.Kotlinx.datetime)
    
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.navigation)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.ConstraintLayout.constraintLayoutCompose)

    //region compose
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.viewModel)
    implementation(Libs.AndroidX.Compose.liveData)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.iconsExtended)
    implementation(Libs.AndroidX.Compose.tooling)
    //endregion

    implementation(Libs.AndroidX.Lifecycle.liveDataKtx)
    implementation(Libs.AndroidX.Lifecycle.runTime)
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)
    implementation(Libs.AndroidX.Lifecycle.extensions)
    implementation(Libs.AndroidX.DataStore.preferences)

    implementation(Libs.Accompanist.coil)
    implementation(Libs.Accompanist.insets)

    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.gson)

    //region room
    implementation(Libs.Room.runtime)
    annotationProcessor(Libs.Room.compiler)
    kapt(Libs.Room.compiler)
    implementation(Libs.Room.ktx)
    //endregion

    //region Test
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.MockK.mockk)
    testImplementation(Libs.Coroutines.test)
    testImplementation(Libs.Hilt.test)
    kaptTest(Libs.Hilt.test)
    testImplementation(Libs.Arch.testing)

    androidTestImplementation(Libs.AndroidX.Activity.activityCompose)
    androidTestImplementation(Libs.AndroidX.Test.core)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
    androidTestImplementation(Libs.Room.testing)
    //endregion
}