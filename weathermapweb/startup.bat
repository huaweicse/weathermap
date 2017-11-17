@echo off
REM
REM Copyright@2017 Huawei Technologies Co., Ltd. 
REM

REM check NODE_HOME & npm
set NPM=npm
where %NPM%
if %errorlevel% == 0 goto HAS_NODE

if not "%NODE_HOME%" == "" goto HAS_NODE_HOME
echo Cannot find npm command and NODE_HOME
goto end

:HAS_NODE_HOME
echo HAS_NODE_HOME
set NPM="%NODE_HOME%\npm"

:HAS_NODE
echo HAS_NODE
REM set 
REM NODE_OPTS="-server -Xms512m -Xmx512m -Xmn256m -Xss256k"
REM NODE_OPTS="$NODE_OPTS -agentlib:jdwp=transport=dt_socket,address=0:8000,server=y,suspend=n"

REM swith to home directory
cd %~dp0

REM startup erver
start "weathermapweb" %NPM% install & %NPM% start

:end