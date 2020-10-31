# revapi

*   https://revapi.org/revapi-site/index.html
*   https://revapi.org/revapi-maven-plugin/index.html
*   Detected Changes: https://revapi.org/revapi-java/differences.html

## Run

```
$ ./compare.sh
20:22:58.233 INFO  Downloading checked archives
20:22:58.254 INFO  Downloading extensions
20:22:58.679 WARN  Detected version conflict in dependencies of extension org.revapi:revapi-java:jar:0.22.0. The extension depends on org.revapi:revapi:jar:0.12.0 while the CLI has org.revapi:revapi:jar:0.11.3 on global classpath. This will likely cause problems.
20:22:58.746 WARN  Detected version conflict in dependencies of extension org.revapi:revapi-java-spi:jar:0.21.0. The extension depends on org.revapi:revapi:jar:0.12.0 while the CLI has org.revapi:revapi:jar:0.11.3 on global classpath. This will likely cause problems.
20:22:58.785 WARN  Detected version conflict in dependencies of extension org.revapi:revapi-reporter-text:jar:0.12.1. The extension depends on org.revapi:revapi:jar:0.12.0 while the CLI has org.revapi:revapi:jar:0.11.3 on global classpath. This will likely cause problems.
20:22:58.814 WARN  Detected version conflict in dependencies of extension org.revapi:revapi-reporter-file-base:jar:0.2.1. The extension depends on org.revapi:revapi:jar:0.12.0 while the CLI has org.revapi:revapi:jar:0.11.3 on global classpath. This will likely cause problems.
20:22:58.866 INFO  Starting analysis
20:22:58.905 WARN  At least one of `minLevel` and `minCriticality` should to be defined. Defaulting to the obsolete behavior of reporting all potentially breaking elements.
Analysis results
----------------

Old API: pmd-core-6.27.0.jar
New API: pmd-core-6.28.0.jar
old: missing-class org.dom4j.io.SAXReader
new: missing-class org.dom4j.io.SAXReader
java.missing.oldClass: Class 'org.dom4j.io.SAXReader' could not be found in the archives of the old API. It has been detected as contributing to the API and thus the analysis results may be incorrect.
BINARY: POTENTIALLY_BREAKING, SOURCE: POTENTIALLY_BREAKING
java.missing.newClass: Class 'org.dom4j.io.SAXReader' could not be found in the archives of the new API. It has been detected as contributing to the API and thus the analysis results may be incorrect.
BINARY: POTENTIALLY_BREAKING, SOURCE: POTENTIALLY_BREAKING

old: missing-class org.jdom.Element
new: missing-class org.jdom.Element
java.missing.oldClass: Class 'org.jdom.Element' could not be found in the archives of the old API. It has been detected as contributing to the API and thus the analysis results may be incorrect.
BINARY: POTENTIALLY_BREAKING, SOURCE: POTENTIALLY_BREAKING
java.missing.newClass: Class 'org.jdom.Element' could not be found in the archives of the new API. It has been detected as contributing to the API and thus the analysis results may be incorrect.
BINARY: POTENTIALLY_BREAKING, SOURCE: POTENTIALLY_BREAKING

old: missing-class org.jdom.Namespace
new: missing-class org.jdom.Namespace
java.missing.oldClass: Class 'org.jdom.Namespace' could not be found in the archives of the old API. It has been detected as contributing to the API and thus the analysis results may be incorrect.
BINARY: POTENTIALLY_BREAKING, SOURCE: POTENTIALLY_BREAKING
java.missing.newClass: Class 'org.jdom.Namespace' could not be found in the archives of the new API. It has been detected as contributing to the API and thus the analysis results may be incorrect.
BINARY: POTENTIALLY_BREAKING, SOURCE: POTENTIALLY_BREAKING

old: class net.sf.saxon.query.QueryParser.Import
new: class net.sf.saxon.query.QueryParser.Import
java.class.nonPublicPartOfAPI: Class 'net.sf.saxon.query.QueryParser.Import' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: class net.sf.saxon.tree.ParentNodeImpl
new: class net.sf.saxon.tree.ParentNodeImpl
java.class.nonPublicPartOfAPI: Class 'net.sf.saxon.tree.ParentNodeImpl' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: class net.sourceforge.pmd.benchmark.BenchmarkResult
new: class net.sourceforge.pmd.benchmark.BenchmarkResult
java.class.nonPublicPartOfAPI: Class 'net.sourceforge.pmd.benchmark.BenchmarkResult' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: class net.sourceforge.pmd.benchmark.TimeTracker.TimedResult
new: class net.sourceforge.pmd.benchmark.TimeTracker.TimedResult
java.class.nonPublicPartOfAPI: Class 'net.sourceforge.pmd.benchmark.TimeTracker.TimedResult' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: field net.sourceforge.pmd.cpd.AnyTokenizer.TOKENS
new: <none>
java.field.removedWithConstant: Field with constant value has been removed.
BINARY: NON_BREAKING, SOURCE: BREAKING, SEMANTIC: POTENTIALLY_BREAKING

old: class net.sourceforge.pmd.lang.ast.xpath.internal.DeprecatedAttrLogger.Noop
new: class net.sourceforge.pmd.lang.ast.xpath.internal.DeprecatedAttrLogger.Noop
java.class.nonPublicPartOfAPI: Class 'net.sourceforge.pmd.lang.ast.xpath.internal.DeprecatedAttrLogger.Noop' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: class net.sourceforge.pmd.renderers.CodeClimateIssue.Location.Lines
new: class net.sourceforge.pmd.renderers.CodeClimateIssue.Location.Lines
java.class.nonPublicPartOfAPI: Class 'net.sourceforge.pmd.renderers.CodeClimateIssue.Location.Lines' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: class org.antlr.v4.runtime.tree.pattern.Chunk
new: class org.antlr.v4.runtime.tree.pattern.Chunk
java.class.nonPublicPartOfAPI: Class 'org.antlr.v4.runtime.tree.pattern.Chunk' is indirectly included in the API (by the means of method return type for example) but the class is not accessible (neither public nor protected).
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: BREAKING

old: field org.apache.tools.ant.RuntimeConfigurable.serialVersionUID
new: field org.apache.tools.ant.RuntimeConfigurable.serialVersionUID
java.field.serialVersionUIDUnchanged: The class changed in an incompatible way with regards to serialization but the serialVersionUID field stayed unchanged. This might be ok and/or desired but is suspicious.
BINARY: EQUIVALENT, SOURCE: EQUIVALENT, SEMANTIC: POTENTIALLY_BREAKING

old: method java.lang.Object org.apache.tools.ant.types.DataType::getCheckedRef()
new: method <T> T org.apache.tools.ant.types.DataType::getCheckedRef()
java.method.returnTypeTypeParametersChanged: The return type changed from 'java.lang.Object' to 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING
java.generics.elementNowParameterized: Element now defines formal type parameters.
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: POTENTIALLY_BREAKING
java.generics.formalTypeParameterAdded: A new formal type parameter added to element: 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING
java.element.nowDeprecated: The element is now deprecated.
BINARY: EQUIVALENT, SOURCE: EQUIVALENT

old: method java.lang.Object org.apache.tools.ant.types.DataType::getCheckedRef(org.apache.tools.ant.Project)
new: method <T> T org.apache.tools.ant.types.DataType::getCheckedRef(java.lang.Class<T>)
java.method.returnTypeTypeParametersChanged: The return type changed from 'java.lang.Object' to 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING
java.generics.elementNowParameterized: Element now defines formal type parameters.
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: POTENTIALLY_BREAKING
java.generics.formalTypeParameterAdded: A new formal type parameter added to element: 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING

old: parameter java.lang.Object org.apache.tools.ant.types.DataType::getCheckedRef(===org.apache.tools.ant.Project===)
new: parameter <T> T org.apache.tools.ant.types.DataType::getCheckedRef(===java.lang.Class<T>===)
java.method.parameterTypeChanged: The type of the parameter changed from 'org.apache.tools.ant.Project' to 'java.lang.Class<T>'.
BINARY: BREAKING, SOURCE: POTENTIALLY_BREAKING

old: method org.apache.tools.ant.types.FileList org.apache.tools.ant.types.FileList::getRef(org.apache.tools.ant.Project)
new: method org.apache.tools.ant.types.FileList org.apache.tools.ant.types.FileList::getRef(org.apache.tools.ant.Project)
java.method.visibilityReduced: visibility reduced
BINARY: BREAKING, SOURCE: BREAKING

old: method java.lang.Object org.apache.tools.ant.types.Reference::getReferencedObject() throws org.apache.tools.ant.BuildException
new: method <T> T org.apache.tools.ant.types.Reference::getReferencedObject() throws org.apache.tools.ant.BuildException
java.method.returnTypeTypeParametersChanged: The return type changed from 'java.lang.Object' to 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING
java.generics.elementNowParameterized: Element now defines formal type parameters.
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: POTENTIALLY_BREAKING
java.generics.formalTypeParameterAdded: A new formal type parameter added to element: 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING

old: method java.lang.Object org.apache.tools.ant.types.Reference::getReferencedObject(org.apache.tools.ant.Project) throws org.apache.tools.ant.BuildException
new: method <T> T org.apache.tools.ant.types.Reference::getReferencedObject(org.apache.tools.ant.Project) throws org.apache.tools.ant.BuildException
java.method.returnTypeTypeParametersChanged: The return type changed from 'java.lang.Object' to 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING
java.generics.elementNowParameterized: Element now defines formal type parameters.
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: POTENTIALLY_BREAKING
java.generics.formalTypeParameterAdded: A new formal type parameter added to element: 'T'.
BINARY: NON_BREAKING, SOURCE: BREAKING

old: <none>
new: class org.apache.tools.ant.types.selectors.PosixGroupSelector
java.class.externalClassExposedInAPI: A class from supplementary archives is used in a public capacity in the API.
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: POTENTIALLY_BREAKING

old: <none>
new: class org.apache.tools.ant.types.selectors.PosixPermissionsSelector
java.class.externalClassExposedInAPI: A class from supplementary archives is used in a public capacity in the API.
BINARY: NON_BREAKING, SOURCE: NON_BREAKING, SEMANTIC: POTENTIALLY_BREAKING

20:23:09.265 INFO  Analysis took 10399ms.
```

