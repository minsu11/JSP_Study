package com.example.annotation;

public class ArrayListTestMain {
    public static void main(String[] args) {
        ArrayListText arrayListText = new ArrayListText();
        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy(arrayListText);
        arrayListTestProxy.test();
    }
}
