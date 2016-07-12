package com.umland.learnjava.generics.erasure;

import java.util.List;

public class Overloading {
    public void print(String param) {
    }

    public void print(Integer param) {
    }

    public void print(List<String> param) {
    }

    // this and print(List<String> param) have the same erasure
//    public void print(List<Integer> param) {
//    }
}
