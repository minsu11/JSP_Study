package com.example.annotation;

import java.util.ArrayList;
import java.util.List;

public class ArrayListText implements PerformanceTestable {
    @StopWatch
    @Override
    public void test() {
        List<Integer> integerList = new ArrayList();
        for (int i = 0; i < 1000000; i++) {
            integerList.add(i);
        }
    }

}
