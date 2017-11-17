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

REM swith to home directory
cd %~dp0

REM startup erver
%NPM% install & %NPM% start

:end