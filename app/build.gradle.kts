plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.app.tap2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.tap2"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // 新增：支持自定义构建配置字段
        buildConfigField("String", "APP_NAME", "\"tap2\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // 新增：发布版签名配置（需提前准备签名文件）
            signingConfig = signingConfigs.getByName("debug") // 实际发布需替换为正式签名
        }
    }

    // 确保 Java 版本匹配
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // 约束布局
    implementation (libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.ui.test.android)
    implementation(libs.androidx.espresso.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


   // val room_version = "2.6.1"

    implementation(libs.androidx.room.room.runtime4)
    annotationProcessor(libs.androidx.room.room.compiler2)


    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.room.compiler2)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // optional - RxJava2 support for Room
    implementation(libs.room.rxjava2)

    // optional - RxJava3 support for Room
    implementation(libs.room.rxjava3)

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.room.guava)

    // optional - Test helpers
    testImplementation(libs.room.testing)

    // optional - Paging 3 Integration
    implementation(libs.room.paging)
    implementation(libs.androidx.core.splashscreen)
}
