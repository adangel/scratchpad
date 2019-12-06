This sample project tries to reproduce [#2143 [java] [False positive] PMD fails to recognize method call when type passed in is var.](https://github.com/pmd/pmd/issues/2143).

Originated here: https://stackoverflow.com/questions/58529020/why-a-pmd-violation-for-unused-method-when-it-is-obviously-used


Just run `mvn clean verify` in order to compile and run pmd.

There is only one deliberate warning about the unused method called `myUnusedMethod`. But the method `processDocument(SolrInputDocument,Map,LongAdder)` is always correctly detected as being used.
