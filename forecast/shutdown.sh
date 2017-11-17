#!/bin/sh
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##

#set JAVA_HOME
#JAVA_HOME=/opt/jdk1.8.0

#check JAVA_HOME & jps
JPS='jps'
if [ -z "`which jps`" ]; then
    echo "`which jps`"
    if [ -z "$JAVA_HOME" ]; 
        then
            JPS=$JAVA_HOME/bin/jps
        else
            echo "Cannot find jps command and JAVA_HOME."
    fi
fi

#stop Server
$JPS |grep forecast|awk -F ' ' '{print $1}'|while read line
do
  eval "kill -9 $line"
done