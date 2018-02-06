#!/bin/sh

monitor() {
    ps -ef |grep "weathermapweb" | grep -v grep
    if [ $? != 0 ];then
        return 1
    fi
    return $?
}
monitor
