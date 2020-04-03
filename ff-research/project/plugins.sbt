// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.0")

// Defines scaffolding (found under .g8 folder)
// http://www.foundweekends.org/giter8/scaffolding.html
// sbt "g8Scaffold form"
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.11.0")
addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "6.2.3")

addSbtPlugin("com.github.kxbmap" % "sbt-jooq" % "0.4.1")