# Result from 2020-03-29

Details, see [results-2020-03-29.ods](results-2020-03-29.ods)

Comparisons:

*   baseline: PMD 6.22.0
*   master: PMD 6.23.0-SNAPSHOT at ac23b2cb3d6f77a8fc3dfd82d909d161b43e6ab6
*   master-with-2384: PMD 6.23.0-SNAPSHOT at ac23b2cb3d6f77a8fc3dfd82d909d161b43e6ab6 + merged in [PR #2384](https://github.com/pmd/pmd/pulls/2384)
*   master-with-2384-and-2377: PMD 6.23.0-SNAPSHOT at ac23b2cb3d6f77a8fc3dfd82d909d161b43e6ab6 + merged in [PR #2384](https://github.com/pmd/pmd/pulls/2384) + merged in [PR #2377](https://github.com/pmd/pmd/pulls/2377)

Each measurement has been done twice (except for "master-with-2384-and-2377") and the average has been used.

<table>
<tr><th></th><th>baseline</th><th>master</th><th>master-with-2384</th><th>master-with-2384-and-2377</th></tr>
<tr><th>Overall Duration</th><td>100%</td><td>90%</td><td>80%</td><td>??</td></tr>
<tr><th>Overall Duration (time)</th><td>172s</td><td>154s</td><td>138s</td><td>??</td></tr>
<tr><th>Number of faster rules</th><td>0</td><td>104</td><td>174</td><td>??</td></tr>
<tr><th>Number of slower rules</th><td>0</td><td>177</td><td>107</td><td>??</td></tr>
<tr><th>Number of rules, that are more than 25% slower</th><td>0</td><td>5</td><td>5</td><td>??</td></tr>
</table>

Rules, that are more than 25% slower with "master-with-2384":

*   [AvoidInstanceofChecksInCatchClause](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#avoidinstanceofchecksincatchclause) (182%)
*   [AvoidLosingExceptionInformation](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#avoidlosingexceptioninformation) (140%)
*   [AvoidSynchronizedAtMethodLevel](https://pmd.github.io/latest/pmd_rules_java_multithreading.html#avoidsynchronizedatmethodlevel) (126%)
*   [NonCaseLabelInSwitchStatement](https://pmd.github.io/latest/pmd_rules_java_errorprone.html#noncaselabelinswitchstatement) (149%)
*   [SimplifyBooleanExpressions](https://pmd.github.io/latest/pmd_rules_java_design.html#simplifybooleanexpressions) (162%)
