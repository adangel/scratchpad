# Results from 2020-04-03

## master-with-2380

* This is on [04f0bb78b9b179cee7f7aee0dc881f3ae6d281e2](https://github.com/pmd/pmd/commit/04f0bb78b9b179cee7f7aee0dc881f3ae6d281e2)
* Benchmark: [master-with-2380-log.txt](master-with-2380-log.txt)

* Comparison against 6.22.0
    * Overall time: 200s --> 140s: PMD takes 70% of the time (= 30% less time)

## master-with-2377

* This is master merged into [pr-2377](https://github.com/pmd/pmd/pulls/2377): [3e932de647b623fc17b6d123f2ea6a13e4342325](https://github.com/pmd/pmd/commit/3e932de647b623fc17b6d123f2ea6a13e4342325)
    So, this contains the typeres optimization from above (pr 2380).
* Benchmark: [master-with-2377-log.txt](master-with-2377-log.txt)

* Comparison against 6.22.0
    * Overall time: 200s --> 637s: PMD takes 320% of the time (= 220% more)

## master-with-2377-without-ancestor

* Refactored rules like UseAssertSameInsteadOfAssertTrue to not use ancestor:: axis. These are the rules, that use the rule chain and are executed very often. E.g. they select all primary expressions, which are in the test 470,274 nodes. By changing these rules, they
are only executed for unit test classes. In these cases, the rule chain has a negative effect.
*   rules affected:
    *   best practices: UseAssertEqualsInsteadOfAssertTrue, UseAssertNullInsteadOfAssertTrue, UseAssertSameInsteadOfAssertTrue
    *   design: SimplifyBooleanAssertion
    *   error prone: JUnitSpelling, JUnitStaticSuite, UnnecessaryBooleanAssertion
* Benchmark: [master-with-2377-without-ancestor](master-with-2377-without-ancestor-log.txt)

* Comparison against 6.22.0
    * Overall time: 200s --> 280s: PMD takes 140% of the time (= 40% more)

## master-with-2377-without-ancestor-not

*   use not(..) instead of count(...) = 0 where possible
*   rules changes:
    *   best practices: AbstractClassWithoutAbstractMethod, JUnit4TestShouldUseAfterAnnotation, JUnit4TestShouldUseBeforeAnnotation, UseAssertSameInsteadOfAssertTrue
    *   code style: CallSuperInConstructor, EmptyMethodInAbstractClassShouldBeAbstract, UselessParentheses
    *   design: AbstractClassWithoutAnyMethod, ClassWithOnlyPrivateConstructorsShouldBeFinal, SimplifyConditional
    *   documentation: UncommentedEmptyConstructor
    *   error prone: ClassCastExceptionWithToArray, EmptyCatchBlock, EmptyFinalizer, EmptyFinallyBlock, EmptyIfStmt, EmptyInitializer, EmptyStatementBlock, EmptySynchronizedBlock, EmptyTryBlock, EmptyWhileStmt, ProperLogger, UnusedNullCheckInEquals
    *   performance: UseArrayListInsteadOfVector, UseArraysAsList
*   Benchmark [master-with-2377-without-ancestor-not](master-with-2377-without-ancestor-not-log.txt)

*   Comparison against 6.22.0
    *   Overall time: 200s --> 153s: PMD takes now 77% of the time (= 23% less time)

There are still some rules, for which we have a performance regression...

## master-with-2377-without-ancestor-not-optimized

*   More optimizations done for the rules 5 worst performing rules: ProperCloneImplementation,
    JUnit4SuitesShouldUseSuiteAnnotation, AbstractClassWithoutAbstractMethod,
    AvoidDecimalLiteralsInBigDecimalConstructor, JUnitStaticSuite
*   Benchmark [master-with-2377-without-ancestor-not-optimized](master-with-2377-without-ancestor-not-optimized-log.txt)

*   Comparison against 6.22.0
    *   Overall time: 200s --> 153s: PMD takes now 77% of the time (= 23% less time)

*   AbstractClassWithoutAbstractMethod is now twice as fast, but still slower as in 6.22.0

The ten slowest rules compared to 6.22.0:

* ProperCloneImplementation
* AvoidDecimalLiteralsInBigDecimalConstructor
* JUnitStaticSuite
* PositionLiteralsFirstInCaseInsensitiveComparisons
* PositionLiteralsFirstInComparisons
* EmptyCatchBlock
* AbstractClassWithoutAbstractMethod
* AvoidFileStream
* ForLoopVariableCount
* LogicInversion
* JUnit4SuitesShouldUseSuiteAnnotation



## Further optimizations

*   use descendant::x[parent::y] instead of //y/x (this optimization would be applied by saxon 9.6)
*   update saxon. We currently use 9.1
*   rewrite some rules as Java rules
