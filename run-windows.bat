@echo off
setlocal
cd /d "%~dp0"
if not exist "out" mkdir "out"
javac -encoding UTF-8 -d out src\main\java\com\example\game2048\*.java
if errorlevel 1 (
  echo Java compile failed. Check JDK 21 and PATH.
  pause
  exit /b 1
)
java -cp out com.example.game2048.Main
if errorlevel 1 pause
endlocal
