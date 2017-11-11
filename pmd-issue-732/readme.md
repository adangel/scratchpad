# PMD Issue 732

https://github.com/pmd/pmd/issues/732

## m-pmd-p 3.8 + PMD 5.8.1

Run with `mvn clean test-compile`. The build is expected to fail with 1 violation:

    [ERROR] Failed to execute goal org.apache.maven.plugins:maven-pmd-plugin:3.8:check (default) on project MPMD-89-232-typeresolution: You have 1 PMD violation. For more details see: /home/andreas/PMD/source/scratchpad/pmd-classloading/target/pmd.xml -> [Help 1]

The expected violation is this:

    <violation beginline="28" endline="32" begincolumn="8" endcolumn="1" rule="TestClassWithoutTestCases" ruleset="JUnit" package="it" class="NoTestsHere" externalInfoUrl="http://pmd.sourceforge.net/snapshot/pmd-java/rules/java/junit.html#TestClassWithoutTestCases" priority="3">
    This class name ends with Test but contains no test cases
    </violation>

## m-pmd-p 3.8 + PMD 6.0.0-SNAPSHOT

Run with `mvn clean test-compile -DpmdVersion=6.0.0-SNAPSHOT`.

We get the following warning:

    Nov 11, 2017 10:30:26 AM net.sourceforge.pmd.lang.java.typeresolution.ClassTypeResolver visit
    WARNING: Could not find class it.NoTestsHere, due to: java.lang.LinkageError: loader (instance of  net/sourceforge/pmd/util/ClasspathClassLoader): attempted  duplicate class definition for name: "org/custommonkey/xmlunit/XMLConstants"

And no violation.
