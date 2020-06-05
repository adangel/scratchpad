# Reproduction project for pmd issue 2564

See https://github.com/pmd/pmd/issues/2564

The goal is, to use maven to generate a PMD/CPD report via the maven-site-plugin
for Apex sources.

## Tested Versions

maven 3.6.3

## Run it

Run it with

```
mvn site
```

The site will be generated as `target/site/index.html`.
