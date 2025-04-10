plugins {
    kotlin("jvm") version "2.0.21"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.aallam.openai:openai-client:3.8.2")
    implementation("io.ktor:ktor-client-core:2.3.2")
    implementation("io.ktor:ktor-client-cio:2.3.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
