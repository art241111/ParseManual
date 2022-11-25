import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.gerasimov"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                // https://mvnrepository.com/artifact/net.sf.cssbox/pdf2dom
                implementation("net.sf.cssbox:pdf2dom:2.0.1")
                implementation("org.apache.pdfbox:pdfbox-tools:2.0.13")
                // https://mvnrepository.com/artifact/com.itextpdf/itextpdf
                implementation("com.itextpdf:itextpdf:5.5.13.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ParseManual"
            packageVersion = "1.0.0"
        }
    }
}
