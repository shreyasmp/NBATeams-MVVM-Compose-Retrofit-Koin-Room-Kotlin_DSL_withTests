import org.gradle.kotlin.dsl.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.shreyasmp.nbateams"
    compileSdk = AppConfig.compileSDK

    defaultConfig {
        applicationId = "com.shreyasmp.nbateams"
        minSdk = AppConfig.minSDK
        targetSdk = AppConfig.targetSDK
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
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
    kotlinOptions {
        jvmTarget = "17"
        javaParameters = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    dataBinding {
        //noinspection DataBindingWithoutKapt
        android.buildFeatures.dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
    kapt {
        generateStubs = true
        correctErrorTypes = true
        javacOptions {
            option("-Adagger.fastInit=enabled")
        }
    }
    lint {
        warningsAsErrors = true
        abortOnError = true
    }
    sourceSets {
        getByName("main").java.srcDir("src/main/java")
        getByName("test").java.srcDirs("src/test/java")
        getByName("test").resources.srcDirs("src/test/assets")
    }

    // To avoid annotations from both jetbrains and intelliJ from colliding.
    configurations.forEach { it.exclude("com.intellij", "annotations") }
}

tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(AppDependencies.kotlinStdLib)
    implementation(AppDependencies.kotlinCoRoutinesCore)
    implementation(AppDependencies.jakeWhartonCoRoutineAdapter)
    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.material)
    implementation(AppDependencies.coreKtx)

    kapt(AppDependencies.lifeCycleCompiler)
    implementation(AppDependencies.lifeCycleRunTime)
    implementation(AppDependencies.lifeCycleExtensions)
    implementation(AppDependencies.lifeCycleKTX)
    implementation(AppDependencies.lifeCycleLiveDataKTX)

    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.okhttp3Interceptor)
    implementation(AppDependencies.gsonConverter)
    implementation(AppDependencies.coil)
    implementation(AppDependencies.coilCompose)
    implementation(AppDependencies.coilSvg)
    implementation(AppDependencies.gson)

    implementation(AppDependencies.koin)
    implementation(AppDependencies.koinCompose)
    implementation(AppDependencies.koinCompat)
    implementation(AppDependencies.koinWorkManager)

    annotationProcessor(AppDependencies.roomRuntime)
    kapt(AppDependencies.roomCompiler)
    implementation(AppDependencies.roomCoRoutine)

    implementation(AppDependencies.composeActivities)
    implementation(AppDependencies.composeMaterialDesign)
    implementation(AppDependencies.composeAnimations)
    implementation(AppDependencies.composeTooling)
    implementation(AppDependencies.composeToolingPreview)
    implementation(AppDependencies.composeViewModels)
    implementation(AppDependencies.composeNavigation)
    implementation(AppDependencies.composeLiveData)
    implementation(AppDependencies.composeUiAndroidTests)

    testImplementation(AppDependencies.kotlinCoRoutinesCoreTest)
    testImplementation(AppDependencies.junit)
    testImplementation(AppDependencies.robolectric)
    testImplementation(AppDependencies.robolectricShadows)
    testImplementation(AppDependencies.robolectricSupport)
    testImplementation(AppDependencies.archCoreTesting)
    testImplementation(AppDependencies.junitRunner)
    testImplementation(AppDependencies.mockito)
    testImplementation(AppDependencies.hamcrest)
    testImplementation(AppDependencies.truth)
    testImplementation(AppDependencies.mockk)
    testImplementation(AppDependencies.mockkAndroid)
    testImplementation(AppDependencies.nharmaanMockito)
    testImplementation(AppDependencies.okHttpMockServer)
    testImplementation(AppDependencies.koinTest)
    testImplementation(AppDependencies.koinTestJUnit4)
    testImplementation(AppDependencies.roomTesting)

    androidTestImplementation(AppDependencies.androidXTestRunner)
    androidTestImplementation(AppDependencies.androidxTestRules)
    androidTestImplementation(AppDependencies.extJUnit)
    androidTestImplementation(AppDependencies.espressoCore)
    androidTestImplementation(AppDependencies.espressoContrib)
    androidTestImplementation(AppDependencies.mockito)
    androidTestImplementation(AppDependencies.mockk)
    androidTestImplementation(AppDependencies.mockkAndroid)
    androidTestImplementation(AppDependencies.navigationTesting)
    androidTestImplementation(AppDependencies.composeUITests)

    debugImplementation(AppDependencies.fragmentTesting)
    debugImplementation(AppDependencies.composeTooling)
    debugImplementation(AppDependencies.composeUIManifest)
}