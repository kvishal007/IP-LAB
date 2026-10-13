@echo off
REM Usage: build.bat "C:\apache-tomcat-9.0.xx\lib\servlet-api.jar"
if not exist web\WEB-INF\classes mkdir web\WEB-INF\classes
dir /s /b src\*.java > sources.txt
javac -cp %1 -d web\WEB-INF\classes @sources.txt
del sources.txt
cd web
jar -cvf ..\OrderApp.war .
cd ..
echo Created OrderApp.war - copy it to Tomcat's webapps folder
