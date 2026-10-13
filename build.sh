#!/bin/bash
# Usage: ./build.sh /path/to/tomcat/lib/servlet-api.jar
SERVLET_JAR=${1:-$CATALINA_HOME/lib/servlet-api.jar}
mkdir -p web/WEB-INF/classes
javac -cp "$SERVLET_JAR" -d web/WEB-INF/classes $(find src -name "*.java") && \
(cd web && jar -cvf ../OrderApp.war .) && echo "Created OrderApp.war - copy it to Tomcat's webapps folder"
