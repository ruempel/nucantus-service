plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    java
}

repositories {
    mavenCentral() // popular Maven artifacts
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jersey")  // REST-ful Web service
    implementation("io.swagger.core.v3:swagger-jaxrs2:2.2.2") // OpenAPI design

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // JUnit
    implementation(platform("org.junit:junit-bom:5.9.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-api") // test API
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // test engine implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks.test {
    useJUnitPlatform()
}
