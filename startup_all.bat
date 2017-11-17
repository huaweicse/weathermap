@echo off
REM
REM Copyright@2017 Huawei Technologies Co., Ltd. 
REM

echo "start weather"
./weather/startup.bat

echo "start weather-beta"
./weather-beta/startup.bat

echo "start forecast"
./forecast/startup.bat

echo "start fusionweather"
./fusionweather/startup.bat

echo "start weathermapweb"
./weathermapweb/startup.bat