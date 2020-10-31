#!/usr/bin/env bash

#set -x
set -e

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

function compare() {
    MODULE=$1
    VERSION_OLD=$2
    VERSION_NEW=$3
    
    echo "Comparing module=${MODULE} ${VERSION_OLD}->${VERSION_NEW}"
    
    prepare $MODULE $VERSION_OLD
    prepare $MODULE $VERSION_NEW
    
    java -jar japicmp-0.14.4-jar-with-dependencies.jar \
      --old downloads/${VERSION_OLD}/${MODULE}-${VERSION_OLD}.jar --old-classpath $(cat downloads/${VERSION_OLD}/${MODULE}-${VERSION_OLD}.classpath) \
      --new downloads/${VERSION_NEW}/${MODULE}-${VERSION_NEW}.jar --new-classpath $(cat downloads/${VERSION_NEW}/${MODULE}-${VERSION_NEW}.classpath) \
      --only-modified \
      --report-only-filename \
      --html-file results/${MODULE}-${VERSION_OLD}-${VERSION_NEW}.html

#      --only-incompatible \
}

function create_index_header() {
    MODULES=$1
    cat > results/index.html <<EOF
<html>
    <head>
      <title>japicmp PMD</title>
    </head>
    <body>
    <h1>japicmp PMD</h1>
EOF
    for MODULE in $MODULES; do
        echo "<a href=\"#$MODULE\">$MODULE</a> | " >> results/index.html
    done
    echo "<hr>" >> results/index.html
}

if [ ! -e japicmp-0.14.4-jar-with-dependencies.jar ]; then
    wget https://repo1.maven.org/maven2/com/github/siom79/japicmp/japicmp/0.14.4/japicmp-0.14.4-jar-with-dependencies.jar
fi

FIRST_VERSION="6.0.0"
VERSIONS="6.26.0 6.27.0 6.28.0 6.29.0"
MODULES="pmd-core pmd-java"

mkdir -p results

create_index_header "$MODULES"

for MODULE in $MODULES; do
    echo "<h2 id=\"$MODULE\">$MODULE</h2>" >> results/index.html
    echo "<ul>" >> results/index.html
    OLD_VERSION=$FIRST_VERSION
    for VERSION in $VERSIONS; do
        compare $MODULE $OLD_VERSION $VERSION
        echo "  <li><a href=\"${MODULE}-${OLD_VERSION}-${VERSION}.html\">${OLD_VERSION} --> ${VERSION}</a></li>" >> results/index.html
        OLD_VERSION=$VERSION
    done
    echo "</ul>" >> results/index.html
done

echo "</body></html>" >> results/index.html
