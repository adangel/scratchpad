
package com.cb4.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.collections4.Transformer;

public class MapUtils {

    public static <K, V> Map<K, V> putUniqueValues(Map<?, ?> src, Map<K, V> dst) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Map<Integer, String> sort(Map<Integer, String> map) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void addValueToMapList(Map<Integer, ArrayList<String>> map, int i, String string) {
        // TODO Auto-generated method stub
        
    }

    public static Map<String, Integer> sortByValues(Map<String, Integer> testMap) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Comparator<Entry<String, Object>> byKeyComparator(Comparator<String> caseInsensitiveOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    public static <K extends L, V extends M, L, M> Map<M, L> flip(boolean b, Map<K, V> src,
            HashMap<M, L> hashMap) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Object flip(boolean b, Object src, Map<Object, Object> dst) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Map<String, String> flip(boolean b, Map<String, String> map, TreeMap<String, String> treeMap) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Object getMandatoryValue(Map<String, Object> map, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    public static int getMandatoryIntValue(Map<String, Object> map, String string) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static double getMandatoryDoubleValue(Map<String, Object> map, String string) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static Object nestedLen(Map<String, Collection<Object>> mapOfColls) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Map<String, String> clear(Object object) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Map<String, String> clearAndReplace(Map<String, String> original, Map<String, String> expected) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Transformer<Entry<String, Object>, String> keyExtractor() {
        // TODO Auto-generated method stub
        return null;
    }

    public static Transformer<Entry<String, Object>, Object> valueExtractor() {
        // TODO Auto-generated method stub
        return null;
    }

    public static NavigableMap<String, Object> filterByKey(Map<String, Object> map, Predicate<?> filter, Supplier<?> object2) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Map<Integer, Integer> filterByEntry(Map<Integer, Integer> map,
            Predicate<Entry<Integer, Integer>> selector, Supplier<?> object) {
        // TODO Auto-generated method stub
        return null;
    }

    public static boolean equalProperties(Properties p1, Properties p2) {
        // TODO Auto-generated method stub
        return false;
    }

    public static boolean equalProperties(Properties p1, Properties p2, Comparator<?> c1, Comparator<?> c2) {
        // TODO Auto-generated method stub
        return false;
    }

    public static boolean isSubMap(Map<String, Integer> m1, Map<String, Integer> empty) {
        // TODO Auto-generated method stub
        return false;
    }

    public static NavigableMap<String, Integer> getSubMap(Map<String, Integer> hashMap, Predicate<Map.Entry<String, Integer>> object, Supplier<?> object2) {
        // TODO Auto-generated method stub
        return null;
    }
}
