# Example sbt Project that Compiles Using Dotty

I (Mike Slinn) fixed many significant bugs in the [upstream project](https://github.com/lampepfl/dotty-example-project) and added code examples for new major features that were missing.

## Usage

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Dotty REPL.

You can run just one example from the `sbt console`, for example we can run `Typeclasses.test`:

```
$ sbt console
scala> Typeclasses.test
sum("a", "b", "c"): abc
```

If compiling this example project fails, you probably have a global sbt plugin
that does not work with dotty, try to disable all plugins in
`~/.sbt/1.0/plugins` and `~/.sbt/1.0`.

### IDE Support

> Dotty comes built-in with IDE support, to try it out see
http://dotty.epfl.ch/docs/usage/ide-support.html

Meh, not so much.
I found Atom with sbt running in a shell under platformio terminal worked best.

## Making a New Dotty Project
> The fastest way to start a new Dotty project is to use one of the following templates:
> * [Simple Dotty project](https://github.com/lampepfl/dotty.g8)
> * [Dotty project that cross-compiles with Scala 2](https://github.com/lampepfl/dotty-cross.g8)

I am not a fan of `giter8`.
There is no reason to introduce yet another obscure language.
[Try dottyTemplate](https://github.com/mslinn/dottyTemplate) instead.

## Using Dotty in An Existing Project

You will need to make the following adjustments to your build:

### project/plugins.sbt
```scala
addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.3.4")
```

### `project/build.properties`
```scala
sbt.version=1.2.8
```

Versions of sbt older than 1.2.8 are not supported.
Versions 1.3.3 and 1.3.4 do not work properly with Dotty.

### `build.sbt`
Any version number that starts with `0.` is automatically recognized as Dotty by
the `sbt-dotty` plugin, you don't need to set up anything:

```scala
scalaVersion := "0.20.0-RC1"
```

#### Nightly Builds
If the latest release of Dotty is missing a bugfix or feature you need, you may
wish to use a nightly build. Look at the bottom of
https://repo1.maven.org/maven2/ch/epfl/lamp/dotty_0.18/ to find the version
number for the latest nightly build. Alternatively, you can set `scalaVersion :=
dottyLatestNightlyBuild.get` to always use the latest nightly build of dotty.

## Compiling Scala 2 Projects with Dotty
When porting an existing project, it's a good idea to start out with the Scala 2
compatibility mode (note that this mode affects typechecking and thus may
prevent some valid Dotty code from compiling) by adding to your `build.sbt`:

```scala
scalacOptions ++= { if (isDotty.value) Seq("-language:Scala2") else Nil }
```

Using the `isDotty` setting ensures that this option will only be set when
compiling with Dotty.

> A tool to port code from Scala 2.x to Dotty is currently in development at
https://github.com/scalacenter/scalafix

Actually, the truth is `scalafix` has not had any work done on it in a long time, and there is no indication when this important project will get the attention it deserves. I'm concerned that history will repeat itself and we'll get a last-minute hack job.

If your build contains dependencies that have only been published for Scala 2.x,
you may be able to get them to work on Dotty by replacing:

```scala
    libraryDependencies += "a" %% "b" % "c"
```

by:

```scala
    libraryDependencies += ("a" %% "b" % "c").withDottyCompat(scalaVersion.value)
```

This will have no effect when compiling with Scala 2.x, but when compiling
with Dotty this will change the cross-version to a Scala 2.x one. This
works because Dotty is currently retro-compatible with Scala 2.x.

Alternatively, to set this setting on all your dependencies, you can use:

```scala
    libraryDependencies := libraryDependencies.value.map(_.withDottyCompat(scalaVersion.value))
```

## Discuss

Feel free to come chat with us on the
[Dotty gitter](http://gitter.im/lampepfl/dotty)!
