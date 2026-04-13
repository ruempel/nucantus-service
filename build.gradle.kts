plugins {
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
    java
}

repositories {
    mavenCentral() // popular Maven artifacts
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jersey")  // RESTful Web service
    implementation("io.swagger.core.v3:swagger-jaxrs2-jakarta:2.2.22") // OpenAPI design

    // reduce boilerplate code with Lombok, versions managed by spring.io
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // test dependencies
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api") // test API
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") // test engine implementation
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Spring Boot test annotations
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(26))
    }
}

tasks.test {
    useJUnitPlatform()
}
