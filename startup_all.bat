@echo off
REM
REM Copyright@2017 Huawei Technologies Co., Ltd. 
REM

REM read system 
if not "%ak%" == "" goto microservice
if not "%sk%" == "" goto microservice

if not exist "credentials" goto nocredentials

REM read user credentials
for /f "tokens=1,2 delims==" %%i in (credentials) do (
  set %%i=%%j
)

REM prepare cse microservice.yaml
REM cse:
REM  credentials:
REM    accessKey: UZLI6CLENEO6D2ANV35U
REM    secretKey: nAQ7jQyynhuDckGGtzNYMJGDZoFqaEfNwhadIXXP
REM    akskCustomCipher: default
:microservice
echo cse: > microservice.yaml
echo   credentials: >> microservice.yaml
echo     accessKey: %ak% >> microservice.yaml
echo     secretKey: %sk% >> microservice.yaml
echo     akskCustomCipher: default >> microservice.yaml

copy microservice.yaml .\weather\
copy microservice.yaml .\weather-beta\
copy microservice.yaml .\forecast\
copy microservice.yaml .\fusionweather\
del microservice.yaml

if not exist "httpproxy.properties" goto nohttpproxy

REM read httpproxy settings
for /f "tokens=1,2 delims==" %%i in (httpproxy.properties) do (
  set %%i=%%j
)



:nohttpproxy
if not "%ak%" == "" goto microservice


REM mklink with all service
if not exist "weather\lib" mklink /J weather\lib lib
if not exist "weather-beta\lib" mklink /J weather-beta\lib lib
if not exist "forecast\lib" mklink /J forecast\lib lib
if not exist "fusionweather\lib" mklink /J fusionweather\lib lib

REM start all service
echo "start weather"
start weather\startup.bat

echo "start weather-beta"
start weather-beta\startup.bat

echo "start forecast"
start forecast\startup.bat

echo "start fusionweather"
start fusionweather\startup.bat

echo "start weathermapweb"
start weathermapweb\startup.bat

goto end

:nocredentials
echo "Cannot find ak/sk in credentials file or system environment variables."


:end