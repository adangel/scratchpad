# PMD Issue 1330

<https://github.com/pmd/pmd/issues/1330>

Just run

    mvn clean verify

It fails with

```
[INFO] >>> maven-pmd-plugin:3.10.0:check (pmd-check) > :pmd @ pmd-issue-1330 >>>
[INFO] 
[INFO] --- maven-pmd-plugin:3.10.0:pmd (pmd) @ pmd-issue-1330 ---
[WARNING] Unable to locate Source XRef to link to - DISABLED
[WARNING] Unable to locate Source XRef to link to - DISABLED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.737 s
[INFO] Finished at: 2018-09-18T19:53:43+02:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-pmd-plugin:3.10.0:pmd (pmd) on project pmd-issue-1330: Execution pmd of goal org.apache.maven.plugins:maven-pmd-plugin:3.10.0:pmd failed: An API incompatibility was encountered while executing org.apache.maven.plugins:maven-pmd-plugin:3.10.0:pmd: java.lang.ClassFormatError: Absent Code attribute in method that is not native or abstract in class file javax/servlet/jsp/PageContext
```

Stacktrace via `mvn clean verify -e`:

```
Caused by: java.lang.ClassFormatError: Absent Code attribute in method that is not native or abstract in class file javax/servlet/jsp/PageContext
    at java.lang.ClassLoader.defineClass1 (Native Method)
    at java.lang.ClassLoader.defineClass (ClassLoader.java:1009)
    at java.security.SecureClassLoader.defineClass (SecureClassLoader.java:174)
    at java.net.URLClassLoader.defineClass (URLClassLoader.java:545)
    at java.net.URLClassLoader.access$100 (URLClassLoader.java:83)
    at java.net.URLClassLoader$1.run (URLClassLoader.java:453)
    at java.net.URLClassLoader$1.run (URLClassLoader.java:447)
    at java.security.AccessController.doPrivileged (Native Method)
    at java.net.URLClassLoader.findClass (URLClassLoader.java:446)
    at net.sourceforge.pmd.util.ClasspathClassLoader.loadClass (ClasspathClassLoader.java:114)
    at java.lang.ClassLoader.loadClass (ClassLoader.java:499)
    at java.lang.ClassLoader.defineClass1 (Native Method)
    at java.lang.ClassLoader.defineClass (ClassLoader.java:1009)
    at java.security.SecureClassLoader.defineClass (SecureClassLoader.java:174)
    at java.net.URLClassLoader.defineClass (URLClassLoader.java:545)
    at java.net.URLClassLoader.access$100 (URLClassLoader.java:83)
    at java.net.URLClassLoader$1.run (URLClassLoader.java:453)
    at java.net.URLClassLoader$1.run (URLClassLoader.java:447)
    at java.security.AccessController.doPrivileged (Native Method)
    at java.net.URLClassLoader.findClass (URLClassLoader.java:446)
    at net.sourceforge.pmd.util.ClasspathClassLoader.loadClass (ClasspathClassLoader.java:114)
    at java.lang.ClassLoader.loadClass (ClassLoader.java:553)
    at java.lang.ClassLoader.loadClass (ClassLoader.java:499)
    at net.sourceforge.pmd.lang.java.typeresolution.PMDASMClassLoader.loadClass (PMDASMClassLoader.java:75)
    at net.sourceforge.pmd.lang.java.qname.JavaTypeQualifiedName.loadType (JavaTypeQualifiedName.java:195)
    at net.sourceforge.pmd.lang.java.qname.JavaTypeQualifiedName.getType (JavaTypeQualifiedName.java:175)
    at net.sourceforge.pmd.lang.java.ast.AbstractAnyTypeDeclaration.setQualifiedName (AbstractAnyTypeDeclaration.java:85)
    at net.sourceforge.pmd.lang.java.qname.QualifiedNameResolver.visit (QualifiedNameResolver.java:212)
    at net.sourceforge.pmd.lang.java.ast.JavaParserVisitorReducedAdapter.visit (JavaParserVisitorReducedAdapter.java:16)
    at net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration.jjtAccept (ASTClassOrInterfaceDeclaration.java:55)
    at net.sourceforge.pmd.lang.java.ast.AbstractJavaNode.childrenAccept (AbstractJavaNode.java:60)
    at net.sourceforge.pmd.lang.java.ast.JavaParserVisitorAdapter.visit (JavaParserVisitorAdapter.java:11)
    at net.sourceforge.pmd.lang.java.ast.JavaParserVisitorAdapter.visit (JavaParserVisitorAdapter.java:206)
    at net.sourceforge.pmd.lang.java.ast.ASTTypeDeclaration.jjtAccept (ASTTypeDeclaration.java:37)
    at net.sourceforge.pmd.lang.java.ast.AbstractJavaNode.childrenAccept (AbstractJavaNode.java:60)
    at net.sourceforge.pmd.lang.java.ast.JavaParserVisitorAdapter.visit (JavaParserVisitorAdapter.java:11)
    at net.sourceforge.pmd.lang.java.ast.JavaParserVisitorAdapter.visit (JavaParserVisitorAdapter.java:181)
    at net.sourceforge.pmd.lang.java.qname.QualifiedNameResolver.visit (QualifiedNameResolver.java:134)
    at net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit.jjtAccept (ASTCompilationUnit.java:41)
    at net.sourceforge.pmd.lang.java.qname.QualifiedNameResolver.initializeWith (QualifiedNameResolver.java:118)
    at net.sourceforge.pmd.lang.java.AbstractJavaHandler$8.start (AbstractJavaHandler.java:140)
    at net.sourceforge.pmd.SourceCodeProcessor.resolveQualifiedNames (SourceCodeProcessor.java:125)
    at net.sourceforge.pmd.SourceCodeProcessor.processSource (SourceCodeProcessor.java:176)
    at net.sourceforge.pmd.SourceCodeProcessor.processSourceCode (SourceCodeProcessor.java:96)
    at net.sourceforge.pmd.SourceCodeProcessor.processSourceCode (SourceCodeProcessor.java:51)
    at net.sourceforge.pmd.processor.PmdRunnable.call (PmdRunnable.java:78)
    at net.sourceforge.pmd.processor.PmdRunnable.call (PmdRunnable.java:24)
    at java.util.concurrent.FutureTask.run (FutureTask.java:264)
    at java.util.concurrent.Executors$RunnableAdapter.call (Executors.java:514)
    at java.util.concurrent.FutureTask.run (FutureTask.java:264)
    at java.util.concurrent.ThreadPoolExecutor.runWorker (ThreadPoolExecutor.java:1135)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run (ThreadPoolExecutor.java:635)
    at java.lang.Thread.run (Thread.java:844)
```

The dependency with the class format error classes is:

    <dependency>
        <groupId>javax</groupId>
        <artifactId>javaee-api</artifactId>
        <version>6.0</version>
    </dependency>
