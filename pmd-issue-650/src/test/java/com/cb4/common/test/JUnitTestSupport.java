
package com.cb4.common.test;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Map.Entry;

public class JUnitTestSupport {

    public static final String JAVA_SUFFIX = "TEST";
    public static final String JAVA_TYPE = "TYPE";

    public String getCurrentTestName() {
        return null;
    }
    
    public Class<?> getCurrentTestClass() {
        return this.getClass();
    }
    
    public static void assertListEquals(String currentTestName, List<Entry<String, Object>> expected,
            List<Entry<String, Object>> actual) {
        // TODO Auto-generated method stub
        
    }

    public static void assertMapEquals(String currentTestName, Map<String, Object> singletonMap,
            NavigableMap<String, Object> actual) {
        // TODO Auto-generated method stub
        
    }

    public static void assertMapEquals(String currentTestName, Map<Integer, Integer> expected, Map<Integer, Integer> actual) {
        // TODO Auto-generated method stub
        
    }

    public String shuffleCase(String name) {
        // TODO Auto-generated method stub
        return null;
    }


}
