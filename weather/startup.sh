#!/bin/bash
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##

#set JAVA_HOME
#JAVA_HOME=/opt/jdk1.8.0

#check JAVA_HOME & java
JAVA='java'
if [ -z "`which java`" ]; then
    if [ -z "$JAVA_HOME" ]; then
        JAVA=$JAVA_HOME/bin/java
    else
        echo "Cannot find java command and JAVA_HOME"
    fi
fi

#set JAVA_OPTS
#JAVA_OPTS="-server -Xms512m -Xmx512m -Xmn256m -Xss256k"
#JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n"

#swith to home directory
cd `dirname "$0"`

#check java version
if [ ! -z `$JAVA -version 2>&1 | grep 'java version' | awk '{print $3}' | egrep '1.8.\d*'` ]; then
    eval $JAVA $JAVA_OPTS -jar ./weather-1.0.0.jar >/dev/null 2>&1 &
    echo "weather started successfully."
else
    echo 'Java version must be 1.8+.'
fi
