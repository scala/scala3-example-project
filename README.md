# Example sbt project that compiles using Scala 3

[![Continuous Integration](https://github.com/scala/scala3-example-project/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/scala/scala3-example-project/actions/workflows/ci.yml)

## Usage

This is a normal sbt project. You can start the sbt shell using `sbt` then compile code with `compile`, run the main
method with `run`, run the tests with `test` and start a REPL using `console`.

If compiling this example project fails, you probably have a global sbt plugin
that does not work with Scala 3. You might try disabling plugins in
`~/.sbt/1.0/plugins` and `~/.sbt/1.0`.

## Making a new Scala 3 project

The fastest way to start a new Scala 3 project is to use one of the following templates:

* [Minimal Scala 3 project](https://github.com/scala/scala3.g8)
* [Scala 3 project that cross-compiles with Scala 2](https://github.com/scala/scala3-cross.g8)

## Using Scala 3 in an existing project

You will need to make the following adjustments to your build:

### project/build.properties

```
sbt.version=1.9.6
```

You must use sbt 1.5.5 or newer; older versions of sbt are not supported.

### build.sbt

Set the Scala 3 version:

```scala
scalaVersion := "3.3.1"
```

### Getting your project to compile with Scala 3

For help with porting an existing Scala 2 project to Scala 3, see the
[Scala 3 migration guide](https://docs.scala-lang.org/scala3/guides/migration/compatibility-intro.html).

## Need help?

https://www.scala-lang.org/community/ has links.
