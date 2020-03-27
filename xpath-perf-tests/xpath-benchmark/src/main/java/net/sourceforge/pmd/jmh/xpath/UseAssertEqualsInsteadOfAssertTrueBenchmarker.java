/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */


package net.sourceforge.pmd.jmh.xpath;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression;
import net.sourceforge.pmd.lang.java.xpath.TypeIsFunction;
import net.sourceforge.pmd.lang.rule.xpath.AbstractXPathRuleQuery;
import net.sourceforge.pmd.lang.rule.xpath.JaxenXPathRuleQuery;
import net.sourceforge.pmd.lang.rule.xpath.SaxonXPathRuleQuery;

public class UseAssertEqualsInsteadOfAssertTrueBenchmarker {
    public static final boolean DEBUG = false;

    private static final String BASE_NAME = "UseAssertEqualsInsteadOfAssertTrue";
    private static final String CODE_RESOURCE = BASE_NAME + "_code.java";

    private final String code;
    private final String xpath;
    private final LanguageVersion java;
    private final Parser parser;

    static {
        TypeIsFunction.registerSelfInSimpleContext();
    }

    public UseAssertEqualsInsteadOfAssertTrueBenchmarker(String benchmarkVariant) {
        try {
            this.code = IOUtils.toString(UseAssertEqualsInsteadOfAssertTrueBenchmarker.class.getResourceAsStream(CODE_RESOURCE), StandardCharsets.UTF_8);
            this.xpath = IOUtils.toString(UseAssertEqualsInsteadOfAssertTrueBenchmarker.class.getResourceAsStream(BASE_NAME + "_" + benchmarkVariant + ".xpath"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        java = LanguageRegistry.getLanguage("Java").getDefaultVersion();
        parser = java.getLanguageVersionHandler().getParser(java.getLanguageVersionHandler().getDefaultParserOptions());
    }


    private RuleContext ruleContext;
    private Node node;
    private List<ASTPrimaryExpression> primaryExpressions;
    private JaxenXPathRuleQuery jaxenQuery;
    private SaxonXPathRuleQuery saxonQuery;

    public void setup() {
        ruleContext = new RuleContext();
        ruleContext.setLanguageVersion(java);
        // note: that's without typeres
        node = parser.parse("no-name", new StringReader(code));
        primaryExpressions = node.findDescendantsOfType(ASTPrimaryExpression.class);

        jaxenQuery = new JaxenXPathRuleQuery();
        jaxenQuery.setXPath(xpath);
        jaxenQuery.setProperties(Collections.emptyMap());
        jaxenQuery.evaluate(node, ruleContext);

        saxonQuery = new SaxonXPathRuleQuery();
        saxonQuery.setXPath(xpath);
        saxonQuery.setProperties(Collections.emptyMap());
        saxonQuery.evaluate(node, ruleContext);
    }

    private void runWith(AbstractXPathRuleQuery query) {
        final List<Node> result = query.evaluate(node, ruleContext);
        if (DEBUG) {
            System.out.println("query " + query.getClass().getSimpleName() + " found " + result.size() + " nodes");
        }
        if (result.size() != 1) {
            throw new RuntimeException("Wrong number of nodes");
        }
    }

    private void runWithRuleChain(AbstractXPathRuleQuery query) {
        final Collection<Node> result = new ArrayList<>();
        for (ASTPrimaryExpression primaryNode : primaryExpressions) {
            result.addAll(query.evaluate(primaryNode, ruleContext));
        }
        if (DEBUG) {
            System.out.println("query " + query.getClass().getSimpleName() + " found " + result.size() + " nodes");
        }
        if (result.size() != 1) {
            throw new RuntimeException("Wrong number of nodes");
        }
    }

    public void jaxen() {
        runWith(jaxenQuery);
    }

    public void jaxenRuleChain() {
        runWithRuleChain(jaxenQuery);
    }

    public void saxon() {
        runWith(saxonQuery);
    }

    public void saxonRuleChain() {
        runWithRuleChain(saxonQuery);
    }

    protected void saxonLoop() throws InterruptedException {
        while (true) {
            //test.saxon();
            this.saxonRuleChain();
            //Thread.sleep(200);
        }
    }
}
