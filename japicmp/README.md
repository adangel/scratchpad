# japicmp

* https://github.com/siom79/japicmp
* https://siom79.github.io/japicmp/
* https://repo1.maven.org/maven2/com/github/siom79/japicmp/japicmp/0.14.4/japicmp-0.14.4-jar-with-dependencies.jar

## Run

```
$ ./compare.sh
```

## Result

https://chunk.pmd-code.org/pmd/d5db0e25-67d6-43b9-a20c-fab3639f1b4b/index.html

## Sample config for pmd-core

```xml
<plugin>
    <groupId>com.github.siom79.japicmp</groupId>
    <artifactId>japicmp-maven-plugin</artifactId>
    <version>0.14.4</version>
    <configuration>
        <parameter>
            <onlyModified>true</onlyModified>
            <breakBuildBasedOnSemanticVersioning>true</breakBuildBasedOnSemanticVersioning>
            <excludes>
                <!-- <exclude>net.sourceforge.pmd.ant.internal</exclude> -->
                <!-- <exclude>@net.sourceforge.pmd.annotation.InternalApi</exclude> -->
            </excludes>
        </parameter>
    </configuration>
    <executions>
        <execution>
            <phase>verify</phase>
            <goals>
                <goal>cmp</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
