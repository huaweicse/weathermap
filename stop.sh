#!/bin/sh

stop()
{
    curPath=`pwd`
    shutdownAllPath="$curPath"/shutdown_all.sh
    if [ ! -f $shutdownAllPath ];then
        exit 1
    fi
    bash $shutdownAllPath
    rst=$?
    if [  $rst != 0 ];then
        echo "shutdown_all.sh not exist, please check your pkg"
        exit $rst
    fi
    echo "stop weather successfully"
}

stop
