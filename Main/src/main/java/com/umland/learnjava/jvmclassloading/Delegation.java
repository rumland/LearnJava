package com.umland.learnjava.jvmclassloading;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Show default delegation of class loading.
 * <ol>
 *     <li>Application class loader</li>
 *     <li>Extension class loader</li>
 *     <li>Bootstrap class loader</li>
 * </ol>
 */
public class Delegation {
    public static void main(String[] args) {
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        do {
            System.out.println(classLoader);
            for (URL url : classLoader.getURLs()) {
                System.out.printf("\t %s%n", url.getPath());
            }
        } while((classLoader = (URLClassLoader) classLoader.getParent()) != null);
        System.out.println("Bootstrap ClassLoader");
    }
}
