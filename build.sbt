lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-example-project",
    description := "Example sbt project that compiles using Dotty",
    version := "0.1.0",

    scalaVersion := "3.0.0-M1",

    useScala3doc := true,
  )
