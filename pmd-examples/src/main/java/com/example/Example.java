package com.example;

import java.io.InputStream;
import java.io.IOException;

public class Example {
    public void bar() throws IOException {
        InputStream in = null;
        try (in) {
            System.out.println("test");
        }
    }
}
