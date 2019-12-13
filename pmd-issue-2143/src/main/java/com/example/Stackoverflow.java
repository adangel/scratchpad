package com.example;

public class Stackoverflow {

    public static void main(String[] args) {
        var s = new Stackoverflow();
        s.anyMethod();
    }

    private void anyMethod() {
        var myString = "a String";
        printMyString(myString);
    }

    private void printMyString(String string) {
        System.out.println(string);
    }
}
