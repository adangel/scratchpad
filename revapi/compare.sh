#!/usr/bin/env bash

set -e

function download_revapi() {
    mkdir -p revapi
    pushd revapi >/dev/null
    URL='https://search.maven.org/remotecontent?filepath=org/revapi/revapi-standalone/0.9.7/revapi-standalone-0.9.7-standalone.zip'
    NAME=$(basename $URL)
    if [ ! -e $NAME ]; then
        wget -O $NAME $URL
        unzip $NAME
    fi
    popd >/dev/null
}

function prepare() {
    MODULE=$1
    VERSION=$2
    
    mkdir -p downloads/${VERSION}
    pushd downloads/${VERSION} >/dev/null
    if [ ! -e ${MODULE}-${VERSION}.jar ]; then
        wget https://repo.maven.apache.org/maven2/net/sourceforge/pmd/${MODULE}/${VERSION}/${MODULE}-${VERSION}.jar
    fi
    if [ ! -e ${MODULE}-${VERSION}.pom ]; then
        wget https://repo.maven.apache.org/maven2/net/sourceforge/pmd/${MODULE}/${VERSION}/${MODULE}-${VERSION}.pom
    fi
    if [ ! -e ${MODULE}-${VERSION}.classpath ]; then
        mvn -f ${MODULE}-${VERSION}.pom dependency:build-classpath -DincludeScope=test -Dmdep.outputFile=${MODULE}-${VERSION}.classpath
    fi
    popd >/dev/null
}

download_revapi

REVAPI="revapi/revapi-*/revapi.sh"

prepare "pmd-core" "6.27.0"
prepare "pmd-core" "6.28.0"

oldcl=$(cat downloads/6.27.0/pmd-core-6.27.0.classpath)
newcl=$(cat downloads/6.28.0/pmd-core-6.28.0.classpath)
${REVAPI} \
  --extensions=org.revapi:revapi-java:0.22.0,org.revapi:revapi-reporter-text:0.12.1 \
  --old=downloads/6.27.0/pmd-core-6.27.0.jar \
  --old-supplementary=${oldcl//\:/,} \
  --new=downloads/6.28.0/pmd-core-6.28.0.jar \
  --new-supplementary=${newcl//\:/,}
  
  #-Drevapi.reporter.text.minSeverity=POTENTIALLY_BREAKING
