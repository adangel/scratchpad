package com.example;

import java.util.Map;
import java.util.HashMap;

public class DocImpl implements Document {
    public Map<String, Object> getRecord() {
        return new HashMap<>();
    }
}
