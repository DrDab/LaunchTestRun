# LaunchTestRun
A platform that lets you submit and test code snippets online for practice.

# Dependencies
LaunchTestRun depends on the following software to run:
- Apache Tomcat 8.5.33 or higher
- Java 1.8 or higher
- GCC 5 or higher
- Python 2
- Python 3
- Mono .NET

To install these packages on Ubuntu or Debian, please run the following commands:

To install the required toolchains, run
```
sudo apt-get update
sudo apt-get install default-jre default-jdk build-essential python2 python3 mono-devel
```

# Installation
1. If you would like to build from source, continue to step 2. Otherwise, copy the .WAR file in ``release/`` to the ``webapps/`` directory in your Apache Tomcat server (if you used the previous steps Tomcat should be installed in ~/LaunchTestRun), and skip to step 5.
2. Import this as an Eclipse Dynamic Web Project.
3. Export the project into a .WAR named ``LaunchTestRun.war``.
4. Move the .WAR file into your Apache Tomcat server, in the ``webapps/`` directory.
5. Replace ``bin/catalina.sh`` in your Apache Tomcat installation with the ``catalina.sh`` in this directory (Patches issues with Java 9 and Tomcat 8).
6. Make the shell scripts in the ``bin/`` directory in your Tomcat installation executable by running ``chmod +x *.sh`` in the ``bin/`` directory of your Tomcat installation..
7. Run ``bin/startup.sh`` in your Tomcat installation directory to start LaunchTestRun.

### Securing your setup
If you would like more security on your LaunchTestRun installation (strongly recommended as others will be running code on your server!), you should create a new user that only has permissions to run and access the files within the Tomcat installation directory. To secure your setup, after running ``bin/startup.sh`` the first time after moving the .WAR file, run the following commands (replace all text in brackets with locations applicable to your specific installation) :
```
su root
chmod 755 [ YOUR TOMCAT DIRECTORY HERE ] -R
cd [ YOUR TOMCAT DIRECTORY HERE ]
useradd launchtestrun
mkdir work
touch webapps/LaunchTestRun/forensics.txt
chown launchtestrun:launchtestrun -R work
chown launchtestrun:launchtestrun -R logs
chown launchtestrun:launchtestrun -R webapps/LaunchTestRun
chown launchtestrun:launchtestrun -R webapps/LaunchTestRun/upload
chown launchtestrun:launchtestrun -R webapps/LaunchTestRun/forensics.txt
```

After setting these permissions, when starting the LaunchTestRun server, switch to the ``launchtestrun`` user before running ``startup.sh`` with the following commands:
```
su launchtestrun
cd [ YOUR TOMCAT DIRECTORY HERE ]/bin
./startup.sh
```

To view a forensic log of all the files uploaded and executed (containing IP, input, output, MD5 hash, time uploaded, etc), view ``webapps/LaunchTestRun/forensics.txt``. 

# Setup
NOTE: It is recommended to initalize a git repository in ``webapps/LaunchTestRun/problems`` so that problems can be remotely uploaded, edited and reverted if needed.
1. If you haven't installed the LaunchTestRun server before reading this step, please refer back to Running and follow the instructions.
2. To add a custom problem, go into your Apache Tomcat server directory, and go to ``webapps/LaunchTestRun/problems`` inside your Apache Tomcat directory. Create a new directory with the following structure:
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
	"pdf":"http://www.pdfsite.org/files/test.pdf",
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
- ``pdf`` is the URL link to the PDF file that you want to be displayed with the problem.
- ``setinfo`` is the information about the problem set that the problem you want to define comes from.
- ``samplein`` is the name of the sample input file. (The input given to the user-uploaded program)
- ``sampleout`` is the name of the sample output file. (The output expected for the input)
- ``judgein`` is the name of the judge input file. (The input given to the user-uploaded program)
- ``judgeout`` is the name of the judge output file. (The output expected for the input)
- ``inputname`` is the name of the input file that the user-uploaded program will read, that the sample/judge input file will be renamed to.
- ``timeout`` is an integer defining the number of milliseconds that the program will be alloted to execute before a forced termination.

3. To set the locations of the compilers, you can edit ``webapps/LaunchTestRun/settings.json``, which contains the paths that LaunchTestRun will look for the compilers in.

# Using
The LaunchTestRun server can be accessed by default from: ``http://127.0.0.1:8080/LaunchTestRun/index.html/`` after it is configured with Tomcat in the previous sections.
A list of problems added can be accessed by default from: ``http://127.0.0.1:8080/LaunchTestRun/listproblems.html/``.
