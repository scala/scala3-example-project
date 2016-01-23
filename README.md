## Example sbt project that compiles using Dotty

### Requirements

Until artifacts are published, you'll need to compile Dotty and its compiler
bridge yourself:
```shell
git clone https://github.com/lampepfl/dotty.git
cd dotty
sbt publishLocal
cd ..

git clone https://github.com/smarter/dotty-bridge.git
cd dotty-bridge
sbt publishLocal
cd ..
```

### Usage

Run sbt as usual, see
https://github.com/smarter/dotty-bridge#implementation-status for what works and
what doesn't.

### Discuss

Feel free to come chat with us on the
[Dotty gitter](http://gitter.im/lampepfl/dotty)!
