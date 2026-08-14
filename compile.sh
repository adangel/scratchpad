#!/bin/bash

rm -rf classes/*
javac -d classes src/*.java
jar --create --file input.jar -C classes .
