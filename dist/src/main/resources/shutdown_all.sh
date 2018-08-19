#!/bin/bash
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##

echo "stop weather"
./weather/shutdown.sh

echo "stop weather-beta"
./weather-beta/shutdown.sh

echo "stop forecast"
./forecast/shutdown.sh

echo "stop fusionweather"
./fusionweather/shutdown.sh

echo "stop weathermapweb"
./weathermapweb/shutdown.sh
