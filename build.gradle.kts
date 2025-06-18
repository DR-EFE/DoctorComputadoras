// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // Añade el plugin de Compose
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20" apply false
}

// Añade esta configuración para las versiones
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.20")
    }
}