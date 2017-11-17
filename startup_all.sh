#!/bin/bash
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##
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