plugins {
    application // create and package executable JVM application
    id("com.github.johnrengelman.shadow") version "7.1.0" // create fat JAR with dependencies
}

repositories {
    mavenCentral() // popular Maven artifacts
}

dependencies {
    // Jersey
    implementation(platform("org.glassfish.jersey:jersey-bom:3.0.3")) // Jersey dependency management
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-servlet") // servlet and HTTP server
    runtimeOnly("org.glassfish.jersey.inject:jersey-hk2") // dependency injection
    runtimeOnly("org.glassfish.jersey.media:jersey-media-json-jackson") // JSON conversion

    // JUnit
    implementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api") // test API
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // test engine implementation

    // misc
    implementation("io.swagger.core.v3:swagger-jaxrs2-jakarta:2.1.11") // API annotations
    implementation("ch.qos.logback:logback-classic:1.2.6") // logger
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("de.nucantus.NucantusApplication") // FQN of the application's main class
}
