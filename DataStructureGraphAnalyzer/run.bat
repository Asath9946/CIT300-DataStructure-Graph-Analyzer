@echo off
REM Compile and run the Data Structure ^& Graph Performance Analyzer
REM Usage: double-click run.bat, or type run.bat in Command Prompt

echo ============================================================
echo  Compiling Data Structure ^& Graph Performance Analyzer...
echo ============================================================

if not exist build mkdir build
dir /s /B src\*.java > sources.txt
javac -d build @sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation FAILED. Check the errors above.
    del sources.txt
    pause
    exit /b 1
)

del sources.txt
echo Compilation successful.
echo.

java -cp build com.sltc.dsa.Main
pause
