#!/bin/bash

PMD_HOME="$HOME/PMD/binaries/pmd-bin-7.26.0"
#PMD_HOME="$HOME/PMD/source/pmd/pmd-dist/target/pmd-bin-7.27.0-SNAPSHOT"
DEBUG="--debug"
DEBUG=
RULESET="rulesets/java/quickstart.xml"
$PMD_HOME/bin/pmd check --no-progress --no-cache $DEBUG \
  -d src/Other.java -R "$RULESET" --aux-classpath output.jar
