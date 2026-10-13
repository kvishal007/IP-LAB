#!/bin/bash
# Usage: ./build.sh /path/to/tomcat/lib/servlet-api.jar
SERVLET_JAR=${1:-$CATALINA_HOME/lib/servlet-api.jar}
mkdir -p web/WEB-INF/classes
javac -cp "$SERVLET_JAR" -d web/WEB-INF/classes src/com/lab/*.java && \
(cd web && jar -cvf ../SessionDemo.war .) && echo "Created SessionDemo.war - copy it to Tomcat's webapps folder"
