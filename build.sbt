lazy val root = (project in file(".")).
  settings(
    name := "dotty-example-project",
    description := "Example sbt project that compiles using Dotty",
    version := "0.1",

    // All the settings set below this line are important to get your project
    // to compile with Dotty. Please read the comments carefully.

    // Dotty version
    scalaVersion := "0.1.1-bin-SNAPSHOT",
    scalaBinaryVersion := "0.1",
    scalaOrganization := "ch.epfl.lamp",

    // Needed because of https://github.com/sbt/sbt/issues/3012
    resolvers += Resolver.typesafeIvyRepo("releases")

    // Enable Scala 2 compatibility mode.
    // This allows you to use Scala 2 features that have been removed
    // from Dotty, like procedure syntax, thus making it easier to test
    // Dotty on an existing Scala 2 code base.
    // A rewriting tool that can do the porting for you is currently in
    // development at https://github.com/scalacenter/scalafix
    // Note that this affects typechecking and thus may prevent some valid
    // Dotty code from compiling, so it is not enabled by default.
    // scalacOptions ++= Seq("-language:Scala2")
  )
