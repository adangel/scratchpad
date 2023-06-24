# Reproduction case for pmd/pmd#4602

<https://github.com/pmd/pmd/issues/4602>

1. Build with maven: `mvn clean verify`
2. Expected output:

```
[INFO] --- pmd:3.21.1-pmd-7-SNAPSHOT:check (default) @ issue-4602-unnecessaryimport ---
[INFO] PMD version: 7.0.0-rc3
[INFO] PMD Failure: sample.CallRateLayer:4 Rule:UnnecessaryImport Priority:4 Unused static import 'com.codeborne.selenide.Condition.exist'.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

Only `com.codeborne.selenide.Condition.exist` is marked as unused, which is correct.
`com.codeborne.selenide.Condition.visible` and `com.codeborne.selenide.Selenide.$` are used
and this is correctly determined.

