plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    java
}

repositories {
    mavenCentral() // popular Maven artifacts
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jersey")  // RESTful Web service
    implementation("io.swagger.core.v3:swagger-jaxrs2:2.1.11") // OpenAPI design

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // JUnit
    implementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api") // test API
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // test engine implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.test {
    useJUnitPlatform()
}
