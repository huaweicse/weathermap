#!/bin/bash

# CMDVAR="-Djava.security.egd=file:/dev/./urandom","java -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n -jar"
java $CMDVAR -jar ./forecastweatherdata-1.0.0.jar