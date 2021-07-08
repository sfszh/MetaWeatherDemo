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
        kotlinCompilerExtensionVersion = co.ruizhang.buildsrc.Libs.AndroidX.Compose.version
        kotlinCompilerVersion = "1.4.32"
    }
}
dependencies {

    implementation(co.ruizhang.buildsrc.Libs.Kotlin.stdlib)
    implementation(co.ruizhang.buildsrc.Libs.Coroutines.android)

    implementation(co.ruizhang.buildsrc.Libs.Hilt.hilt)
    kapt(co.ruizhang.buildsrc.Libs.Hilt.androidCompiler)
    kapt(co.ruizhang.buildsrc.Libs.Hilt.compiler)
    implementation(co.ruizhang.buildsrc.Libs.Hilt.viewModel)
    implementation(co.ruizhang.buildsrc.Libs.Hilt.composeNavigation)

    implementation(co.ruizhang.buildsrc.Libs.AndroidX.coreKtx)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.navigation)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Activity.activityCompose)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.ConstraintLayout.constraintLayoutCompose)

    //region compose
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.runtime)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.foundation)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.layout)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.ui)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.uiUtil)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.viewModel)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.liveData)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.material)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.animation)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.iconsExtended)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.tooling)
    //endregion

    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Lifecycle.liveDataKtx)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Lifecycle.runTime)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Lifecycle.viewModelKtx)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.Lifecycle.extensions)
    implementation(co.ruizhang.buildsrc.Libs.AndroidX.DataStore.preferences)

    implementation(co.ruizhang.buildsrc.Libs.Accompanist.coil)
    implementation(co.ruizhang.buildsrc.Libs.Accompanist.insets)

    implementation(co.ruizhang.buildsrc.Libs.Retrofit.retrofit)
    implementation(co.ruizhang.buildsrc.Libs.Retrofit.gson)

    //region room
    implementation(co.ruizhang.buildsrc.Libs.Room.runtime)
    annotationProcessor(co.ruizhang.buildsrc.Libs.Room.compiler)
    kapt(co.ruizhang.buildsrc.Libs.Room.compiler)
    implementation(co.ruizhang.buildsrc.Libs.Room.ktx)
    //endregion

    //region Test
    testImplementation(co.ruizhang.buildsrc.Libs.JUnit.junit)
    testImplementation(co.ruizhang.buildsrc.Libs.MockK.mockk)
    testImplementation(co.ruizhang.buildsrc.Libs.Coroutines.test)
    testImplementation(co.ruizhang.buildsrc.Libs.Hilt.test)
    kaptTest(co.ruizhang.buildsrc.Libs.Hilt.test)
    testImplementation(co.ruizhang.buildsrc.Libs.Arch.testing)

    androidTestImplementation(co.ruizhang.buildsrc.Libs.AndroidX.Activity.activityCompose)
    androidTestImplementation(co.ruizhang.buildsrc.Libs.AndroidX.Test.core)
    androidTestImplementation(co.ruizhang.buildsrc.Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(co.ruizhang.buildsrc.Libs.AndroidX.Test.rules)
    androidTestImplementation(co.ruizhang.buildsrc.Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(co.ruizhang.buildsrc.Libs.AndroidX.Compose.uiTest)
    androidTestImplementation(co.ruizhang.buildsrc.Libs.Room.testing)
    //endregion
}