## maven config
for pmd-core:

```xml
<plugin>
    <groupId>org.revapi</groupId>
    <artifactId>revapi-maven-plugin</artifactId>
    <version>0.12.1</version>
    <dependencies>
        <dependency>
            <groupId>org.revapi</groupId>
            <artifactId>revapi-java</artifactId>
            <version>0.22.0</version>
        </dependency>
    </dependencies>
    <configuration>
        <failSeverity>breaking</failSeverity>
        <ignoreSuggestionsFormat>xml</ignoreSuggestionsFormat>
        <analysisConfiguration>
            <revapi.java>
                <missing-classes>
                    <behavior>ignore</behavior>
                </missing-classes>
                <checks>
                    <nonPublicPartOfAPI>
                        <!-- we should enable this on PMD 7 to avoid such hidden classes -->
                        <reportUnchanged>false</reportUnchanged>
                    </nonPublicPartOfAPI>
                </checks>
            </revapi.java>
            <revapi.java.filter.annotated>
                <regex>false</regex>
                <exclude>
                    <item>@net.sourceforge.pmd.annotation.InternalApi</item>
                </exclude>
            </revapi.java.filter.annotated>
            <revapi.semver.ignore>
                <enabled>true</enabled>
            </revapi.semver.ignore>
            <revapi.differences>
                <differences>
                    <item>
                      <code>java.class.externalClassExposedInAPI</code>
                      <new>interface org.w3c.dom.UserDataHandler</new>
                      <justification>ADD YOUR EXPLANATION FOR THE NECESSITY OF THIS CHANGE</justification>
                      <ignore>true</ignore>
                      <!-- 
                      Additionally, the following attachments can be used to further identify the difference:
                    
                      <classSimpleName>UserDataHandler</classSimpleName>
                      <exampleUseChainInNewApi>org.w3c.dom.UserDataHandler is used as parameter in method java.lang.Object org.jaxen.dom.NamespaceNode::setUserData(java.lang.String, java.lang.Object, org.w3c.dom.UserDataHandler) (method java.lang.Object org.jaxen.dom.NamespaceNode::setUserData(java.lang.String, java.lang.Object, org.w3c.dom.UserDataHandler) is part of the API)</exampleUseChainInNewApi>
                      <package>org.w3c.dom</package>
                      <classQualifiedName>org.w3c.dom.UserDataHandler</classQualifiedName>
                      <elementKind>interface</elementKind>
                      <newArchive>jaxen:jaxen:jar:1.1.6</newArchive>
                      <newArchiveRole>supplementary</newArchiveRole>
                      <breaksSemanticVersioning>true</breaksSemanticVersioning>
                      -->
                    </item>
                </differences>
            </revapi.differences>
        </analysisConfiguration>
    </configuration>
    <executions>
        <execution>
            <phase>verify</phase>
            <goals>
                <goal>report</goal>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
