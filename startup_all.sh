#!/bin/bash
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##

# cp microservice credentials
cp microservice.yaml ./weather/
cp microservice.yaml ./weather-beta/
cp microservice.yaml ./forecast/
cp microservice.yaml ./fusionweather/

# start all services
echo "start weather"
./weather/startup.sh

echo "start weather-beta"
./weather-beta/startup.sh

echo "start forecast"
./forecast/startup.sh

echo "start fusionweather"
./fusionweather/startup.sh

echo "start weathermapweb"
./weathermapweb/startup.sh