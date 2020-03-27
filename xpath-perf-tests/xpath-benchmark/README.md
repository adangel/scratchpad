# XPath Benchmarks

## UseAssertEqualsInsteadOfAssertTrue

Rule documentation: [UseAssertEqualsInsteadOfAssertTrue](https://pmd.github.io/latest/pmd_rules_java_bestpractices.html#useassertequalsinsteadofasserttrue)

### Run

```
mvn clean verify
java -jar target/benchmarks.jar \
    net.sourceforge.pmd.jmh.xpath.UseAssertEqualsInsteadOfAssertTrueOriginalBenchmark \
    net.sourceforge.pmd.jmh.xpath.UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark
```

### Result

First the [original](src/main/resources/net/sourceforge/pmd/jmh/xpath/UseAssertEqualsInsteadOfAssertTrue_original.xpath)
XPath query:

```
Benchmark                                                            Mode  Cnt      Score     Error  Units
UseAssertEqualsInsteadOfAssertTrueOriginalBenchmark.jaxen           thrpt   25  32102,518 ± 350,910  ops/s
UseAssertEqualsInsteadOfAssertTrueOriginalBenchmark.jaxenRuleChain  thrpt   25  45534,341 ± 611,506  ops/s
UseAssertEqualsInsteadOfAssertTrueOriginalBenchmark.saxon           thrpt   25  43241,546 ± 182,855  ops/s
UseAssertEqualsInsteadOfAssertTrueOriginalBenchmark.saxonRuleChain  thrpt   25  40216,103 ± 206,093  ops/s
```

Observations:
* jaxen vs. jaxen+rulechain has a performance improvement of ~40%
* jaxen+rulechain vs. saxon: saxon is only ~5% slower. That means, the rule is almost as fast as the rulechain improved jaxen version.
* saxon vs. saxon+rulechain: this has a performance **degradation** of ~8%

One of the reasons, why this xpath query doesn't perform so well is: it uses a filter based on the whole document
somewhere (`[//ClassOrInterfaceType[...]]`). This seems to be optimized automatically in saxon: This expression is
extracted as a variable and executed only once, since it doesn't depend on any context and is therefore a constant.

When we execute the query as rulechain, this "constant subexpression" needs to be executed over and over again - the
automatic optimization of saxon is useless.

Here's the benchmark of an
[improved](src/main/resources/net/sourceforge/pmd/jmh/xpath/UseAssertEqualsInsteadOfAssertTrue_improved.xpath) query:

```
Benchmark                                                            Mode  Cnt      Score     Error  Units
UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark.jaxen           thrpt   25  46521,006 ± 343,316  ops/s
UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark.jaxenRuleChain  thrpt   25  85365,713 ± 841,453  ops/s
UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark.saxon           thrpt   25  43144,634 ± 231,733  ops/s
UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark.saxonRuleChain  thrpt   25  57806,864 ± 501,807  ops/s
```

Observations:
* the improved query is always faster except for the "normal" saxon case - which is equal. It seems, the saxon
does a good job in optimizing the query even if it is written suboptimal.
* for jaxen: improved vs. original - ~45% faster
* for jaxen original vs. jaxen+rulechain improved - ~165% faster
* for saxon: improved vs. original - ~1% slower...
* for saxon original vs. saxon+rulechain improved - ~33% faster
* and now the most important number: jaxen original vs. saxon+rulechain improved - which must not be slower: in fact
it is **~27% faster**.

Conclusion:
* if we just switch from jaxen to saxon and make an exception for rulechain, when queries use "constant subexpressions",
then the queries are executed ~5% slower.
* we should however improve the queries.

Caveat:
* This only looked at one rule. The [test code](src/main/resources/net/sourceforge/pmd/jmh/xpath/UseAssertEqualsInsteadOfAssertTrue_code.java)
is very small, e.g. it only has 7 primary expressions. So the overhead
of the multiple execution of the constant expression due to rule chain is not as big as in our travis build.
Here the ratio between primary expressions per file is 7, but on the travis build it was ~83.
