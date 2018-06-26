@echo off

goto checkjdkversion

:checkjdkversion
java -version >javaversion.txt 2>&1
findstr "'java'" javaversion.txt >nul
if %errorlevel% equ 0 (
  del javaversion.txt
  goto installjre8
)
for /f tokens^=2delims^=^" %%i in (javaversion.txt) do set currentversion=%%i
echo %currentversion%>currentsdkversion.txt
for /f "tokens=1-3 delims=." %%a in (currentsdkversion.txt) do (
  set jdkversion1=%%a
  set jdkversion2=%%b
  set jdkversion3=%%c
)
del javaversion.txt
del currentsdkversion.txt
if %jdkversion1% equ 10 (
  echo 本机安装jre版本10高于8，安装8后需要手动切换jre版本，按任意键继续安装
  pause
  goto installjre8
)
if %jdkversion2% equ 9 (
  echo 本机安装jre版本9高于8，安装8后需要手动切换jre版本，按任意键继续安装
  pause
  goto installjre8
)
if %jdkversion2% equ 8 (
  goto checknodejs
)
goto installjre8

:checknodejs
node -v >nodeversion.txt 2>&1
findstr "'node'" nodeversion.txt >nul
if %errorlevel% equ 0 (
  del nodeversion.txt
  goto installnodejs
)
for /f "tokens=1-3 delims=." %%a in ('node -v') do (
  set nodejsversion1=%%a
  set nodejsversion2=%%b
  set nodejsversion3=%%c
)
del nodeversion.txt
set nodejsversion1=%nodejsversion1:~1,1%
if %nodejsversion1% lss 4 (
  goto installnodejs
) 
if %nodejsversion1% equ 4 (
  if %nodejsversion2% lss 8 (
    goto installnodejs
  )
)
if %nodejsversion1% equ 4 (
  if %nodejsversion2% equ 8 (
    if %nodejsversion3% lss 4 (
	  goto installnodejs
	)
  )
) 
goto startup_all

:installjre8
set myjrepath="C:\Program Files\Java\jre1.8.0_172"
echo **********************************************
echo.
echo             将要安装jre1.8.0_172
echo.
echo       安装请按任意键，退出直接关闭窗口
echo.
echo **********************************************
pause
echo.
echo 正在安装jre，请在弹出的安装窗口进行操作，一路点击next即可
echo.
echo 请稍等，这个时间大约需要一分钟
echo.
start /w %cd%\pkg\jre-8u172-windows-x64.exe INSTALLDIR=%myjrepath%
if %errorlevel% neq 0 (
  echo 安装jre8失败，请重新安装或手动安装
  pause
  exit
)
echo jre8安装成功
set PATH=C:\Program Files\Java\jre1.8.0_172\bin;%PATH%
set RegV=HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment
reg add "%RegV%" /v "Path" /t REG_EXPAND_SZ /d "%PATH%" /f
echo jre环境变量配置完毕
goto checknodejs

:installnodejs
set mynodejspath="C:\Program Files\nodejs"
echo **********************************************
echo.
echo             将要安装nodejs8.11.3
echo.
echo       安装请按任意键，退出直接关闭窗口
echo.
echo **********************************************
pause
echo.
echo 正在安装nodejs，请在弹出的安装窗口进行操作，一路点击next即可
echo.
echo 请稍等，这个时间大约需要一分钟
echo.
start /w %cd%\pkg\node-v8.11.3-x64.msi INSTALLDIR=%mynodejspath%
if %errorlevel% neq 0 (
  echo 安装nodejs失败，请重新安装或手动安装
  pause
  exit
)
echo nodejs安装完毕
set PATH=C:\Program Files\nodejs\;%PATH%
set RegV=HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment
reg add "%RegV%" /v "Path" /t REG_EXPAND_SZ /d "%PATH%" /f
echo nodejs环境变量配置完毕
goto startup_all

:startup_all
echo **************************************************
echo.
echo 将要启动天气预报，启动请按任意键，退出直接关闭窗口
echo.
echo **************************************************
pause
start startup_all.bat
exit

exit