package com.bank.customListener;


import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomTestListener implements TestExecutionListener {

    private final AtomicInteger totalTests = new AtomicInteger(0);
    private final AtomicInteger passedTests = new AtomicInteger(0);

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        System.out.println("Running tests with JUnit...");
        System.out.println();
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            totalTests.incrementAndGet();
            System.out.print("Test: " + testIdentifier.getDisplayName() + " .......... ");
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult result) {
        if (testIdentifier.isTest()) {
            if (result.getStatus() == TestExecutionResult.Status.SUCCESSFUL) {
                passedTests.incrementAndGet();
                System.out.println("PASSED");
            } else {
                System.out.println("FAILED");
                result.getThrowable().ifPresent(t ->
                        System.out.println("  Cause: " + t.getMessage())
                );
            }
        }
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        if (testIdentifier.isTest()) {
            totalTests.incrementAndGet();
            System.out.println("Test: " + testIdentifier.getDisplayName() + " .......... SKIPPED");
        }
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        System.out.println();
        if (passedTests.get() == totalTests.get()) {
            System.out.println("✓ All " + totalTests.get() + " tests passed successfully!");
        } else {
            System.out.println("✗ " + passedTests.get() + "/" + totalTests.get() + " tests passed.");
        }
        System.out.println();
    }
}
