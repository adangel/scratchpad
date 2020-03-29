# Result from 2020-03-29

Details, see [results-2020-03-29.ods](results-2020-03-29.ods)

Comparisons:

*   baseline: PMD 6.22.0
*   master: PMD 6.23.0-SNAPSHOT at ac23b2cb3d6f77a8fc3dfd82d909d161b43e6ab6
*   master-with-2384: PMD 6.23.0-SNAPSHOT at ac23b2cb3d6f77a8fc3dfd82d909d161b43e6ab6 + merged in [PR #2384](https://github.com/pmd/pmd/pulls/2384)
*   master-with-2384-and-2377: PMD 6.23.0-SNAPSHOT at ac23b2cb3d6f77a8fc3dfd82d909d161b43e6ab6 + merged in [PR #2384](https://github.com/pmd/pmd/pulls/2384) + merged in [PR #2377](https://github.com/pmd/pmd/pulls/2377)
*   master_with_2377_without_ancestor: PMD 6.23.0-SNAPSHOT at a3cd0cf47dc7491fa0ae65aba769711b44ad9d17 + merged in [PR #2377](https://github.com/pmd/pmd/pulls/2377) + JUnit-Rules refactored to avoid "ancestor::".

Each measurement has been done twice (except for "master-with-2384-and-2377") and the average has been used.

<table>
<tr><th></th><th>baseline</th><th>master</th><th>master-with-2384</th><th>master-with-2384-and-2377</th><th>master_with_2377_without_ancestor</th></tr>
<tr><th>Overall Duration</th><td>100%</td><td>90%</td><td>80%</td><td>3680% (!!)</td><td>124%</td></tr>
<tr><th>Overall Duration (time)</th><td>172s</td><td>154s</td><td>138s</td><td>6324s</td><td>214s</td></tr>
<tr><th>Number of faster rules</th><td>0</td><td>104</td><td>174</td><td>154</td><td>187</td></tr>
<tr><th>Number of slower rules</th><td>0</td><td>177</td><td>107</td><td>128</td><td>96</td></tr>
<tr><th>Number of rules, that are more than 25% slower</th><td>0</td><td>5</td><td>5</td><td>61</td><td>54</td></tr>
</table>

Rules, that are more than 25% slower with "master-with-2384":

*   [AvoidInstanceofChecksInCatchClause](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#avoidinstanceofchecksincatchclause) (182%)
*   [AvoidLosingExceptionInformation](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#avoidlosingexceptioninformation) (140%)
*   [AvoidSynchronizedAtMethodLevel](https://pmd.github.io/latest/pmd_rules_java_multithreading.html#avoidsynchronizedatmethodlevel) (126%)
*   [NonCaseLabelInSwitchStatement](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#noncaselabelinswitchstatement) (149%)
*   [SimplifyBooleanExpressions](https://pmd.github.io/latest/pmd_rules_java_design.html#simplifybooleanexpressions) (162%)

With [PR #2377](https://github.com/pmd/pmd/pulls/2377) (all rules moved to XPath 2.0), the top 5 worst performing rules are:

* [JUnitStaticSuite](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#junitstaticsuite) (87157%)
* [UseAssertEqualsInsteadOfAssertTrue](https://pmd.github.io/latest/pmd_rules_java_bestpractices.html#useassertequalsinsteadofasserttrue) (66832%)
* [UseAssertNullInsteadOfAssertTrue](https://pmd.github.io/latest/pmd_rules_java_bestpractices.html#useassertnullinsteadofasserttrue) (56488%)
* [UnnecessaryBooleanAssertion](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#unnecessarybooleanassertion) (55837%)
* [UseAssertSameInsteadOfAssertTrue](https://pmd.github.io/latest/pmd_rules_java_bestpractices.html#useassertsameinsteadofasserttrue) (12995%)

Slow rules with "master_with_2377_without_ancestor":
(The rules UseAssertEqualsInsteadOfAssertTrue, UseAssertNullInsteadOfAssertTrue, UseAssertSameInsteadOfAssertTrue, SimplifyBooleanAssertion, JUnitSpelling, JUnitStaticSuite, UnnecessaryBooleanAssertion are changed to avoid "ancestor::")

* JUnitStaticSuite (4627%)
* JUnit4TestShouldUseBeforeAnnotation (4500%) (Interesting though: JUnit4TestShouldUseTestAnnotation is much faster: 20% !!)
* JUnit4TestShouldUseAfterAnnotation (3678%)
* UnnecessaryBooleanAssertion (2353%)
* JUnit4SuitesShouldUseSuiteAnnotation (2241%)
* ProperCloneImplementation (776%)
* JUnitSpelling (735%)
* AvoidDecimalLiteralsInBigDecimalConstructor (471%)
* UseAssertEqualsInsteadOfAssertTrue (449%)
* AbstractClassWithoutAbstractMethod (372%)
* UseAssertNullInsteadOfAssertTrue (366%) (Interesting: UseAssertSameInsteadOfAssertTrue is much faster: 85%)
* PositionLiteralsFirstInCaseInsensitiveComparisons (310%)
* PositionLiteralsFirstInComparisons (290%)
* EmptyCatchBlock (243%)
* AvoidFileStream (222%)
* ForLoopVariableCount (202%)
* EmptyStatementBlock (194%)
* SuspiciousEqualsMethodName (194%)
* SimpleDateFormatNeedsLocale (192%)
* CloneThrowsCloneNotSupportedException (188%)
* UseAssertTrueInsteadOfAssertEquals (188%)
* SimplifyConditional (187%)
* ForLoopCanBeForeach (186%)
* UselessQualifiedThis (170%)
* SimplifyBooleanExpressions (166%)
* AvoidUsingNativeCode (161%)
* AvoidThrowingNullPointerException (160%)
* LongVariable (158%)
* UseCorrectExceptionLogging (155%)
* ConstantsInInterface (153%)
* ShortMethodName (153%)
* UseProperClassLoader (153%)
* FinalizeOnlyCallsSuperFinalize (152%)
* AvoidThreadGroup (148%)
* AvoidProtectedFieldInFinalClass (147%)
* DoNotHardCodeSDCard (144%)
* OptimizableToArrayCall (144%)
* IntegerInstantiation (142%)
* ByteInstantiation (140%)
* DoNotUseThreads (139%)
* FinalizeDoesNotCallSuperFinalize (138%)
* DoNotExtendJavaLangError (136%)
* AvoidArrayLoops (135%)
* FinalizeOverloaded (135%)
* ShortInstantiation (134%)
* LongInstantiation (131%)
* UseEqualsToCompareStrings (131%)
* UseLocaleWithCaseConversions (130%)
* EmptyFinalizer (129%)
* UseNotifyAllInsteadOfNotify (129%)
* CloneMethodMustBePublic (128%)
* DoNotCallGarbageCollectionExplicitly (128%)
* AvoidEnumAsIdentifier (127%)
* ReplaceHashtableWithMap (126%)
