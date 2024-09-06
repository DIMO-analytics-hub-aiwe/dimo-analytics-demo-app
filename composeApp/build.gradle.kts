import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.20"
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            binaryOption(name = "bundleId", value = "com.aiweapps.dinsurance")
            isStatic = true
            export(libs.decompose)
            export(libs.decompose.essenty.lifecycle)
            export(libs.decompose.essenty.stateKeeper)
            export(libs.decompose.essenty.backHandler)
        }
    }

    sourceSets {
        val desktopMain by getting
        androidMain.dependencies {
            compileOnly(libs.google.errorproneannotations)
            implementation(libs.google.accompanist.permissions)
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.security.crypto)
            implementation(libs.androidx.browser)
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            api(libs.decompose)
            api(libs.decompose.essenty.lifecycle)
            api(libs.decompose.essenty.stateKeeper)
            api(libs.decompose.essenty.backHandler)
            api(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.datetime)
            implementation(libs.bundles.ktor)
            implementation(libs.ktor.client.content.negotiation)

            api(libs.compottie)
            api(libs.compottie.dot)
//            api(libs.compottie.network)
            api(libs.compottie.resources)

            api(libs.decompose)
            api(libs.decompose.extensions.compose)
            api(libs.decompose.essenty.lifecycle)
            api(libs.decompose.essenty.backHandler)
            api(libs.decompose.essenty.stateKeeper)
            api(libs.decompose.essenty.instanceKeeper)
            api(libs.kotlinx.serialization.json)
            api(libs.uuid)

            api(libs.koin.core)
            api(libs.koin.compose)

            api(libs.multiplatform.settings)
            api(libs.multiplatform.settings.coroutines)

            api(libs.haze)

        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            runtimeOnly(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.cio)
        }
    }
}

android {
    namespace = "com.aiweapps.dinsurance"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.aiweapps.dinsurance"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "com.aiweapps.dinsurance.MainKt"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb,
                TargetFormat.Exe,
                TargetFormat.Pkg
            )
            packageName = "DInsurance"
            packageVersion = "1.0.0"

            windows {
                packageVersion = "1.0.0"
                exePackageVersion = "1.0.0"
                msiPackageVersion = "1.0.0"
                menu = false
                upgradeUuid = "0c641664-2afe-42e4-8e4e-32a721339222"
                iconFile.set(projectDir.resolve("src/desktopMain/resources/ic_launcher.ico"))
            }

            linux {
                iconFile.set(projectDir.resolve("src/desktopMain/resources/ic_launcher.png"))
            }

            macOS {
                dmgPackageVersion = "1.0.0"
                dmgPackageBuildVersion = "1"
                bundleID = "com.aiweapps.dinsurance"
                appStore = false
                signing {
                    sign.set(false)
                }
                runtimeEntitlementsFile.set(projectDir.resolve("runtime-entitlements.plist"))
                infoPlist {
                    extraKeysRawXml = """
                    """.trimIndent()
                }
            }
        }

        buildTypes.release.proguard {
            configurationFiles.from(project.file("desktop-proguard-rules.pro"))
            isEnabled = true
            optimize = false
            obfuscate = false
        }
    }
}


tasks.withType<KotlinCompilationTask<*>>().all {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}
