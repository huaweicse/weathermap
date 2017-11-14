#!/bin/bash

#set JAVA_HOME
#JAVA_HOME=/opt/jdk1.8.0

#check JAVA_HOME & java
if [ -z `which java` ]; then
    echo `which java`
    if [ -z $JAVA_HOME ]; 
        then
            JAVA=$JAVA_HOME/bin/java
        else
            echo 'Cannot find java command and JAVA_HOME.'
    fi
else
    JAVA='java'
fi

#set JAVA_OPTS
#JAVA_OPTS="-server -Xms512m -Xmx512m -Xmn256m -Xss256k"
#JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n"

#check java version
if [ ! -z `java -version 2>&1 | grep 'java version' | awk '{print $3}' | egrep '1.8.\d*'` ]; then
    nohup $JAVA $JAVA_OPTS -jar ./forecastweatherdata-1.0.0.jar >/dev/null 2>&1 & 
    echo 'Started successfully, See more details in logs.'
else
    echo 'Java version must be 1.8+.'
fi