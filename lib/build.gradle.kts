plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    alias(libs.plugins.kspPlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

//kotlin {
//    jvmToolchain {
//        languageVersion.set(JavaLanguageVersion.of("17"))
//    }
//}

dependencies {
    implementation(libs.kotlininjectRuntime)
    ksp(libs.kotlininjectCompiler)
}