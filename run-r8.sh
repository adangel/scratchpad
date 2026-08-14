#!/bin/bash

R8_VERSION=9.4.12
R8_JAR="r8-${R8_VERSION}.jar"

if [ ! -e "$R8_JAR" ]; then
  curl -o "$R8_JAR" "https://dl.google.com/android/maven2/com/android/tools/r8/${R8_VERSION}/r8-${R8_VERSION}.jar"
fi

java -cp "${R8_JAR}" com.android.tools.r8.R8 \
       --release \
       --classfile \
       --output output.jar \
       --pg-conf proguard.cfg \
       --lib "$JAVA_HOME" \
       input.jar
