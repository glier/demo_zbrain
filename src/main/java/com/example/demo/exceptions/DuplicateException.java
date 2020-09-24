package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

public class DuplicateException extends RuntimeException {
    public Map<String, Boolean> getBody() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("duplicate", true);
        return map;
    }
}