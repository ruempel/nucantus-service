package de.nucantus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Runs Spring Boot application.
 */
@SpringBootApplication
class NucantusApplication

fun main(args: Array<String>) {
    runApplication<NucantusApplication>(*args)
}
