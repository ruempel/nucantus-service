# About
Nucantus is a Web-based tool to organize challenges at your karaoke party. First, the party organizer distributes a participation link. Players may browse a song list and challenge a song by typing in their player name. Other players see open challenges and accept them by joining a song with their player name. An admin view lists all accepted challenges and tells you, which song to start next in your karaoke software.

# How to Use
The Maven project comprises a RESTful Web service to store challenge data and some static Web resources for user interaction. Challenges do not outlast JVM termination. You need a song list, which can be generated from the song database of your karaoke software such as Vocaluxe.

* Build requirements: JDK 8, Maven
* Runtime requirements: JRE 8

## Create a Song List
First, a file with a list of song directories is required. The directory names must be newline-separated. Each directory's name must have the syntax ```ARTIST - TITLE```. The file is created by navigating to your song directory and then executing one of the following shell scripts:

* Powershell: ```Get-ChildItem -Name -Include "* - *" > songs.txt```
* GNU/bash: ```ls -A1 | grep " - " > songs.txt```

Afterwards, copy the songs file to ```src/main/resources/``` of your Maven project.

## Configure, Build and Run Server
Configure Web service base URI for target hostname:

* ```baseURI``` at ```src/main/resources/config.js``` and
* ```BASE_URI``` at ```src/main/java/de/nucantus/Main.java``` (optional, defaults to ```http://0.0.0.0:5026/rest/```)

Create the JAR with dependencies: ```mvn clean package```

Copy the created JAR from the ```target``` directory of the Maven project to your target host and run: ```java -jar nucantus-x.y.z-jar-with-dependencies.jar``` 

## Challenge and Accept
The challenge view is available to your participants at ```http://localhost:5026``` or according to your URI configuration. The admin view displaying accepted challenges is available at ```http://localhost:5026/admin.html```.
