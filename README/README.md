#### This is a project created utilizing Play, sbt, lombok, jOOQ, and Flyway for backend technologies, and React, React-Bootstrap, React-Redux, React-Router, Redux-Saga, and Immutable.JS for frontend technologies.
  
  
![Play Logo](play.png) ![React Logo](react.png) 
  
  
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
