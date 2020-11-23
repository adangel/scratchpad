# Reproduction case for 2606

**Under Windows:**

* Clean: `rd /s "target"`
* Compile: `javac -d target src\*\*.java`
* Run PMD: `pmd.bat -d src -auxclasspath target -R pmd_ruleset.xml -no-cache -f text`

--> no violations

* Run PMD without auxclasspath: `pmd.bat -d src -R pmd_ruleset.xml -no-cache -f text`

--> one violation: `src\myPackage\Foo.java:6:	Switch statements should have a default label` due to the missing
auxclasspath

**Under Linux:**

* Clean: `rm -rf target; mkdir -p target`
* Compile: `javac -d target src/*/*.java`
* Run PMD: `~/pmd-bin-6.29.0/bin/run.sh pmd -d src -auxclasspath target -R pmd_ruleset.xml -no-cache -f text`

--> no violations

* Run PMD without auxclasspath: `~/pmd-bin-6.29.0/bin/run.sh pmd -d src -R pmd_ruleset.xml -no-cache -f text`

--> one violation: `src/myPackage/Foo.java:6:	Switch statements should have a default label` due to the missing
auxclasspath

* conclusion: works as expected
