/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.jmh.xpath;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark {
    private final UseAssertEqualsInsteadOfAssertTrueBenchmarker benchmarker;

    public UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark() {
        benchmarker = new UseAssertEqualsInsteadOfAssertTrueBenchmarker("improved");
    }

    @Setup
    public void setup() {
        benchmarker.setup();
    }

    @Benchmark
    public void jaxen() {
        benchmarker.jaxen();
    }

    @Benchmark
    public void jaxenRuleChain() {
        benchmarker.jaxenRuleChain();
    }

    @Benchmark
    public void saxon() {
        benchmarker.saxon();
    }

    @Benchmark
    public void saxonRuleChain() {
        benchmarker.saxonRuleChain();
    }

    public static void main(String[] args) throws InterruptedException {
        UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark test = new UseAssertEqualsInsteadOfAssertTrueImprovedBenchmark();
        test.setup();

        //test.saxonLoop();

        System.out.println(test.getClass().getName());
        System.out.println("Jaxen");
        test.jaxen();
        test.jaxenRuleChain();
        System.out.println("Saxon");
        test.saxon();
        test.saxonRuleChain();
    }
}
