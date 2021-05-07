To reproduce:

```
mvn clean package

pmd/bin/run.sh pmd -d src -auxclasspath target/classes -f text -debug -R rulesets/java/quickstart.xml
```

Basically providing an incomplete auxclasspath, e.g. money-api is missing.

You should see these exceptions:

```
Caused by: java.lang.ClassNotFoundException: javax.money.Monetary
	at java.base/java.net.URLClassLoader.findClass(URLClassLoader.java:433)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:586)
	at net.sourceforge.pmd.util.ClasspathClassLoader.loadClass(ClasspathClassLoader.java:123)


Caused by: java.lang.NullPointerException: Cannot invoke "net.sourceforge.pmd.lang.java.typeresolution.typedefinition.JavaTypeDefinition.getType()" because "argSuper" is null
	at net.sourceforge.pmd.lang.java.typeresolution.MethodTypeResolution.isSubtypeable(MethodTypeResolution.java:692)
	at net.sourceforge.pmd.lang.java.typeresolution.MethodTypeResolution.isSubtypeable(MethodTypeResolution.java:656)
	at net.sourceforge.pmd.lang.java.typeresolution.MethodTypeResolution.selectMethodsFirstPhase(MethodTypeResolution.java:165)
	at net.sourceforge.pmd.lang.java.typeresolution.MethodTypeResolution.getBestMethodReturnType(MethodTypeResolution.java:381)
	at net.sourceforge.pmd.lang.java.typeresolution.ClassTypeResolver.visit(ClassTypeResolver.java:445)
	at net.sourceforge.pmd.lang.java.ast.ASTName.jjtAccept(ASTName.java:38)
```
