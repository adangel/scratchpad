# Java Inner Class public + private

Reproducer for pmd/pmd#6932

1. Run `./compile.sh` -> produces normal `input.jar`
2. Run `./run-r8.sh` -> performs optimizations and produces `output.jar`
3. Run `./run-pmd.sh` -> fails with the following stacktrace:

```
Exception in thread "main" java.lang.AssertionError: This should be unreachable: private field of an interface? ClassWithInner$Inner, modifiers: public private static
        at net.sourceforge.pmd.util.AssertionUtil.shouldNotReachHere(AssertionUtil.java:243)
        at net.sourceforge.pmd.util.AssertionUtil.shouldNotReachHere(AssertionUtil.java:236)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.JavaResolvers.isAccessibleIn(JavaResolvers.java:479)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.JavaResolvers.lambda$hidingWalkResolvers$3(JavaResolvers.java:380)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.JavaResolvers.processDeclarations(JavaResolvers.java:432)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.JavaResolvers.walkSelf(JavaResolvers.java:409)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.JavaResolvers.hidingWalkResolvers(JavaResolvers.java:383)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.JavaResolvers.inheritedMembersResolvers(JavaResolvers.java:355)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymTableFactory.typeBody(SymTableFactory.java:394)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymbolTableResolver$MyVisitor.visitTypeDecl(SymbolTableResolver.java:299)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymbolTableResolver$MyVisitor.visitTypeDecl(SymbolTableResolver.java:158)
        at net.sourceforge.pmd.lang.java.ast.JavaVisitorBase.visit(JavaVisitorBase.java:56)
        at net.sourceforge.pmd.lang.java.ast.ASTClassDeclaration.acceptVisitor(ASTClassDeclaration.java:39)
        at net.sourceforge.pmd.lang.java.ast.AbstractJavaNode.acceptVisitor(AbstractJavaNode.java:38)
        at net.sourceforge.pmd.lang.ast.AstVisitorBase.visitChildren(AstVisitorBase.java:31)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymbolTableResolver$MyVisitor.visit(SymbolTableResolver.java:260)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymbolTableResolver$MyVisitor.visit(SymbolTableResolver.java:158)
        at net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit.acceptVisitor(ASTCompilationUnit.java:106)
        at net.sourceforge.pmd.lang.java.ast.AbstractJavaNode.acceptVisitor(AbstractJavaNode.java:38)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymbolTableResolver$MyVisitor.traverse(SymbolTableResolver.java:184)
        at net.sourceforge.pmd.lang.java.symbols.table.internal.SymbolTableResolver.traverse(SymbolTableResolver.java:103)
        at net.sourceforge.pmd.lang.java.internal.JavaAstProcessor.lambda$process$1(JavaAstProcessor.java:136)
        at net.sourceforge.pmd.benchmark.TimeTracker.bench(TimeTracker.java:163)
        at net.sourceforge.pmd.lang.java.internal.JavaAstProcessor.process(JavaAstProcessor.java:136)
        at net.sourceforge.pmd.lang.java.internal.JavaAstProcessor.process(JavaAstProcessor.java:177)
        at net.sourceforge.pmd.lang.java.internal.JavaAstProcessor.process(JavaAstProcessor.java:161)
        at net.sourceforge.pmd.lang.java.ast.JavaParser.parseImpl(JavaParser.java:70)
        at net.sourceforge.pmd.lang.java.ast.JavaParser.parseImpl(JavaParser.java:25)
        at net.sourceforge.pmd.lang.ast.impl.javacc.JjtreeParserAdapter.parse(JjtreeParserAdapter.java:36)
        at net.sourceforge.pmd.lang.impl.PmdRunnable.parse(PmdRunnable.java:112)
        at net.sourceforge.pmd.lang.impl.PmdRunnable.processSource(PmdRunnable.java:132)
        at net.sourceforge.pmd.lang.impl.PmdRunnable.run(PmdRunnable.java:80)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:545)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:328)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
        at java.base/java.lang.Thread.run(Thread.java:1474)
```

* R8 is available here: <https://r8.googlesource.com/r8/>
* Available options for prodguard.cfg: <https://developer.android.com/topic/performance/app-optimization/global-options>