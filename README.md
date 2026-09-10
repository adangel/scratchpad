# Reproducer for false positives / negatives depending on Java version

Related issues:
* https://github.com/pmd/pmd/issues/6224
* https://github.com/pmd/pmd/pull/5299
* https://github.com/pmd/pmd/issues/4291
* https://github.com/pmd/pmd/issues/4620
* https://github.com/apache/maven-pmd-plugin/pull/726

## Runtime Java 25, targetJdk=8

```
$ ./mvnw --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /home/andreas/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: /home/andreas/programs/openjdk/eclipse-temurin/jdk-25.0.2+10
```

```
$ ./mvnw verify
...
[INFO] --- pmd:3.28.0:check (default) @ auxclasspath-java-runtime ---
[WARNING] PMD Failure: CloseResourceExample:6 Rule:CloseResource Priority:3 Ensure that resources like this ForkJoinPool object are closed after use.
[WARNING] PMD Failure: CloseResourceExample:18 Rule:CloseResource Priority:3 Ensure that resources like this ExecutorService object are closed after use.
[WARNING] PMD Failure: UnnecessaryCastExample:8 Rule:UnnecessaryCast Priority:3 Unnecessary cast (byte[]).
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

These are all false positives for Java 8.

## Runtime Java 8, targetJdk=8

```
$ ./mvnw --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /home/andreas/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f
Java version: 1.8.0_482, vendor: Temurin, runtime: /home/andreas/programs/openjdk/eclipse-temurin/jdk8u482-b08/jre
```

```
$ ./mvnw verify
...
[INFO] --- pmd:3.28.0:check (default) @ auxclasspath-java-runtime ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

## Runtime Java 25, targetJdk=8, with jrt-fs.jar from toolchain
See https://github.com/apache/maven-pmd-plugin/pull/726

-> Toolchain is selected by targetJdk

```
$ ./mvnw --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /home/andreas/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: /home/andreas/programs/openjdk/eclipse-temurin/jdk-25.0.2+10
```

```
$ ./mvnw verify -Dpmd.plugin.version=3.28.1-SNAPSHOT
...
[INFO] --- pmd:3.28.1-SNAPSHOT:check (default) @ auxclasspath-java-runtime ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

## Runtime Java 25, targetJdk=8, no toolchains found
See https://github.com/apache/maven-pmd-plugin/pull/726

- Toolchain is searched with targetJdk, but none is found
- Warning is issued

```
$ ./mvnw --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /home/andreas/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: /home/andreas/programs/openjdk/eclipse-temurin/jdk-25.0.2+10
Default locale: de_DE, platform encoding: UTF-8
OS name: "linux", version: "7.1.12+deb14-amd64", arch: "amd64", family: "unix"
```

```
$ ./mvnw verify -Dpmd.plugin.version=3.28.1-SNAPSHOT --global-toolchains=empty-toolchains.xml --toolchains=empty-toolchains.xml 
[INFO] --- pmd:3.28.1-SNAPSHOT:pmd (pmd) @ auxclasspath-java-runtime ---
[WARNING] Adding current java runtime classes from /home/andreas/programs/openjdk/eclipse-temurin/jdk-25.0.2+10/lib/jrt-fs.jar to aux classpath. Please make sure to configure a toolchain for java version 8 in your toolchains.xml. See also <https://maven.apache.org/plugins/maven-pmd-plugin/examples/targetJdk.html>.
[INFO] PMD version: 7.27.0
[INFO] 
[INFO] <<< pmd:3.28.1-SNAPSHOT:check (default) < :pmd @ auxclasspath-java-runtime <<<
[INFO] 
[INFO] 
[INFO] --- pmd:3.28.1-SNAPSHOT:check (default) @ auxclasspath-java-runtime ---
[WARNING] PMD Failure: CloseResourceExample:6 Rule:CloseResource Priority:3 Ensure that resources like this ForkJoinPool object are closed after use.
[WARNING] PMD Failure: CloseResourceExample:18 Rule:CloseResource Priority:3 Ensure that resources like this ExecutorService object are closed after use.
[WARNING] PMD Failure: UnnecessaryCastExample:8 Rule:UnnecessaryCast Priority:3 Unnecessary cast (byte[]).
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

## Runtime Java 25, targetJdk=8, with toolchain plugin
See https://github.com/apache/maven-pmd-plugin/pull/726

- Toolchain is selected by toolchain plugin
- Toolchain is added to the aux classpath
- PMD is executed via the toolchain

```
$ ./mvnw --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /home/andreas/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: /home/andreas/programs/openjdk/eclipse-temurin/jdk-25.0.2+10
Default locale: de_DE, platform encoding: UTF-8
OS name: "linux", version: "7.1.12+deb14-amd64", arch: "amd64", family: "unix"
```

```
$ ./mvnw verify -Dpmd.plugin.version=3.28.1-SNAPSHOT -Dtoolchain.jdk.version=8
[INFO] --- pmd:3.28.1-SNAPSHOT:check (default) @ auxclasspath-java-runtime ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

Cross check with jdk21 - expect the violations again
```
$ ./mvnw verify -Dpmd.plugin.version=3.28.1-SNAPSHOT -Dtoolchain.jdk.version=21
[INFO] --- pmd:3.28.1-SNAPSHOT:check (default) @ auxclasspath-java-runtime ---
[WARNING] PMD Failure: CloseResourceExample:6 Rule:CloseResource Priority:3 Ensure that resources like this ForkJoinPool object are closed after use.
[WARNING] PMD Failure: CloseResourceExample:18 Rule:CloseResource Priority:3 Ensure that resources like this ExecutorService object are closed after use.
[WARNING] PMD Failure: UnnecessaryCastExample:8 Rule:UnnecessaryCast Priority:3 Unnecessary cast (byte[]).
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

## Runtime Java 25, targetJdk=8, jdkToolchain property for pmd plugin
See https://github.com/apache/maven-pmd-plugin/pull/726

- Toolchain is selected by the `jdkToolchain` property for the pmd plugin
- Toolchain is added to the aux classpath
- PMD is executed via the toolchain

```
$ ./mvnw --version
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /home/andreas/.m2/wrapper/dists/apache-maven-3.9.16/56ba1f9f
Java version: 25.0.2, vendor: Eclipse Adoptium, runtime: /home/andreas/programs/openjdk/eclipse-temurin/jdk-25.0.2+10
Default locale: de_DE, platform encoding: UTF-8
OS name: "linux", version: "7.1.12+deb14-amd64", arch: "amd64", family: "unix"
```

```
$ ./mvnw verify -Dpmd.plugin.version=3.28.1-SNAPSHOT -DpmdToolchain.jdk.version=8
[INFO] --- pmd:3.28.1-SNAPSHOT:check (default) @ auxclasspath-java-runtime ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

Cross check with jdk21 - expect the violations again
```
$ ./mvnw verify -Dpmd.plugin.version=3.28.1-SNAPSHOT -DpmdToolchain.jdk.version=21
[INFO] --- pmd:3.28.1-SNAPSHOT:check (default) @ auxclasspath-java-runtime ---
[WARNING] PMD Failure: CloseResourceExample:6 Rule:CloseResource Priority:3 Ensure that resources like this ForkJoinPool object are closed after use.
[WARNING] PMD Failure: CloseResourceExample:18 Rule:CloseResource Priority:3 Ensure that resources like this ExecutorService object are closed after use.
[WARNING] PMD Failure: UnnecessaryCastExample:8 Rule:UnnecessaryCast Priority:3 Unnecessary cast (byte[]).
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```
