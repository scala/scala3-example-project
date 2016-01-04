## Example sbt project that compiles using Dotty

### Requirements

Until artifacts are published, you'll need to compile the dependencies:
```shell
git clone https://github.com/lampepfl/dotty.git
cd dotty

## This step won't be needed once https://github.com/lampepfl/dotty/pull/1013 has been merged
mkdir resources && echo "version.number=0.1-SNAPSHOT" > resources/compiler.properties

sbt publishLocal
cd ..

## This step won't be needed once sbt 0.13.10 is released
git checkout -b 0.13 https://github.com/sbt/sbt.git
cd sbt
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
