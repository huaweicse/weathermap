#!/bin/bash

# CMDVAR="-Djava.security.egd=file:/dev/./urandom","java -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n -jar"
JAR=weather-beta-2.0.0.jar
if [ ! -e $JAR ]; then
    JAR=target/$JAR
    if [ -e microservice.yaml ]; then
        cp microservice.yaml ./target/
    fi
fi
java $CMDVAR -jar ./$JAR
