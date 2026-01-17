package net.sourceforge.pmd.it.issue6395deprecatedrules;

import java.util.Arrays;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.event.Level;

import net.sourceforge.pmd.util.log.PmdReporter;
import net.sourceforge.pmd.lang.rule.RuleSet;
import net.sourceforge.pmd.lang.rule.RuleSetLoader;
import net.sourceforge.pmd.lang.rule.InternalApiBridge;

class RuleSetLoaderTest {
    @Test
    void loadRuleSetsWithoutException() {
        RuleSetLoader loader = new RuleSetLoader();
        InternalApiBridge.withReporter(loader, new PmdReporter() {
            @Override
            public boolean isLoggable(Level level) {
                return true;
            }

            @Override
            public void logEx(Level level, @Nullable String message, Object[] formatArgs, @Nullable Throwable error) {
                System.out.println("message = " + message);
                System.out.println("error = " + error);
            }

            @Override
            public int numErrors() {
                return 0;
            }
        });
        List<String> rulesetPaths = Arrays.asList("ruleset-issue6395.xml");
        List<RuleSet> ruleSets = InternalApiBridge.loadRuleSetsWithoutException(loader, rulesetPaths);
        System.out.println("ruleSets = " + ruleSets);
        Assertions.assertEquals(1, ruleSets.size());
        ruleSets.get(0).getRules().forEach(rule -> {
            System.out.println("rule = " + rule.getName());
        });
        Assertions.assertNotNull(ruleSets.get(0).getRuleByName("ExcessiveClassLength"));
    }
}

