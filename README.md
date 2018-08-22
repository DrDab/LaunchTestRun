# LaunchTestRun
A platform that lets you submit and test code snippets online for practice.

# Running
1. Import this as an Eclipse Dynamic Web Project.
2. Export the project into a .WAR.
3. Move the .WAR file into your Apache Tomcat server, in the ``webapps/`` directory.
4. In ``bin/catalina.sh``, remove the following lines:
```
JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=java.base/java.lang=ALL-UNNAMED"
JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=java.base/java.io=ALL-UNNAMED"
JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED"
export JDK_JAVA_OPTIONS
```
5. Run ``bin/startup.sh``.
