package com.bank.customListener;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class HandleTests {
    public static void handleRunTests() {
        System.out.println("\nRUNNING TESTS...");
        System.out.println("-".repeat(40));

        try {
            // Manually load the test-classes directory into the classloader
            File testClassesDir = new File("target/test-classes");
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{testClassesDir.toURI().toURL()},
                    Thread.currentThread().getContextClassLoader()
            );
            Thread.currentThread().setContextClassLoader(classLoader);

            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(selectPackage("com.bank"))
                    .filters(includeClassNamePatterns(".*Test"))  // match test classes
                    .build();

            Launcher launcher = LauncherFactory.create();
            launcher.execute(request, new CustomTestListener());

        } catch (Exception e) {
            System.out.println("Failed to run tests: " + e.getMessage());
        }

        System.out.println("-".repeat(40));
    }
}
