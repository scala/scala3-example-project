## Example sbt project that compiles using Dotty

### Requirements

```shell
git clone https://github.com/lampepfl/dotty.git
cd dotty

## This step won't be needed once https://github.com/lampepfl/dotty/pull/1013 has been merged
mkdir resources && echo "version.number=0.1-SNAPSHOT" > resources/compiler.properties

sbt publishLocal
cd ..

git clone https://github.com/smarter/sbt.git
cd sbt
git checkout 0.13-dotty
sbt publishLocal
cd ..

git clone https://github.com/smarter/dotty-bridge.git
cd dotty-bridge
sbt publishLocal
cd ..
```

### Usage

Run sbt as usual, see https://github.com/smarter/dotty-bridge#todo for what
works and what doesn't.
