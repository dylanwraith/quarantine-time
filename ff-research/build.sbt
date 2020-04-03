import com.github.kxbmap.sbt.jooq.CodegenStrategy.Always

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

// Enable the plugin
enablePlugins(JooqCodegen)

// Add your database driver dependency to `jooq` scope
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.30.1"

//jOOQ version
jooqVersion := "3.13.1"

jooqOrganization := "org.jooq"

// Automatically add jOOQ dependencies
autoJooqLibrary := true

// Set filepath for codegen.XML
jooqCodegenConfig := baseDirectory.value / "src/library.xml"

// Always execute code generation. Can change to IfAbsent or Never
jooqCodegenStrategy := CodegenStrategy.IfAbsent


