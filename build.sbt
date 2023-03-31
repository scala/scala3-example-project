lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-example-project",
    description := "Example sbt project that compiles using Scala 3",
    version := "0.1.0",
    scalaVersion := "3.2.2",
    scalacOptions ++= Seq("-deprecation"),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.2.15"
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.15" % "test"
    resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
  )
