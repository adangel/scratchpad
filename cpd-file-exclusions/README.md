# CPD Exclude File Patterns

Given the following project:

```
.
├── README.md
└── src
    ├── File.cs
    ├── Form1.cs
    └── Form1.Designer.cs
```

## a) CLI

**Manual**:
```
$ ${PATH_TO_PMD}/bin/run.sh cpd --language cs --minimum-tokens 2 --files src --exclude "src/Form1.Designer.cs"
Found a 1 line (5 tokens) duplication in the following files: 
Starting at line 1 of src/File.cs
Starting at line 1 of src/Form1.cs

public class Test { }
```

**Preparing a filelist.txt**:
`$ find -name "*.cs" -and -not -name "*.Designer.cs" -printf "%P," > filelist.txt`

This creates the file "filelist.txt" with the following content:
```
src/File.cs,src/Form1.cs,
```

Now you can run CPD giving this filelist:

```
$ ${PATH_TO_PMD}/bin/run.sh cpd --language cs --minimum-tokens 2 --filelist filelist.txt
Found a 1 line (5 tokens) duplication in the following files: 
Starting at line 1 of src/File.cs
Starting at line 1 of src/Form1.cs

public class Test { }
```

More info: https://pmd.github.io/latest/pmd_userdocs_cpd.html#cli-usage

## b) ANT

See [build.xml](build.xml)

```
$ ant -Dpmd.path=${PATH_TO_PMD} cpd
Buildfile: build.xml

cpd:
      [cpd] Starting run, minimumTokenCount is 2
      [cpd] Tokenizing files
      [cpd] Starting to analyze code
      [cpd] Done analyzing code; that took 5 milliseconds
      [cpd] Generating report
      [cpd] Found a 1 line (5 tokens) duplication in the following files: 
      [cpd] Starting at line 1 of src/File.cs
      [cpd] Starting at line 1 of src/Form1.cs
      [cpd] 
      [cpd] public class Test { }

BUILD SUCCESSFUL
Total time: 0 seconds
```

More info: https://pmd.github.io/latest/pmd_userdocs_cpd.html#ant-task

## c) Maven

See [pom.xml](pom.xml)

```
$ mvn pmd:cpd-check
[INFO] Scanning for projects...
[INFO] 
[INFO] ------< org.apache.maven.plugins.pmd.its:MPMD-290-cpd-for-csharp >------
[INFO] Building MPMD-290-cpd-for-csharp 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] >>> maven-pmd-plugin:3.13.0:cpd-check (default-cli) > :cpd @ MPMD-290-cpd-for-csharp >>>
[INFO] 
[INFO] --- maven-pmd-plugin:3.13.0:cpd (cpd) @ MPMD-290-cpd-for-csharp ---
[WARNING] Unable to locate Source XRef to link to - DISABLED
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] 
[INFO] <<< maven-pmd-plugin:3.13.0:cpd-check (default-cli) < :cpd @ MPMD-290-cpd-for-csharp <<<
[INFO] 
[INFO] 
[INFO] --- maven-pmd-plugin:3.13.0:cpd-check (default-cli) @ MPMD-290-cpd-for-csharp ---
[INFO] PMD version: 6.21.0
[INFO] CPD Failure: Found 1 lines of duplicated code at locations:
[INFO]     src/File.cs line 1
[INFO]     src/Form1.cs line 1
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.434 s
[INFO] Finished at: 2020-10-22T09:22:55+02:00
[INFO] ------------------------------------------------------------------------
[WARNING] The requested profile "generate-rule-docs" could not be activated because it does not exist.
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-pmd-plugin:3.13.0:cpd-check (default-cli) on project MPMD-290-cpd-for-csharp: You have 1 CPD duplication. For more details see: target/cpd.xml -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```

More info:

* https://pmd.github.io/latest/pmd_userdocs_tools_maven.html
* https://github.com/apache/maven-pmd-plugin/tree/master/src/it/MPMD-290-cpd-for-csharp
* https://maven.apache.org/plugins/maven-pmd-plugin/cpd-mojo.html
