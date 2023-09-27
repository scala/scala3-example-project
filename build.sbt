lazy val root = project
  .in(file("."))
  .settings(
    name := "apolo",
    description := "Music manager written in dotty",
    version := "0.1.0",
    scalaVersion := "3.3.1",
    scalacOptions ++= Seq("-deprecation"),
    libraryDependencies ++= Seq(
        "org.scalameta" %% "munit" % "0.7.29" % Test,
        "org.typelevel" %% "cats-core" % "2.9.0",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
        "ch.qos.logback" % "logback-classic" % "1.2.10")
  )
