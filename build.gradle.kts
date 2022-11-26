import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    java
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
}

repositories {
    mavenCentral() // popular Maven artifacts
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jersey")  // REST-ful Web service
    implementation("io.swagger.core.v3:swagger-jaxrs2-jakarta:2.2.7") // OpenAPI design

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // JUnit
    implementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api") // test API
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // test engine implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib-jdk8"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks.test {
    useJUnitPlatform()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "18"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "18"
}
