# Example sbt project that compiles using Scala 3

[![Build Status](https://travis-ci.com/scala/scala3-example-project.svg?branch=master)](https://travis-ci.com/scala/scala3-example-project)

## Usage

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Scala 3 REPL.

If compiling this example project fails, you probably have a global sbt plugin
that does not work with Scala 3, try to disable all plugins in
`~/.sbt/1.0/plugins` and `~/.sbt/1.0`.

### IDE support

Scala 3 comes built-in with IDE support, to try it out see
[IDE support for Scala 3](http://dotty.epfl.ch/docs/usage/ide-support.html)

## Making a new Scala 3 project

The fastest way to start a new Scala 3 project is to use one of the following templates:

* [Minimal Scala 3 project](https://github.com/scala/scala3.g8)
* [Scala 3 project that cross-compiles with Scala 2](https://github.com/scala/scala3-cross.g8)

## Using Scala 3 in an existing project

You will need to make the following adjustments to your build:

### project/plugins.sbt

```scala
addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.5.3")
```

Here, _dotty_ is the project name for technologies that are considered for inclusion in Scala 3.
Since the development of Scala 3 is not done, the word _dotty_ and _Dotty_ are still can be seen
in some names of Scala 3 related tools, sbt plugins, etc.

### project/build.properties

```scala
sbt.version=1.4.7
```

You must use sbt 1.4 or newer; older versions of sbt are not supported.

### build.sbt

Set up the Scala 3 version:

```scala
scalaVersion := "3.0.0-RC1"
```

#### Nightly builds

If the latest release of Scala 3 is missing a bugfix or feature you need, you may
wish to use a nightly build. Look at the bottom of the list of
[releases](https://repo1.maven.org/maven2/org/scala-lang/scala3-compiler_3.0.0-RC1/)
to find the version number for the latest nightly build. Alternatively, you can set
`scalaVersion := dottyLatestNightlyBuild.get` to always use the latest nightly build of Scala 3.

## Getting your project to compile with Scala 3

When porting an existing project, it's a good idea to start out with the Scala 2
compatibility mode (note that this mode affects typechecking and thus may
prevent some valid Scala 3 code from compiling) by adding to your `build.sbt`:

```scala
scalacOptions ++= { if (isDotty.value) Seq("-source:3.0-migration") else Nil }
```

Using the `isDotty` setting ensures that this option will only be set when
compiling with Scala 3. For more information on the `-source` flag, see
[language versions](http://dotty.epfl.ch/docs/usage/language-versions.html);
for more information on migrating to Scala 3, see
[the migration guide](https://github.com/scalacenter/scala-3-migration-guide).

If your build contains dependencies that have only been published for Scala 2.x,
you may be able to get them to work on Scala 3 by replacing:

```scala
    libraryDependencies += "a" %% "b" % "c"
```

by:

```scala
    libraryDependencies += ("a" %% "b" % "c").withDottyCompat(scalaVersion.value)
```

This will have no effect when compiling with Scala 2.x, but when compiling
with Scala 3 this will change the cross-version to a Scala 2.x one. This
works because Scala 3 is currently retro-compatible with Scala 2.x.

Alternatively, to set this setting on all your dependencies, you can use:

```scala
    libraryDependencies := libraryDependencies.value.map(_.withDottyCompat(scalaVersion.value))
```

## Discuss

Feel free to come chat with us on the
[Scala 3 gitter](http://gitter.im/lampepfl/dotty)!
