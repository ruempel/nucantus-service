# Nucantus Karaoke Challenge Service

Nucantus organizes challenges at your karaoke party. First, the party organizer distributes a participation link.
Players may browse a song list and challenge a song by typing in their player name. Other players see open challenges
and accept them by joining a song with their player name. A challenge view lists all accepted challenges and tells you,
which song to start next in your karaoke software.

This service provides your song list and lets you manage challenges using a REST API. The API is consumed by
the [Nucantus App](https://github.com/ruempel/nucantus-app).

![Gradle workflow status](https://github.com/ruempel/nucantus-service/actions/workflows/gradle.yml/badge.svg)

## How to Create a Song List

First, a file with a list of song directories is required. The directory names must be newline-separated. Each
directory's name must have the syntax `ARTIST - TITLE`. The file is created by navigating to your song directory and
then executing one of the following shell scripts:

- Powershell: `Get-ChildItem -Name -Include "* - *" > songs.txt`
- GNU/bash: `ls -A1 | grep " - " > songs.txt`

Afterwards, copy the songs file to `src/main/resources/` of your project.

## How to Build

Challenges are held in-memory and thus do not outlast JVM termination. You need a song list, which can be generated from
the song database of your karaoke software such as:

- [Vocaluxe](https://github.com/Vocaluxe/Vocaluxe),
- [Performous](https://github.com/performous/performous) or
- [UltraStar Play](https://github.com/UltraStar-Deluxe/Play)

The project comes with a Gradle wrapper, you do not need to have Gradle or a JDK installed on you computer.

Build the application and create the Java archive (JAR) with dependencies: `gradlew clean build`

## How to Run

- run `gradlew bootRun` or
- run `java -jar build/libs/nucantus.jar` after building

The API is available at `http://localhost:8080/api/`.

You may also create a Docker image using `gradlew bootBuildImage` and then run the API using the Docker container at
your favorite port.

## Browse API

Use [Swagger UI](https://swagger.io/tools/swagger-ui/) to browse the Nucantus API:

```
docker run -d -p 8081:8080 -e URL=http://localhost:8080/api/openapi.json --name swagger-ui swaggerapi/swagger-ui
```

Swagger UI is available at `http://localhost:8081`.
