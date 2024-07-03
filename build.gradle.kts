plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    java
}

repositories {
    mavenCentral() // popular Maven artifacts
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jersey")  // REST-ful Web service
    implementation("io.swagger.core.v3:swagger-jaxrs2-jakarta:2.2.22") // OpenAPI design

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // JUnit
    implementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api") // test API
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // test engine implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

tasks.test {
    useJUnitPlatform()
}
