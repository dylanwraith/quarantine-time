#### This is a project created utilizing Play, sbt, lombok, jOOQ, and Flyway for backend technologies, and React, React-Bootstrap, React-Redux, React-Router, Redux-Saga, and Immutable.JS for frontend technologies.
  
  
<p align="center" background-color="black">
  <img src="./ff-research/public/images/play.png">
  <img src="./ff-research/public/images/add.png">
  <img src="./ff-research/public/images/react.png">
  <img src="./ff-research/public/images/add.png">
  <img src="./ff-research/public/images/jooq.png">
</p>
  
  
***NOTE: Play Framework is currently compatible with Java 8 and Java 11. Due to unknown potential issues with compatibility of Java 11 and other used technologies, use Java 8 rather than Java 11. As of now, Java 8 is the most used version of Java, with Java 11 the only other version of Java with long-term support.***  
  
**Java 8 Setup:**  
Download and install Java 8 jdk from https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html  
Add file to 'C:/Program Files/Java'  
Configure system environment variables 'PATH' and 'JAVA_HOME' to include file added to 'C:/Program Files/Java/bin'  
Enter 'java -version' in command prompt to ensure installation is performed successfully  
More information on setting up Java can be found here:  
  
**sbt Setup:**  
Download and install using installation wizard from https://piccolo.link/sbt-1.3.4.msi  
More information on setting up sbt can be found here: https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Windows.html  
  
**Play Framework Setup (Built-in to sbt):**  
Open command prompt and enter the following command in desired directory of project  :
```
'sbt new playframework/play-java-seed.g8'
```
More information on setting up Play Framework can be found here: https://www.playframework.com/documentation/2.8.x/NewApplication  
  
**Lombok Setup:**  
Download .jar from https://projectlombok.org/download and add to project path  
More information on setting up Lombok can be found here: https://projectlombok.org/  
  
**SQLite Setup:**  
Download precompiled binaries from https://www.sqlite.org/download.html  
Unzip files, then add sqlite3.def, sqlite3.dll and sqlite3.exe files to 'C:/Program Files/sqlite'  
Configure system environment variables 'PATH' to include file added to 'C:/Program Files/sqlite/bin'  
More information on setting up SQLite can be found here: https://www.tutorialspoint.com/sqlite/sqlite_installation.htm  
  
**jOOQ Setup:**  
Download jOOQ files from http://www.jooq.org/download  
Download SQLite JDBC (Java Database Connector) .jar file from https://bitbucket.org/xerial/sqlite-jdbc/downloads/  
Create SQLite database. Example using command line here: https://sqlite.org/cli.html  
Create .XML file for code generation. Example for SQLite database in sample-jooq-setup/library.xml  
More information on configuring .XML can be found here: https://www.jooq.org/doc/3.13/manual/code-generation/  
Drag jOOQ .jar files, JDBC .jar file, and .XML file into a temporary directory, then enter following command sequence in the command prompt(may need to change variables based on filed names/locations):  
  
```
java -classpath jooq-3.13.1.jar;^
jooq-meta-3.13.1.jar;^
jooq-codegen-3.13.1.jar;^
reactive-streams-1.0.2.jar;^
sqlite-jdbc-3.30.1.jar;. ^
org.jooq.codegen.GenerationTool library.xml
```
  
***NOTE: That 5th command has '. ' after the semi-colon! Do not forget that, or you will receive an unfriendly error that is difficult to debug!***  
  
Finally, add jOOQ .jar files to project path to use within project.  
More information on setting up jOOQ can be found here: https://www.jooq.org/doc/latest/manual/getting-started/tutorials/jooq-in-7-steps/

**Fylway Setup:**  
Add the following line to the end of plugins.sbt (found in ff-research/project):  
```
addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "6.2.3")
```
Create the directory 'resources/db/migration' within ff-research.  
Place database file in db directory.  
Add the following lines to the end of build.sbt(found in ff-research), be sure to update database name:  
```  
enablePlugins(FlywayPlugin)
version := "0.0.1"
name := "flyway-sbt-test1"

libraryDependencies += "org.hsqldb" % "hsqldb" % "2.5.0"

flywayUrl := "jdbc:sqlite:resources/db/library.db"
flywayLocations += "filesystem:resources/db/migration"
```
  
Create SQL files, then add them to migration folder after naming them using this template: **V#__name_of_migration.sql**  
Using the command prompt at the main project directory, enter following command to execute migrations:  
```
sbt flywayMigrate
```  
  
Recompile jOOQ classes using following command:
```
java -classpath jooq-3.13.1.jar;^
jooq-meta-3.13.1.jar;^
jooq-codegen-3.13.1.jar;^
reactive-streams-1.0.2.jar;^
sqlite-jdbc-3.30.1.jar;. ^
org.jooq.codegen.GenerationTool library.xml
```  
More information on the jOOQ sbt plugin can be found here: https://github.com/flyway/flyway-sbt

**sbt Automation with IntelliJ IDEA Setup:**
Create src directory within main project, and place codegen.XML file inside.  
Create lib directory within main project, and place all [dependency].jar files inside.  
Add dependency .jar files to IntelliJ Path Variables:  
  
1. Go to File->Settings in IntelliJ IDE, then type Path Variables in the search box.  
  
2. Click '+' to add dependencies one by one. Name each dependency, then enter filepath as value.  
    * jOOQ files needed: jooq.jar, jooq-codegen.jar, jooq-meta.jar, reactive-stream.jar, sqlite-jdbc.jar  
    * Lombok files needed: lombok.jar  
  
Install jOOQ sbt plugin:  
  
1. Add this line of code to the end of plugins.sbt:  
```
addSbtPlugin("com.github.kxbmap" % "sbt-jooq" % "0.4.1")
```   
  
2. Add these lines of code to the end of build.sbt:  
```
enablePlugins(JooqCodegen)
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.30.1"
jooqVersion := "3.13.1"
jooqOrganization := "org.jooq"
autoJooqLibrary := true
jooqCodegenConfig := baseDirectory.value / "src/library.xml"
jooqCodegenStrategy := CodegenStrategy.IfAbsent
```  
  
More information on the jOOQ sbt plugin can be found here: https://github.com/kxbmap/sbt-jooq  
  
Create a run configuration:  
  
1. Go to Run->Edit Configurations within the IntelliJ IDE, then click '+' to create a new run configuration.  
  
2. Select sbt task as your configuration type.  
  
3. Rename task, then enter the following inside of Tasks textbox:  
```
flywayMigrate; jooqCodegen; run;
```  
  
4. Uncheck the box next to 'Use sbt shell', then click Apply and close the window.  

5. Click the play button in the top right of IntelliJ IDE to run project.  
