@echo off
REM Usage: build.bat "C:\apache-tomcat-9.0.xx\lib\servlet-api.jar"
if not exist web\WEB-INF\classes mkdir web\WEB-INF\classes
javac -cp %1 -d web\WEB-INF\classes src\com\lab\*.java
cd web
jar -cvf ..\SessionDemo.war .
cd ..
echo Created SessionDemo.war - copy it to Tomcat's webapps folder
