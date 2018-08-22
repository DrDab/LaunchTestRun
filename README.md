# LaunchTestRun
A platform that lets you submit and test code snippets online for practice.

# Running
1. If you would like to build from source, continue to step 2. Otherwise, copy the .WAR file in ``release/`` to the ``webapps/`` directory in your Apache Tomcat server, and skip to step 5.
2. Import this as an Eclipse Dynamic Web Project.
3. Export the project into a .WAR.
4. Move the .WAR file into your Apache Tomcat server, in the ``webapps/`` directory.
5. In ``bin/catalina.sh``, remove the following lines:
```
JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=java.base/java.lang=ALL-UNNAMED"
JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=java.base/java.io=ALL-UNNAMED"
JDK_JAVA_OPTIONS="$JDK_JAVA_OPTIONS --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED"
export JDK_JAVA_OPTIONS
```
6. Run ``bin/startup.sh``.
