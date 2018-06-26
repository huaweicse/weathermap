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
  echo ������װjre�汾10����8����װ8����Ҫ�ֶ��л�jre�汾���������������װ
  pause
  goto installjre8
)
if %jdkversion2% equ 9 (
  echo ������װjre�汾9����8����װ8����Ҫ�ֶ��л�jre�汾���������������װ
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
echo             ��Ҫ��װjre1.8.0_172
echo.
echo       ��װ�밴��������˳�ֱ�ӹرմ���
echo.
echo **********************************************
pause
echo.
echo ���ڰ�װjre�����ڵ����İ�װ���ڽ��в�����һ·���next����
echo.
echo ���Եȣ����ʱ���Լ��Ҫһ����
echo.
start /w %cd%\pkg\jre-8u172-windows-x64.exe INSTALLDIR=%myjrepath%
if %errorlevel% neq 0 (
  echo ��װjre8ʧ�ܣ������°�װ���ֶ���װ
  pause
  exit
)
echo jre8��װ�ɹ�
set PATH=C:\Program Files\Java\jre1.8.0_172\bin;%PATH%
set RegV=HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment
reg add "%RegV%" /v "Path" /t REG_EXPAND_SZ /d "%PATH%" /f
echo jre���������������
goto checknodejs

:installnodejs
set mynodejspath="C:\Program Files\nodejs"
echo **********************************************
echo.
echo             ��Ҫ��װnodejs8.11.3
echo.
echo       ��װ�밴��������˳�ֱ�ӹرմ���
echo.
echo **********************************************
pause
echo.
echo ���ڰ�װnodejs�����ڵ����İ�װ���ڽ��в�����һ·���next����
echo.
echo ���Եȣ����ʱ���Լ��Ҫһ����
echo.
start /w %cd%\pkg\node-v8.11.3-x64.msi INSTALLDIR=%mynodejspath%
if %errorlevel% neq 0 (
  echo ��װnodejsʧ�ܣ������°�װ���ֶ���װ
  pause
  exit
)
echo nodejs��װ���
set PATH=C:\Program Files\nodejs\;%PATH%
set RegV=HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment
reg add "%RegV%" /v "Path" /t REG_EXPAND_SZ /d "%PATH%" /f
echo nodejs���������������
goto startup_all

:startup_all
echo **************************************************
echo.
echo ��Ҫ��������Ԥ���������밴��������˳�ֱ�ӹرմ���
echo.
echo **************************************************
pause
start startup_all.bat
exit

exit