name := """ff-research"""
organization := "dylanwraith"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
    .enablePlugins(PlayJava)

scalaVersion := "2.13.1"

libraryDependencies += guice

enablePlugins(FlywayPlugin)
version := "0.0.1"
name := "flyway-sbt-test1"

libraryDependencies += "org.hsqldb" % "hsqldb" % "2.5.0"

flywayUrl := "jdbc:sqlite:resources/db/library.db"
flywayLocations += "filesystem:resources/db/migration"
flywayUrl in Test := "jdbc:sqlite:resources/db/library.db"
