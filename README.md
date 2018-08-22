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

# Setup
1. If you haven't installed the LaunchTestRun server before reading this step, please refer back to Running and follow the instructions.
2. To add a custom problem, go into your Apache Tomcat server directory, and go to ``webapps/LaunchTestRun/problems`` inside your server directory. Create a new directory with the following structure:
```
[+] Name of problem
 |- config.json
 |- judge.in
 |- judge.out
 |- sample.in
 |- sample.out
```
``config.json`` should contain the following data:
```
{
	"cpid":"12345",
	"title":"Sample Tile 1",
	"description":"Test Problem Description",
	"setinfo":"Fall Competition 2019",
	"samplein":"sample.in",
	"sampleout":"sample.out",
	"judgein":"judge.in",
	"judgeout":"judge.out",
	"inputname":"input.txt",
	"timeout":"2000"
}
```
Where:
- ``cpid`` is the ID # for the problem you want to define.
- ``title`` is the title for the problem you want to define.
- ``description`` is the description for the problem you want to define.
- ``setinfo`` is the information about the problem set that the problem you want to define comes from.
- ``samplein`` is the name of the sample input file. (The input given to the user-uploaded program)
- ``sampleout`` is the name of the sample output file. (The output expected for the input)
- ``judgein`` is the name of the judge input file. (The input given to the user-uploaded program)
- ``judgeout`` is the name of the judge output file. (The output expected for the input)
- ``inputname`` is the name of the input file that the user-uploaded program will read, that the sample/judge input file will be renamed to.
- ``timeout`` is an integer defining the number of milliseconds that the program will be alloted to execute before a forced termination.

3. To set the locations of the compilers, you can edit ``webapps/LaunchTestRun/settings.json``, which contains the paths that LaunchTestRun will look for the compilers in.
