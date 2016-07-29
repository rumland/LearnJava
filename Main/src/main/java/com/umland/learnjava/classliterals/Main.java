package com.umland.learnjava.classliterals;

public class Main {
    public static void main(String args[]) {
        Injector injector = new Injector().with("Hello world!");

        Logger logger = injector.newInstance(Logger.class);
        logger.log();
    }
}
