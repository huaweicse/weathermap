@echo off
REM
REM Copyright@2017 Huawei Technologies Co., Ltd. 
REM

REM check JAVA_HOME & java
set JAVA="java.exe"
where %JAVA%
if %errorlevel% == 0 goto HAS_JAVA

if not "%JAVA_HOME%" == "" goto HAS_JAVA_HOME
echo Cannot find java command and JAVA_HOME
goto end

:HAS_JAVA_HOME
echo HAS_JAVA_HOME
set JAVA="%JAVA_HOME%\bin\java.exe"

:HAS_JAVA
REM set 
REM JAVA_OPTS="-server -Xms512m -Xmx512m -Xmn256m -Xss256k"
REM JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n"

REM swith to home directory
set JAR="forecast-1.0.0.jar"
cd %~dp0
if not exist %JAR% (
    if exist microservice.yaml (
        copy microservice.yaml .\target\
    )
    cd .\target\
)

REM startup erver
set "RUN_CMD="%JAVA%""
set "RUN_CMD=%RUN_CMD% %JAVA_OPTS%"
set "RUN_CMD=%RUN_CMD% -jar ./%JAR%"
call %RUN_CMD%

:end