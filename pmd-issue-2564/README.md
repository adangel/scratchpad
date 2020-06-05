# Reproduction project for pmd issue 2564

See https://github.com/pmd/pmd/issues/2564

The goal is, to use maven to generate a PMD/CPD report via the maven-site-plugin
for Apex sources.

## Tested Versions

* maven 3.6.3
* maven-pmd-plugin 3.13.0
* maven-site-plugin 3.9.0

## Run it

Run it with

```
mvn site
```

The site will be generated as `target/site/index.html`. The pmd report is at `target/site/pmd.html`.
There is one violation expected in "ExampleTest.cls" (so the report must not be empty!).
