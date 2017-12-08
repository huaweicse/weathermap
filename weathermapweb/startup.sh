#!/bin/bash
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##

#set NODE_HOME
#NODE_HOME=/opt/node-v4.8.4

#check NODE_HOME & npm
NPM='npm'
if [ -z "`which npm`" ]; then
    echo "`which npm`"
    if [ -z $NODE_HOME ]; then
            NPM=$NODE_HOME/bin/npm
        else
            echo "Cannot find npm command and NODE_HOME."
    fi
fi

#swith to work directory
cd `dirname "$0"`

#check node version
if [[ $(echo "`node --version 2>&1` v4.8.4" | tr " " "\n" | sort -V | head -n 1) == "v4.8.4" ]]; then
    npm install
    npm start >../logs/weathermapweb.log 2>&1 &
    echo "weathermapweb started successfully."
else
    echo 'Node version must be 4.8.4+.'
fi
